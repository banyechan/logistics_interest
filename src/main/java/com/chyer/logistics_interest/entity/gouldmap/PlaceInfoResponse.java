package com.chyer.logistics_interest.entity.gouldmap;

import lombok.Data;
import lombok.ToString;

import java.util.List;
/**
 * 此类 用于封装 <ID查询> 返回的响应结果
 *     -具体属性意义可参考官方文档
 *     -注释掉的属性为前端不需要属性，故注释，若需要可放开
 */
@Data
@ToString
public class PlaceInfoResponse extends ParentResponse{
    private static final long serialVersionUID = -6707733061546895386L;

    private String count;
    private List<Poi> pois;

    @Data
    public static class Poi{
        private String id;
        //private String[] parent;
        private String name;
        private String type;
        private String typecode;
        // String[] biz_type;
        private String address;
        private String location;
        //private String[] distance;
        private String tel;
        private String citycode;
        private String cityname;
        private String pcode;
        //private String[] importance;
        //private Biz_Ext biz_ext;
        //private String recommend;
        //private List<Photo> photos;
        private String discount_num;
        //private String gridcode;
        //private String shopinfo;
        //private String[] poiweight;
        //private Deepinfo deep_info;
        private String adname;
        //private String[] indoor_src;
        //private String[] children;
        private String[] tag;
        private String[] event;
        //private String entr_location;
        private String indoor_map;
        private String[] email;
        //private String timestamp;
        private String website;
        //private String adcode;
        private String pname;
        //private String[] postcode;
        private String match;
        private String[] business_area;
        //private IndoorData indoor_data;
        //private String[] childtype;
        //private RichContent rich_content;
        //private String[] exit_location;
       // private String[] shopid;
        //private String navi_poiid;
        //private String groupbuy_num;
    }


    @Data
    private static class Biz_Ext{
        private String[] rating;
        private String[] cost;
    }

    @Data
    private static class Photo{
        private String[] title;
        private String url;
    }

    @Data
    private static class Deepinfo{
        private String[] deepsrc;
    }

    @Data
    private static class IndoorData{
        private String[] cmsid;
        private String[] truefloor;
        private String[] cpid;
        private String[] floor;
    }

    @Data
    private static class RichContent{
        private String[] discounts;
        private String[] review;
    }





}
