package com.chyer.logistics_interest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {


    @Value("${baidu.map.ak}")
    private String baidu_ak;

    @Value("${gould.map.key}")
    private String gaode_key;

    //项目初建时，测试用例，可删
    @RequestMapping("/index")
    public String welcome(){
        String result = "春江潮水连海平，海上明月共潮生。";
        System.out.println("--- baidu_ak=" + baidu_ak);
        System.out.println("--- gaode_key=" + gaode_key);
        return result;
    }

}
