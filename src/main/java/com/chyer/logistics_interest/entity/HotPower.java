package com.chyer.logistics_interest.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class HotPower implements Serializable {
    private static final long serialVersionUID = 3597658549147013346L;

    private String lng;
    private String lat;
    private Integer count;

}
