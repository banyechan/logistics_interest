package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AroundSerchRequest implements Serializable {
    private static final long serialVersionUID = 6946295993629778308L;

    private String key;
    private String keywords;
    private String location;
    private String types;
    private String city;
    private String radius;
    private String sortrule="distance";
    private String offset; //每页记录数据
    private String page;
    private String output= "JSON";
    private String sig;
    private String extensions;
    private String callback;

}
