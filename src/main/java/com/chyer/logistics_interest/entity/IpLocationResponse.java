package com.chyer.logistics_interest.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class IpLocationResponse extends CommonResponse{
    private static final long serialVersionUID = -5141971115763875957L;

    private String address;
    private Content content;

    @Data
    private static class Content{
        private String address;
        private Point point;
        private AddressDetail address_detail;
    }

    @Data
    private static class Point{
        private String x;
        private String y;
    }

    @Data
    private static class AddressDetail{
        private String city;
        private String city_code;
        private String district;
        private String province;
        private String street_number;
        private String street;
    }
}
