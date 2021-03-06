package com.chyer.logistics_interest.controller;


import com.alibaba.fastjson.JSON;
import com.chyer.logistics_interest.entity.baidumap.*;
import com.chyer.logistics_interest.service.BaiduMapService;
import com.chyer.logistics_interest.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  百度地图的接口调式的 controller
 *  说明：每个接口的具体功能介绍可参考百度地图的官方文档
 *   url: http://lbsyun.baidu.com/index.php?title=webapi
 */
@Slf4j
@RestController
@RequestMapping("/baidu")
public class BaiDuMapController {

    @Autowired
    private BaiduMapService baiduMapService;

    //ip定位
    @GetMapping("ip")
    public R ipLocation(@RequestParam("ip")String ip,@RequestParam(value = "coor", required = false)String coor){
        String result = baiduMapService.ipLocation(ip,coor);
        IpLocationResponse ipLoction = JSON.parseObject(result, IpLocationResponse.class);
        if(ipLoction.getStatus().equals(0)){
            return new R(ipLoction,"success");
        }

        return new R(ipLoction.getStatus(),new Throwable(ipLoction.getMessage()));
    }

    //圆形区域检索
    @PostMapping("/aroundSerch")
    public R aroundSerch(@RequestBody CircularRegionSerchRequest circularRegionSerchRequest){
        String result = baiduMapService.circularRegionSerch(circularRegionSerchRequest);
        CircularRegionSerchResponse circularRegion = JSON.parseObject(result, CircularRegionSerchResponse.class);
        if(circularRegion.getStatus().equals(0)){
            return new R(circularRegion,"success");
        }

        return new R(circularRegion.getStatus(),new Throwable(circularRegion.getMessage()));
    }

    //行政区划区域检索
    @PostMapping("/areaSerch")
    public R areaRegionSerch(@RequestBody AreaRegionSerchRequest areaRegionSerchRequest){
        String result = baiduMapService.areaRegionSerch(areaRegionSerchRequest);
        CircularRegionSerchResponse areaRegion = JSON.parseObject(result, CircularRegionSerchResponse.class);
        if(areaRegion.getStatus().equals(0)){
            return new R(areaRegion,"success");
        }

        return new R(areaRegion.getStatus(),new Throwable(areaRegion.getMessage()));
    }

    //地点详情检索
    @PostMapping("/placeDetailInfo")
    public R placeDetailInfo(@RequestBody PlaceDetailInfoRequest placeDetailInfoRequest){
        String result = baiduMapService.placeDetailInfo(placeDetailInfoRequest);
        PlaceDetailInfoResponse detailInfo = JSON.parseObject(result, PlaceDetailInfoResponse.class);
        if(detailInfo.getStatus().equals(0)){
            return new R(detailInfo,"success");
        }

        return new R(detailInfo.getStatus(),new Throwable(detailInfo.getMessage()));
    }


}
