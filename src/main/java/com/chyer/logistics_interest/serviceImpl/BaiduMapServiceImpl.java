package com.chyer.logistics_interest.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.chyer.logistics_interest.service.BaiduMapService;
import com.chyer.logistics_interest.utils.HttpsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BaiduMapServiceImpl implements BaiduMapService {

    @Value("baidu.map.ak")
    private String baidu_ak;


    @Override
    public JSON administrationRegionSerch() {
        return null;
    }

    @Override
    public JSON circularRegionSerch() {
        return null;
    }

    @Override
    public JSON placeDetailInfo(String uid) {
        return null;
    }

    @Override
    public String ipLocation(String ip, String coor) {
        //http://api.map.baidu.com/location/ip?ak=您的AK&ip=您的IP&coor=bd09ll //HTTP协议
        StringBuilder sb = new StringBuilder("http://api.map.baidu.com/location/ip");
        sb.append("?ak=" + baidu_ak);
        if(StringUtils.isNotBlank(ip)){
            sb.append("&ip=" + ip);
        }
        if(StringUtils.isBlank(coor)){
            sb.append("&coor=" + coor);
        }

        String result = HttpsUtil.doGet(sb.toString());
        return result;
    }
}
