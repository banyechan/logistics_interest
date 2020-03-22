package com.chyer.logistics_interest.entity;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class PlaceDetailInfoResponse extends CommonResponse{
    private static final long serialVersionUID = 2667895990002232613L;

    private Result result;


    @Data
    private static class Result{
        private String uid;
        private String street_id;
        private String name;
        private Location location;
        private String address;
        private String province;
        private String city;
        private String area;
        private String detail;
        private DetailInfo detail_info;
    }

    @Data
    private static class Location{
        private String lat;
        private String lng;
    }

    @Data
    private static class DetailInfo {
        private Location navi_location;
        private String type;
        private String tag;
        private String detail_url;
        private String overall_rating;
        private String comment_num;
        private String image_num;
        private String[] alias;
    }

}
