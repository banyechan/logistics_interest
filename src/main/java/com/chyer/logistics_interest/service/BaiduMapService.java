package com.chyer.logistics_interest.service;


import com.chyer.logistics_interest.entity.baidumap.AreaRegionSerchRequest;
import com.chyer.logistics_interest.entity.baidumap.CircularRegionSerchRequest;
import com.chyer.logistics_interest.entity.baidumap.PlaceDetailInfoRequest;

//百度地图的service接口
public interface BaiduMapService {

    //ip定位
    String ipLocation(String ip,String coor);

    //圆形区域检索
    String circularRegionSerch(CircularRegionSerchRequest record);

    //行政区划区域检索
    String areaRegionSerch(AreaRegionSerchRequest record);

    //地点详情检索服务
    String placeDetailInfo(PlaceDetailInfoRequest record);

}
