package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TrafficResponse extends ParentResponse{


    private Trafficinfo trafficinfo;

    @Data
    private static class Trafficinfo{
        private String description;
        private Evaluation evaluation;
        private List<Road> roads;
    }

    @Data
    private static class Evaluation{
        private String expedite;
        private String congested;
        private String blocked;
        private String unknown;
        private String status;
        private String description;

    }

    @Data
    private static class Road{
        private String name;
        private String status;
        private String direction;
        private String angle;
        private String speed;
        private String lcodes;
        private String polyline;

    }

}
