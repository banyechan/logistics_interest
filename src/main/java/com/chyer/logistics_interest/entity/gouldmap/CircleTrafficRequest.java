package com.chyer.logistics_interest.entity.gouldmap;

import java.io.Serializable;

public class CircleTrafficRequest implements Serializable {
    private static final long serialVersionUID = 9216077439369902454L;

    private String key;
    private String level;
    private String extensions;
    private String output= "JSON";
    private String sig;
    private String callback;
    private String location; //必填
    private String radius;

}