package com.chyer.logistics_interest.controller;

import com.chyer.logistics_interest.service.GouldMapService;
import com.chyer.logistics_interest.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gould")
public class GouldMapController {


    @Autowired
    private GouldMapService gouldMapService;

    @RequestMapping("/index")
    public String welcome(){
        String result = "春江潮水连海平，海上明月共潮生。";
        return result;
    }

    //ip定位
    @GetMapping("ip")
    public R ipLocation(@RequestParam("ip")String ip, @RequestParam(value = "coor", required = false)String coor){

        return new R();
    }





}
