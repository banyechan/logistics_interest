package com.chyer.logistics_interest.serviceImpl;

import com.chyer.logistics_interest.entity.gouldmap.*;
import com.chyer.logistics_interest.service.GouldMapService;
import com.chyer.logistics_interest.utils.HttpsUtil;
import com.chyer.logistics_interest.utils.ObjectUtil;
import org.springframework.stereotype.Service;

@Service
public class GouldMapServiceImpl implements GouldMapService {

    private final String GOULD_KEY = "d73af80e885c11bd5bf046a0e612225e";

    /**
     * https://restapi.amap.com/v3/ip?parameters
     * 请求方式  GET
     * @param ip
     * @return
     */
    @Override
    public String ipLocation(String ip) {
        StringBuilder sb = new StringBuilder("https://restapi.amap.com/v3/ip");
        sb.append("?key=" + GOULD_KEY);
        sb.append("&output=json");
        sb.append("&ip=" + ip);
        return HttpsUtil.doGet(sb.toString());
    }

    /**
     * https://restapi.amap.com/v3/config/district?keywords=北京
     *  &subdistrict=2&key=<用户的key>
     * @param record
     * @return
     */
    @Override
    public String districtSerch(DistrictSerchRequest record) {
        StringBuilder sb = new StringBuilder("https://restapi.amap.com/v3/config/district");
        sb.append("?key=" + GOULD_KEY);
        String parameterUrl = ObjectUtil.getParameterUrl(record);
        sb.append(parameterUrl);
        return HttpsUtil.doGet(sb.toString());
    }

    /**
     * https://restapi.amap.com/v3/place/text?parameters
     * @param record
     * @return
     */
    @Override
    public String keywordsSerch(KeywordsSerchRequest record) {
        StringBuilder sb = new StringBuilder("https://restapi.amap.com/v3/place/text");
        sb.append("?key=" + GOULD_KEY);
        sb.append("&citylimit=true");
        String parameterUrl = ObjectUtil.getParameterUrl(record);
        sb.append(parameterUrl);
        return HttpsUtil.doGet(sb.toString());
    }

    /**
     * https://restapi.amap.com/v3/place/around?parameters
     * 请求方式  GET
     * @param record
     * @return
     */
    @Override
    public String aroundSerch(AroundSerchRequest record) {
        StringBuilder sb = new StringBuilder("https://restapi.amap.com/v3/place/around");
        sb.append("?key=" + GOULD_KEY);
        String parameterUrl = ObjectUtil.getParameterUrl(record);
        sb.append(parameterUrl);
        return HttpsUtil.doGet(sb.toString());
    }


    /**
     *https://restapi.amap.com/v3/traffic/status/road?parameters
     * 请求方式  GET
     * @param record
     * @return
     */
    @Override
    public String roadTraffic(RoadTrafficRequest record) {
        StringBuilder sb = new StringBuilder("https://restapi.amap.com/v3/traffic/status/road");
        sb.append("?key=" + GOULD_KEY);
        String parameterUrl = ObjectUtil.getParameterUrl(record);
        sb.append(parameterUrl);
        return HttpsUtil.doGet(sb.toString());
    }

    /**
     * https://restapi.amap.com/v3/traffic/status/circle?parameters
     *  请求方式  GET
     * @param record
     * @return
     */
    @Override
    public String circleTraffic(CircleTrafficRequest record) {
        StringBuilder sb = new StringBuilder("https://restapi.amap.com/v3/traffic/status/circle");
        sb.append("?key=" + GOULD_KEY);
        String parameterUrl = ObjectUtil.getParameterUrl(record);
        sb.append(parameterUrl);
        return HttpsUtil.doGet(sb.toString());
    }

    /**
     *  https://restapi.amap.com/v3/place/detail?parameters
     *  请求方式  GET
     * @param id
     * @return
     */
    @Override
    public String placeDetailInfo(String id) {
        StringBuilder sb = new StringBuilder("https://restapi.amap.com/v3/place/detail");
        sb.append("?key=" + GOULD_KEY);
        sb.append("&id=").append(id);
        sb.append("&output=json");
        return HttpsUtil.doGet(sb.toString());
    }
}
