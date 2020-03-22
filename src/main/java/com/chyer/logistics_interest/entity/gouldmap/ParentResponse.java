package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ParentResponse implements Serializable {
    private static final long serialVersionUID = 1936046332952083243L;

    private Integer status;
    private String info;
    private String infocode;

}
