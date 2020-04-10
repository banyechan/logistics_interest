package com.chyer.logistics_interest.entity;

import java.util.Date;

public class GreenhouseMonitorData {
    private Integer id;

    private Integer greenhouseId;

    private Double airtemp;

    private Double airhumi;

    private Double soiltemp;

    private Double soilhumi;

    private Double co2;

    private Double sundata;

    private Date datetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(Integer greenhouseId) {
        this.greenhouseId = greenhouseId;
    }

    public Double getAirtemp() {
        return airtemp;
    }

    public void setAirtemp(Double airtemp) {
        this.airtemp = airtemp;
    }

    public Double getAirhumi() {
        return airhumi;
    }

    public void setAirhumi(Double airhumi) {
        this.airhumi = airhumi;
    }

    public Double getSoiltemp() {
        return soiltemp;
    }

    public void setSoiltemp(Double soiltemp) {
        this.soiltemp = soiltemp;
    }

    public Double getSoilhumi() {
        return soilhumi;
    }

    public void setSoilhumi(Double soilhumi) {
        this.soilhumi = soilhumi;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getSundata() {
        return sundata;
    }

    public void setSundata(Double sundata) {
        this.sundata = sundata;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}