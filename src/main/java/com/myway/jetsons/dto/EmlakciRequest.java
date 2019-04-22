package com.myway.jetsons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Ebru Göksal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmlakciRequest implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
