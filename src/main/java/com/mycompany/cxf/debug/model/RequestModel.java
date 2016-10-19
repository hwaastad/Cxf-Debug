/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cxf.debug.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helge
 */
public class RequestModel {

    private String application;
    private List<Input> parameters = new ArrayList<>();

    public RequestModel(String application) {
        this.application = application;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public List<Input> getParameters() {
        return parameters;
    }

    public void setParameters(List<Input> parameters) {
        this.parameters = parameters;
    }

}
