package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DistrictSerchRequest implements Serializable {

    private String key;
    private String keywords;
    private String subdistrict;
    private String page;
    private String offset;
    private String extensions;
    private String filter;
    private String callback;
    private String output= "JSON";

}
