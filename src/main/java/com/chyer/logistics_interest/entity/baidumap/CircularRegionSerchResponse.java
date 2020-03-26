package com.chyer.logistics_interest.entity.baidumap;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 此类 用于封装 <圆形区域检索> 返回的响应结果
 *     具体属性意义可参考官方文档
 */
@Data
@ToString
public class CircularRegionSerchResponse extends CommonResponse{
    private static final long serialVersionUID = -1504010488763444757L;

    private List<Result> results;

    @Data
    private static class Result{
        private String name;
        private Location location;
        private String address;
        private String province;
        private String city;
        private String area;
        private String street_id;
        private String detail;
        private String uid;
        private DetailInfo detail_info;
    }

    @Data
    private static class Location{
        private String lat;
        private String lng;
    }

    @Data
    private static class DetailInfo {
        private int distance;
        private String type;
        private String tag;
        private String detail_url;
        private List<Children> children;

    }

    @Data
    private static class Children{
        private String uid;
        private String name;
        private String show_name;
        private String tag;
        private Location location;
        private String address;
    }

}
