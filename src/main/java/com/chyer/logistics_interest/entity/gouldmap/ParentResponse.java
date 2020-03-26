package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *   高德地图所有请求响应的父类
 *     --所有响应结果共有的属性
 */
@Data
@ToString
public class ParentResponse implements Serializable {
    private static final long serialVersionUID = 1936046332952083243L;

    private Integer status;
    private String info;
    private String infocode;

}
