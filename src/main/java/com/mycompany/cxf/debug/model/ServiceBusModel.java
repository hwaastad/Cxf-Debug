/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cxf.debug.model;

/**
 *
 * @author helge
 */
public class ServiceBusModel {

    private String destinationHost;
    private String destinationPath;
    private String username;
    private String password;

    public ServiceBusModel() {
    }

    public ServiceBusModel(String destinationHost, String destinationPath, String username, String password) {
        this.destinationHost = destinationHost;
        this.destinationPath = destinationPath;
        this.username = username;
        this.password = password;
    }

    public String getDestinationHost() {
        return destinationHost;
    }

    public void setDestinationHost(String destinationHost) {
        this.destinationHost = destinationHost;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
