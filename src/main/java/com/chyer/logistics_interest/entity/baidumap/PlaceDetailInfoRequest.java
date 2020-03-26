package com.chyer.logistics_interest.entity.baidumap;

import lombok.Data;
import lombok.ToString;

/**
 * 此类 用于封装 <地点详情检索服务> 所需的请求参数
 *     具体属性意义可参考官方文档
 */
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
