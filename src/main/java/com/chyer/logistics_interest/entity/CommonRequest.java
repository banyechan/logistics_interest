package com.chyer.logistics_interest.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonRequest implements Serializable {
    private static final long serialVersionUID = 8679913747176504096L;

    private String ak;
    private String sn;
    private String timestamp;//设置sn后该值必填。
}
