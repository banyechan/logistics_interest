package com.chyer.logistics_interest.serviceImpl;


import com.chyer.logistics_interest.entity.baidumap.AreaRegionSerchRequest;
import com.chyer.logistics_interest.entity.baidumap.CircularRegionSerchRequest;
import com.chyer.logistics_interest.entity.baidumap.PlaceDetailInfoRequest;
import com.chyer.logistics_interest.service.BaiduMapService;
import com.chyer.logistics_interest.utils.HttpsUtil;
import com.chyer.logistics_interest.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
@Service
public class BaiduMapServiceImpl implements BaiduMapService {

    //@Value("baidu.map.ak")
    //private String baidu_ak;

    private final String BAIDU_AK = "FIkaMovHOv5VAG1Ew9BbjKs8M6w7LvN7";

    @Override
    public String ipLocation(String ip, String coor) {
        //http://api.map.baidu.com/location/ip?ak=您的AK&ip=您的IP&coor=bd09ll //HTTP协议
        StringBuilder sb = new StringBuilder("http://api.map.baidu.com/location/ip");
        sb.append("?ak=" + BAIDU_AK);
        if(StringUtils.isNotBlank(ip)){
            sb.append("&ip=" + ip);
        }
        if(StringUtils.isBlank(coor)){
            sb.append("&coor=" + coor);
        }

        String result = HttpsUtil.doGet(sb.toString());
        return result;
    }

    /**
     * http://api.map.baidu.com/place/v2/search?query=银行
     *          &location=39.915,116.404&radius=2000&output=xml
     *          &ak=您的密钥 //GET请求
     * @param record
     * @return
     */
    @Override
    public String circularRegionSerch(CircularRegionSerchRequest record) {
        StringBuilder sb = new StringBuilder("http://api.map.baidu.com/place/v2/search");
        sb.append("?ak=" + BAIDU_AK);
        String parameterUrl = ObjectUtil.getParameterUrl(record);
        sb.append(parameterUrl);
        return HttpsUtil.doGet(sb.toString());
    }

    /**
     * http://api.map.baidu.com/place/v2/search?query=ATM机
     *  &tag=银行&region=北京&output=json&ak=您的ak //GET请求
     * @param record
     * @return
     */
    @Override
    public String areaRegionSerch(AreaRegionSerchRequest record) {
        StringBuilder sb = new StringBuilder("http://api.map.baidu.com/place/v2/search");
        sb.append("?ak=" + BAIDU_AK);
        String parameterUrl = ObjectUtil.getParameterUrl(record);
        sb.append(parameterUrl);
        return HttpsUtil.doGet(sb.toString());
    }

    /**
     * http://api.map.baidu.com/place/v2/detail?uid=435d7aea036e54355abbbcc8
     *  &output=json&scope=2&ak=您的密钥 //GET请求
     * @param record
     * @return
     */
    @Override
    public String placeDetailInfo(PlaceDetailInfoRequest record) {
        StringBuilder sb = new StringBuilder("http://api.map.baidu.com/place/v2/detail");
        sb.append("?ak=" + BAIDU_AK);
        String parameterUrl = ObjectUtil.getParameterUrl(record);
        sb.append(parameterUrl);
        return HttpsUtil.doGet(sb.toString());
    }

}
