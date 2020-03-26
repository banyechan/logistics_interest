package com.chyer.logistics_interest.entity.baidumap;

import lombok.Data;

import java.io.Serializable;

/**
 *   百度地图所有请求响应的父类
 *     --所有响应结果共有的属性
 */
@Data
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = 6113213920177589565L;

    private Integer status; //返回结果状态值， 成功返回0，其他值请查看下方返回码状态表

    private String message; //状态值说明
}
