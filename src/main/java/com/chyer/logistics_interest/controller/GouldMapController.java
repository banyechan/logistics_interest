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
import java.util.concurrent.CountDownLatch;

/**
 *  高德地图的接口调式 controller
 *   说明：每个接口的具体功能介绍可参考高德地图的官方文档
 *   url: https://lbs.amap.com/api/webservice/summary
 */
@Slf4j
@RestController
@RequestMapping("/gould")
public class GouldMapController {

    @Autowired
    private GouldMapService gouldMapService;

    //ip定位 （根据用户输入的IP地址，能够快速的帮用户定位IP的所在位置）
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
    // 根据用户输入的搜索条件可以帮助用户快速的查找特定的行政区域信息
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
    //  通过用POI的关键字进行条件搜索
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
    //  在用户传入经纬度坐标点附近，在设定的范围内，按照关键字或POI类型搜索
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
    // 根据用户输入的内容能够返回希望查询的交通态势情况
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
    // 根据用户输入的内容能够返回希望查询的交通态势情况,，路况信息2分钟更新一次
    @PostMapping("/circleTraffic")
    public R circleTraffic(@RequestBody CircleTrafficRequest record){
        String result = gouldMapService.circleTraffic(record);
        TrafficResponse circleTraffic = JSON.parseObject(result, TrafficResponse.class);
        if(circleTraffic.getStatus().equals(1)){
            return new R(circleTraffic,"success");
        }

        return new R(circleTraffic.getStatus(),new Throwable(circleTraffic.getInfo()));
    }


    //全国范围 关键字搜索      单线程处理   每个省份返回20条数据
//    @GetMapping("/countrySerch")
//    public R singleCountryKeywordsSerch(){
//        String keyword = CommonConstant.getKeyword();
//        List<String> citys = CommonConstant.getCityList();
//        List<KeywordsSerchResponse.Poi> totalPois = new ArrayList<KeywordsSerchResponse.Poi>();
//        KeywordsSerchRequest request = new KeywordsSerchRequest();
//        request.setKeywords(keyword);
//        request.setOutput("json");
//        request.setOffset("20");
//        request.setPage("1");
//        for(String city : citys){
//            request.setCity(city);
//            String result = gouldMapService.keywordsSerch(request);
//            KeywordsSerchResponse keywordsSerch = JSON.parseObject(result, KeywordsSerchResponse.class);
//            if(keywordsSerch.getStatus().equals(1)){
//                totalPois.addAll(keywordsSerch.getPois());
//                log.info(city + "--- 查询出的记录总数=" + keywordsSerch.getPois().size());
//            }
//        }
//        log.info("--- 查询出的记录总数=" + totalPois.size());
//        return new R(totalPois);
//    }

    /**
     * 全国范围 关键字搜索
     * <多线程处理>
     * 因关键词拼接在一起查询不合理，故把关键字进行分类.现分三类，开三个线程，每个线程查一类关键字，
     * 把多线程的结果汇总以后，返回前端
     * 逻辑为： 每个省份，每一类关键词 返回20条数据，即每个省份共返回60条数据
     * 技术点： CountDownLatch  可以查一下相关知识
     */
    @GetMapping("/countrySerch")
    public R countrySerchMutiThread() {
        long startTime = System.currentTimeMillis();
        List<String> keywords = CommonConstant.keywordList();
        List<String> citys = CommonConstant.getCityList();
        List<KeywordsSerchResponse.Poi> totalPois = new ArrayList<KeywordsSerchResponse.Poi>();
        final CountDownLatch countDownLatch = new CountDownLatch(3); //开3个线程

        for (int i = 0; i < 3; i++) {
            final int num = i;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String temKey = keywords.get(num);
                        KeywordsSerchRequest request = new KeywordsSerchRequest();
                        request.setOutput("json");
                        request.setOffset("20");
                        request.setPage("1");
                        request.setKeywords(temKey);
                        for (String city : citys) {
                            request.setCity(city);
                            String result = gouldMapService.keywordsSerch(request);
                            KeywordsSerchResponse keywordsSerch = JSON.parseObject(result, KeywordsSerchResponse.class);
                            if (keywordsSerch.getStatus().equals(1)) {
                                totalPois.addAll(keywordsSerch.getPois());
                                log.info(city + "--- 查询出的记录总数=" + keywordsSerch.getPois().size());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("--- 查询出的记录总数=" + totalPois.size());
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("--- 运行总时间=" + totalTime);
        return new R(totalPois);
    }


    //全国范围 关键字搜索  热力图值形式
//    @GetMapping("/countrySerchHot")
//    public R singleCountrySerchHot(){
//        String keyword = CommonConstant.getKeyword();
//        List<String> citys = CommonConstant.getCityList();
//        List<HotPower> totalHotPowerList = new ArrayList<HotPower>();
//        KeywordsSerchRequest request = new KeywordsSerchRequest();
//        request.setKeywords(keyword);
//        request.setOutput("json");
//        request.setOffset("20");
//        request.setPage("1");
//        for(String city : citys){
//            request.setCity(city);
//            String result = gouldMapService.keywordsSerch(request);
//            KeywordsSerchResponse keywordsSerch = JSON.parseObject(result, KeywordsSerchResponse.class);
//            if(keywordsSerch.getStatus().equals(1)){
//                List<KeywordsSerchResponse.Poi> temPois = keywordsSerch.getPois();
//                if(temPois != null && temPois.size() > 0){
//                    for(KeywordsSerchResponse.Poi tem : temPois){
//                        String location = tem.getLocation();
//                        if(StringUtils.isNotBlank(location)){
//                            String[] locaArr = location.split(",");
//                            HotPower hotPower = new HotPower();
//                            hotPower.setLng(locaArr[0]);
//                            hotPower.setLat(locaArr[1]);
//                            hotPower.setCount(0);
//                            totalHotPowerList.add(hotPower);
//                        }
//
//
//                    }
//                }
//                log.info(city + "--- 查询出的记录总数=" + keywordsSerch.getPois().size());
//            }
//        }
//        log.info("--- 查询出的记录总数=" + totalHotPowerList.size());
//        return new R(totalHotPowerList);
//    }


    /**
     * 全国范围 关键字搜索   热力图值形式
     * <多线程处理>
     * 因关键词拼接在一起查询不合理，故把关键字进行分类.现分三类，开三个线程，每个线程查一类关键字，
     * 把多线程的结果汇总以后，返回前端
     * 逻辑为： 每个省份，每一类关键词 返回20条数据，即每个省份共返回60条数据
     */
    @GetMapping("/countrySerchHot")
    public R testMutiSerchHot() {
        long startTime = System.currentTimeMillis();
        List<String> citys = CommonConstant.getCityList();
        List<String> keywords = CommonConstant.keywordList();
        List<HotPower> totalHotPowerList = new ArrayList<HotPower>();
        final CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            final int num = i;
            new Thread(new Runnable() {
                public void run() {
                    long startTime4Thread = System.currentTimeMillis();
                    try {
                        String temKey = keywords.get(num);
                        KeywordsSerchRequest request = new KeywordsSerchRequest();
                        request.setOutput("json");
                        request.setOffset("20");
                        request.setPage("1");
                        request.setKeywords(temKey);
                        for (String city : citys) {
                            request.setCity(city);
                            String result = gouldMapService.keywordsSerch(request);
                            KeywordsSerchResponse keywordsSerch = JSON.parseObject(result, KeywordsSerchResponse.class);
                            if (keywordsSerch.getStatus().equals(1)) {
                                List<KeywordsSerchResponse.Poi> temPois = keywordsSerch.getPois();
                                if (temPois != null && temPois.size() > 0) {
                                    for (KeywordsSerchResponse.Poi tem : temPois) {
                                        String location = tem.getLocation();
                                        if (StringUtils.isNotBlank(location)) {
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("--- 查询出的记录总数=" + totalHotPowerList.size());
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("--- 运行总时间=" + totalTime);
        return new R(totalHotPowerList);
    }

    //ID查询
    //  通过POI ID，查询某个POI详情
    @GetMapping("/placeInfo")
    public R placeInfo(@RequestParam("id")String id){
        String result = gouldMapService.placeDetailInfo(id);
        log.info("--- result = " + result);
        PlaceInfoResponse detailInfo = JSON.parseObject(result, PlaceInfoResponse.class);
        log.info("--- detailInfo = " + detailInfo.toString());
        if(detailInfo.getStatus().equals(1)){
            return new R(detailInfo,"success");
        }

        return new R(detailInfo.getStatus(),new Throwable(detailInfo.getInfo()));
    }

}
