package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 此类 用于封装 <圆形区域交通态势> 所需的请求参数
 *     具体属性意义可参考官方文档
 */
@Data
@ToString
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
