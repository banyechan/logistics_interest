package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 此类 用于封装 <行政区划区域检索> 所需的请求参数
 *     具体属性意义可参考官方文档
 */
@Data
@ToString
public class DistrictSerchRequest implements Serializable {
    private static final long serialVersionUID = -6348516788607808624L;

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
