package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 此类 用于封装 <关键字搜索> 返回的响应结果
 *     具体属性意义可参考官方文档
 */
@Data
@ToString
public class KeywordsSerchResponse extends ParentResponse{
    private static final long serialVersionUID = 5284973274992310405L;

    private String count;
    private Suggestion suggestion;
    private SugAddress sub_address;
    private List<Poi> pois;


    @Data
    private static class Suggestion{
        private String[] keywords;
        private String[] cites;
    }

    @Data
    private static class SugAddress{
        private String name;
        private String address;
        private String location;
        private String country;
        private String pname;
        private String cityname;
        private String adcode;
        private String adname;
        private String district;
        private String aoi;
        private String street;
    }

    @Data
    public static class Poi{
        private String id;
        private String parent;
        private String name;
        private String type;
        private String typecode;
        private String biz_type;
        private String address;
        private String location;
        private String distance;
        private String tel;
        private String citycode;
        private String cityname;

    }

}
