package com.chyer.logistics_interest.service;

import com.chyer.logistics_interest.entity.gouldmap.*;

public interface GouldMapService {

    //ip定位
    String ipLocation(String ip);

    //行政区划区域检索
    String districtSerch(DistrictSerchRequest record);

    //关键字搜索
    String keywordsSerch(KeywordsSerchRequest record);

    //周边搜索
    String aroundSerch(AroundSerchRequest record);

    //指定线路交通态势
    String roadTraffic(RoadTrafficRequest record);

    //圆形区域交通态势
    String circleTraffic(CircleTrafficRequest record);





    //地点详情检索服务
    String placeDetailInfo( );

}
