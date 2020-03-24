package com.chyer.logistics_interest.controller;

import com.alibaba.fastjson.JSON;
import com.chyer.logistics_interest.config.CommonConstant;
import com.chyer.logistics_interest.entity.HotPower;
import com.chyer.logistics_interest.entity.gouldmap.*;
import com.chyer.logistics_interest.service.GouldMapService;
import com.chyer.logistics_interest.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/gould")
public class GouldMapController {


    @Autowired
    private GouldMapService gouldMapService;

    //ip定位
    @GetMapping("/ip")
    public R ipLocation(@RequestParam("ip")String ip){
        String result = gouldMapService.ipLocation(ip);
        IpLocationResponse ipLocation = JSON.parseObject(result, IpLocationResponse.class);
        if(ipLocation.getStatus().equals(1)){
            return new R(ipLocation,"success");
        }

        return new R(ipLocation.getStatus(),new Throwable(ipLocation.getInfo()));
    }


    //行政区域查询
    @PostMapping("/districtSerch")
    public R districtSerch(@RequestBody DistrictSerchRequest record){
        String result = gouldMapService.districtSerch(record);
        DistrictSerchResponse districtSerch = JSON.parseObject(result, DistrictSerchResponse.class);
        if(districtSerch.getStatus().equals(1)){
            return new R(districtSerch,"success");
        }

        return new R(districtSerch.getStatus(),new Throwable(districtSerch.getInfo()));
    }

    //关键字搜索
    @PostMapping("/keywordsSerch")
    public R keywordsSerch(@RequestBody KeywordsSerchRequest record){
        String result = gouldMapService.keywordsSerch(record);
        KeywordsSerchResponse keywordsSerch = JSON.parseObject(result, KeywordsSerchResponse.class);
        if(keywordsSerch.getStatus().equals(1)){
            return new R(keywordsSerch,"success");
        }

        return new R(keywordsSerch.getStatus(),new Throwable(keywordsSerch.getInfo()));
    }

    //周边搜索
    @PostMapping("/aroundSerch")
    public R aroundSerch(@RequestBody AroundSerchRequest record){
        String result = gouldMapService.aroundSerch(record);
        KeywordsSerchResponse aroundSerch = JSON.parseObject(result, KeywordsSerchResponse.class);
        if(aroundSerch.getStatus().equals(1)){
            return new R(aroundSerch,"success");
        }

        return new R(aroundSerch.getStatus(),new Throwable(aroundSerch.getInfo()));
    }

    //指定线路交通态势
    @PostMapping("/roadTraffic")
    public R roadTraffic(@RequestBody RoadTrafficRequest record){
        String result = gouldMapService.roadTraffic(record);
        TrafficResponse roadTraffic = JSON.parseObject(result, TrafficResponse.class);
        if(roadTraffic.getStatus().equals(1)){
            return new R(roadTraffic,"success");
        }

        return new R(roadTraffic.getStatus(),new Throwable(roadTraffic.getInfo()));
    }

    //圆形区域交通态势
    @PostMapping("/circleTraffic")
    public R circleTraffic(@RequestBody CircleTrafficRequest record){
        String result = gouldMapService.circleTraffic(record);
        TrafficResponse circleTraffic = JSON.parseObject(result, TrafficResponse.class);
        if(circleTraffic.getStatus().equals(1)){
            return new R(circleTraffic,"success");
        }

        return new R(circleTraffic.getStatus(),new Throwable(circleTraffic.getInfo()));
    }


    //全国范围 关键字搜索
    @GetMapping("/countrySerch")
    public R countryKeywordsSerch(){
        List<String> citys = CommonConstant.getCityList();
        List<KeywordsSerchResponse.Poi> totalPois = new ArrayList<KeywordsSerchResponse.Poi>();
        KeywordsSerchRequest request = new KeywordsSerchRequest();
        request.setKeywords("物流点|物流仓储");
        request.setOutput("json");
        request.setOffset("20");
        request.setPage("1");
        for(String city : citys){
            request.setCity(city);
            String result = gouldMapService.keywordsSerch(request);
            KeywordsSerchResponse keywordsSerch = JSON.parseObject(result, KeywordsSerchResponse.class);
            if(keywordsSerch.getStatus().equals(1)){
                totalPois.addAll(keywordsSerch.getPois());
                log.info(city + "--- 查询出的记录总数=" + keywordsSerch.getPois().size());
            }
        }
        log.info("--- 查询出的记录总数=" + totalPois.size());


        return new R(totalPois);
    }


    //全国范围 关键字搜索  热力图值形式
    @GetMapping("/countrySerchHot")
    public R countrySerchHot(){
        List<String> citys = CommonConstant.getCityList();
        List<HotPower> totalHotPowerList = new ArrayList<HotPower>();
        KeywordsSerchRequest request = new KeywordsSerchRequest();
        request.setKeywords("物流点|物流仓储");
        request.setOutput("json");
        request.setOffset("20");
        request.setPage("1");
        for(String city : citys){
            request.setCity(city);
            String result = gouldMapService.keywordsSerch(request);
            KeywordsSerchResponse keywordsSerch = JSON.parseObject(result, KeywordsSerchResponse.class);
            if(keywordsSerch.getStatus().equals(1)){
                List<KeywordsSerchResponse.Poi> temPois = keywordsSerch.getPois();
                if(temPois != null && temPois.size() > 0){
                    for(KeywordsSerchResponse.Poi tem : temPois){
                        String location = tem.getLocation();
                        if(StringUtils.isNotBlank(location)){
                            String[] locaArr = location.split(",");
                            HotPower hotPower = new HotPower();
                            hotPower.setLng(locaArr[0]);
                            hotPower.setLat(locaArr[1]);
                            hotPower.setCount(0);
                            totalHotPowerList.add(hotPower);
                        }


                    }
                }
                log.info(city + "--- 查询出的记录总数=" + keywordsSerch.getPois().size());
            }
        }
        log.info("--- 查询出的记录总数=" + totalHotPowerList.size());


        return new R(totalHotPowerList);
    }







    //某点热力值查询
    @GetMapping("/hotValue")
    public R hotValue(@RequestParam("ip")String ip){
        String result = gouldMapService.ipLocation(ip);
        IpLocationResponse ipLocation = JSON.parseObject(result, IpLocationResponse.class);
        if(ipLocation.getStatus().equals(1)){
            return new R(ipLocation,"success");
        }

        return new R(ipLocation.getStatus(),new Throwable(ipLocation.getInfo()));
    }

}
