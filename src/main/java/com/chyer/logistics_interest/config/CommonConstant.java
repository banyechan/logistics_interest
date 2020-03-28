package com.chyer.logistics_interest.config;

import java.util.ArrayList;
import java.util.List;

/**
 *  公共常量（不会经常改变的量）
 *
 */
public class CommonConstant {

    //关键词
    public static List<String> keywordList(){
        List<String> keywordList = new ArrayList<String>();
        keywordList.add("邮政速递|物流速递|物流仓储场地");
        keywordList.add("火车站|火车检查站");
        keywordList.add("机场货运|货运火车站|货运港口码头");
        return keywordList;
    }

    //全国所有省的名字
    public static List<String> getCityList(){
        List<String> cityList = new ArrayList<String>();
        cityList.add("上海");
        cityList.add("北京");
        cityList.add("天津");
        cityList.add("河北");
        cityList.add("山西");
        cityList.add("内蒙古");
        cityList.add("辽宁");
        cityList.add("吉林");
        cityList.add("黑龙江");
        cityList.add("江苏");
        cityList.add("浙江");
        cityList.add("安徽");
        cityList.add("福建");
        cityList.add("江西");
        cityList.add("山东");
        cityList.add("河南");
        cityList.add("湖北");
        cityList.add("湖南");
        cityList.add("广东");
        cityList.add("广西");
        cityList.add("重庆");
        cityList.add("四川");
        cityList.add("贵州");
        cityList.add("云南");
        cityList.add("西藏");
        cityList.add("陕西");
        cityList.add("甘肃");
        cityList.add("青海");
        cityList.add("宁夏");
        cityList.add("新疆");
        cityList.add("海南");
        cityList.add("台湾");
        cityList.add("香港特别行政区");
        cityList.add("澳门");
        return cityList;
    }

}
