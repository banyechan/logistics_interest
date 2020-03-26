package com.chyer.logistics_interest.entity.baidumap;

import lombok.Data;
import lombok.ToString;

/**
 * 此类 用于封装 <行政区划区域检索> 所需的请求参数
 *     具体属性意义可参考官方文档
 */
@Data
@ToString
public class AreaRegionSerchRequest extends CommonRequest{
    private static final long serialVersionUID = -920095142792379188L;

    private String query; //行政区划区域检索不支持多关键字检索
    private String tag;	//检索分类偏好，与q组合进行检索，多个分类以","分隔
    private String region; //必选
    private String city_limit;	//区域数据召回限制，为true时，仅召回region对应区域内数据。
    private String output; //输出格式为json或者xml可选
    private String scope; //检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息	string(50)	1、2	可选
    private String filter; //检索过滤条件
    private String coord_type; //坐标类型 默认bd09ll即百度经纬度坐标
    private String ret_coordtype;
    private String page_size;
    private String page_num;

}
