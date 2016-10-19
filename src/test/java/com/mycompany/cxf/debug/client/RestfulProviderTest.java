/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cxf.debug.client;

import com.mycompany.cxf.debug.model.Input;
import com.mycompany.cxf.debug.model.RequestModel;
import com.mycompany.cxf.debug.model.ServiceBusModel;
import java.util.concurrent.Future;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Module;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author helge
 */
@RunWith(ApplicationComposer.class)
public class RestfulProviderTest {

    public RestfulProviderTest() {
    }

    @Module
    @Classes(value = {RestfulProvider.class}, cdi = true)
    public WebApp app() {
        return new WebApp().contextRoot("");
    }

    @Inject
    private RestfulProvider restfulProvider;

    /**
     * Test of sendRequest method, of class RestfulProvider.
     */
    @Test
    public void testSendRequest() throws Exception {
        System.out.println("sendRequest");
        ServiceBusModel busModel = new ServiceBusModel();
        busModel.setDestinationHost("https://datametrixdev.service-now.com");
        busModel.setDestinationPath("/api/");

        RequestModel request = new RequestModel("app");
        request.getParameters().add(new Input("u_name", "3560c"));
        Future<Response> sendRequest = restfulProvider.sendRequest(busModel, request);
        Response get = sendRequest.get();
        System.out.println("Response: " + get.readEntity(String.class));
    }

}
