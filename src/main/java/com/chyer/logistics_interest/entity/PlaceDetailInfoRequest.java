package com.chyer.logistics_interest.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlaceDetailInfoRequest extends CommonRequest{
    private static final long serialVersionUID = 8989355642109035122L;

    private String uid;
    private String uids;
    private String ret_coordtype;
    private String output;
    private String scope;

}
