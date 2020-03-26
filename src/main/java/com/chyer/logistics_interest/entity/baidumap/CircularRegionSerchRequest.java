package com.chyer.logistics_interest.entity.baidumap;

import lombok.Data;

/**
 * 此类 用于封装 <圆形区域检索> 所需的请求参数
 *     具体属性意义可参考官方文档
 */
@Data
public class CircularRegionSerchRequest extends CommonRequest {
    private static final long serialVersionUID = 6254765400694795192L;

    private String query; //支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索
    private String tag;	//检索分类偏好，与q组合进行检索，多个分类以","分隔
    private String location; //圆形区域检索中心点，不支持多个点 lat<纬度>,lng<经度>	eg:38.76623,116.43213
    private String radius;	//圆形区域检索半径，单位为米。(当半径过大，超过中心点所在城市边界时，会变为城市范围检索，检索范围为中心点所在城市）	string(50)	1000（默认）	可选
    private String radius_limit; //是否严格限定召回结果在设置检索半径范围内。true（是），false（否）。设置为true时会影响返回结果中total准确性及每页召回poi数量，我们会逐步解决此类问题。
    private String output; //输出格式为json或者xml可选
    private String scope; //检索结果详细程度。取值为1 或空，则返回基本信息；取值为2，返回检索POI详细信息	string(50)	1、2	可选
    private String filter; //检索过滤条件
    private String coord_type; //坐标类型 默认bd09ll即百度经纬度坐标
    private String ret_coordtype;
    private String page_size;
    private String page_num;

}
