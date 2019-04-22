package com.myway.jetsons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Ebru Göksal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DanismanRequest implements Serializable {

    private String name;
    private Long ofisId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOfisId() {
        return ofisId;
    }

    public void setOfisId(Long ofisId) {
        this.ofisId = ofisId;
    }
}
