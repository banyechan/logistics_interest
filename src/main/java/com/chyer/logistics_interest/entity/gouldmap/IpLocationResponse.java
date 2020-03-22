package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class IpLocationResponse extends ParentResponse{
    private static final long serialVersionUID = 4711380518351706753L;

    private String province;
    private String adcode;
    private String city;
    private String rectangle;

}
