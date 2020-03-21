package com.chyer.logistics_interest.service;

import com.alibaba.fastjson.JSON;

public interface BaiduMapService {


    //行政区划区域检索
    JSON administrationRegionSerch();

    //圆形区域检索
    JSON circularRegionSerch();

    //地点详情检索服务
    JSON placeDetailInfo(String uid);

    //ip定位
    String ipLocation(String ip,String coor);

}
