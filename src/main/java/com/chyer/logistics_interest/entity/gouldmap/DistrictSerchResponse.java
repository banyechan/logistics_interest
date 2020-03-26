package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 此类 用于封装 <行政区划区域检索> 返回的响应结果
 *     具体属性意义可参考官方文档
 */
@Data
@ToString
public class DistrictSerchResponse extends ParentResponse {
    private static final long serialVersionUID = 5878466621191560272L;

    private String count;
    private Suggestion suggestion;
    private List<District> districts;



    @Data
    private static class Suggestion{
        private String[] keywords;
        private String[] cites;
    }

    @Data
    private static class District{
        private String citycode;
        private String adcode;
        private String name;
        private String polyline;
        private String center;
        private String level;

        private List<District> districts;
    }




}
