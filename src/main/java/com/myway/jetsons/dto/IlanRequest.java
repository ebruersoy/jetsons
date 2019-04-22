package com.myway.jetsons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Ebru Göksal
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IlanRequest implements Serializable {

    private String detail;
    private Long danismanId;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getDanismanId() {
        return danismanId;
    }

    public void setDanismanId(Long danismanId) {
        this.danismanId = danismanId;
    }
}
