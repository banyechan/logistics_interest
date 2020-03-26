package com.chyer.logistics_interest.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *  此类 用于封装 热力图 所需的参数
 */
@Data
@ToString
public class HotPower implements Serializable {
    private static final long serialVersionUID = 3597658549147013346L;

    private String lng;
    private String lat;
    private Integer count;

}
