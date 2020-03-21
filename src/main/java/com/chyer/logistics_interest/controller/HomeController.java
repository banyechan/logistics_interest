package com.chyer.logistics_interest.controller;

import com.alibaba.fastjson.JSONObject;
import com.chyer.logistics_interest.service.BaiduMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private BaiduMapService baiduMapService;

    @RequestMapping("/index")
    public String welcome(){
        String result = "春江潮水连海平，海上明月共潮生。";
        return result;
    }

    @GetMapping
    public JSONObject aroundSerch(){


    return null;
    }






    //http://api.map.baidu.com/place/v2/search?query=ATM机&tag=银行&region=北京&output=json&ak=FIkaMovHOv5VAG1Ew9BbjKs8M6w7LvN7 //GET请求







}
