/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cxf.debug.client;

import com.mycompany.cxf.debug.auth.Authenticator;
import com.mycompany.cxf.debug.model.Input;
import com.mycompany.cxf.debug.model.RequestModel;
import com.mycompany.cxf.debug.model.ServiceBusModel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.johnzon.jaxrs.WildcardJsrProvider;

/**
 *
 * @author helge
 */
@Singleton
@Lock(LockType.READ)
public class RestfulProvider {
    
    private final Map<String, Invocation.Builder> clientMap = new HashMap<>();
    
    public Future<Response> sendRequest(ServiceBusModel model, RequestModel request) {
        JsonObjectBuilder createObjectBuilder = Json.createObjectBuilder();
        request.getParameters().forEach((Input input) -> {
            createObjectBuilder.add(input.getName(), input.getValue());
        });
        
        return getClient(model).async().post(Entity.entity(createObjectBuilder.build(), MediaType.APPLICATION_JSON_TYPE));
    }
    
    private Invocation.Builder getClient(ServiceBusModel model) {
        Invocation.Builder builder = clientMap.get(model.getDestinationHost() + model.getDestinationPath());
        if (builder == null) {
            Client register = ClientBuilder.newClient()
                    .register(WildcardJsrProvider.class)
                    .register(LoggingFeature.class)
                    .register(new Authenticator(model.getUsername(), model.getPassword()));
            builder = register.target(model.getDestinationHost())
                    .path(model.getDestinationPath())
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE);
            clientMap.put(model.getDestinationHost() + model.getDestinationPath(), builder);
        }
        return builder;
    }
}
