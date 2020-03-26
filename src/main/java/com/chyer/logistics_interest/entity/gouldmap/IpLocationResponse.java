package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

/**
 * 此类 用于封装 <IP定位> 返回的响应结果
 *     具体属性意义可参考官方文档
 */
@Data
@ToString
public class IpLocationResponse extends ParentResponse{
    private static final long serialVersionUID = 4711380518351706753L;

    private String province;
    private String adcode;
    private String city;
    private String rectangle;

}
