package com.chyer.logistics_interest.entity.baidumap;

import lombok.Data;

import java.io.Serializable;

/**
 * 此类 用于封装 百度接口 所需的公共请求参数
 *     具体属性意义可参考官方文档
 */
@Data
public class CommonRequest implements Serializable {
    private static final long serialVersionUID = 8679913747176504096L;

    private String ak;
    private String sn;
    private String timestamp;//设置sn后该值必填。
}
