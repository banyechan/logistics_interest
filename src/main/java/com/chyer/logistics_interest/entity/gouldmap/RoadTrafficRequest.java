package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 此类 用于封装 <指定线路交通态势> 所需的请求参数
 *     具体属性意义可参考官方文档
 */
@Data
@ToString
public class RoadTrafficRequest implements Serializable {
    private static final long serialVersionUID = 2603953288145462716L;

    private String key;
    private String level;
    private String extensions;
    private String output= "JSON";
    private String sig;
    private String callback;
    private String name; //必填
    private String adcode;
    private String city;

}
