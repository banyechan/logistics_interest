package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 此类 用于封装 <关键字搜索> 所需的请求参数
 *     具体属性意义可参考官方文档
 */
@Data
@ToString
public class KeywordsSerchRequest implements Serializable {
    private static final long serialVersionUID = -1808442496329236204L;

    private String key;
    private String keywords;
    private String types;
    private String city;
    //private boolean citylimit;
    private String children;
    private String offset; //每页记录数据
    private String page;
    private String output= "JSON";
    private String sig;
    private String extensions;
    private String callback;
}
