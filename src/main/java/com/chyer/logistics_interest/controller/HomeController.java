package com.chyer.logistics_interest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {



    @RequestMapping("/index")
    public String welcome(){
        String result = "春江潮水连海平，海上明月共潮生。";
        return result;
    }





}
