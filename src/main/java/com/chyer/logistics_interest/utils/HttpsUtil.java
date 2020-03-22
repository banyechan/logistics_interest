package com.chyer.logistics_interest.utils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class HttpsUtil {

    /**
     * get提交方式
     */
    public static  String doGet(String urlString){
        String result=null;
        log.info("----- HttpsUtil.doGet 请求地址为=" + urlString);
        try {
            CloseableHttpClient httpClient= HttpClients.createDefault();
            //创建GET对象
            HttpGet httpGet = new HttpGet(urlString);
            httpGet.addHeader("Content-type","application/json; charset=utf-8");
            httpGet.setHeader("Accept", "application/json");

            //执行请求
            CloseableHttpResponse  response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200) {
                HttpEntity entity = response.getEntity();
                //所需内容都在entity里面，这里可以对数据操作
                String detail = EntityUtils.toString(entity,"utf-8");
                result = String.valueOf(JSON.parse(detail));
                log.info("---- result=" + result);
            }
            response.close();
            httpClient.close();
        } catch (Exception e) {
            log.info("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * post提交方式
     */
    public static  String doPost(String urlString,String postData){
        String result=null;
        try {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost requestPost=new HttpPost(urlString);
            requestPost.addHeader("Content-type","application/json; charset=utf-8");
            requestPost.setHeader("Accept", "application/json");

            requestPost.setEntity(new StringEntity(postData,"UTF-8"));
            HttpResponse response = httpClient.execute(requestPost);

            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity entity = response.getEntity();
                result= EntityUtils.toString(entity,"utf-8");
            }
        } catch (Exception e) {
            log.info("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }


//    public static String createJumpHtml(TradeBean data, String charset,String url) throws Exception {
//        StringBuffer jumpHtml = new StringBuffer("");
//        Map<String,Object> map = new HashMap<>();
//        map.put("head",data.getHead());
//        map.put("body",data.getBody());
//        if(!"".equals(url)) {
//            logger.info("开始创建form表单提交跳转页面-->");
//            jumpHtml.append("<html>");
//            String head = "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + charset + "\" pageEncoding=\"" + charset + "\" />";
//            jumpHtml.append(head);
//            jumpHtml.append("<title>loading</title>");
//            jumpHtml.append("<style type=\"text/css\">");
//            jumpHtml.append("body{margin:200px auto;font-family: \"宋体\", Arial;font-size: 12px;color: #369;text-align: center;}");
//            jumpHtml.append("#1{height:auto; width:78px; margin:0 auto;}");
//            jumpHtml.append("#2{height:auto; width:153px; margin:0 auto;}");
//            jumpHtml.append("vertical-align: bottom;}");
//            jumpHtml.append("</style>");
//            jumpHtml.append("</head>");
//            jumpHtml.append("<body>");
//            jumpHtml.append("<div id=\"3\">交易处理中...</div>");
//            jumpHtml.append("<form name=\"forwardForm\" action=\"").append(url).append("\" method=\"POST\"><div style=\"display:none\">");
//            logger.info("WebUtils genForwardHtml::url=" + url);
//            Iterator keyIterator = map.keySet().iterator();
//
//            while(keyIterator.hasNext()) {
//                Object key = keyIterator.next();
//                jumpHtml.append("<textarea name=\"").append(key.toString()).append("\">").append((String)map.get(key)).append("</textarea>");
//                logger.info("创建跳转页面参数:" + key.toString() + "=" + map.get(key));
//            }
//            jumpHtml.append("</div></form>");
//            jumpHtml.append("<SCRIPT LANGUAGE=\"Javascript\">");
//            jumpHtml.append("    document.forwardForm.submit();");
//            jumpHtml.append("</SCRIPT>");
//            jumpHtml.append("</body>");
//            jumpHtml.append("</html>");
//            logger.info("form表单提交跳转页面创建完成");
//        }
//        return jumpHtml.toString();
//    }


    public static void main(String[] args) {
//                Header[] headers = response.getAllHeaders();
//                if(headers != null && headers.length > 0){
//                    for(Header tem : headers){
//                        log.info("--- header name =" + tem.getName() +"---- value="+tem.getValue());
//                    }
//                }
        String urlString = "http://api.map.baidu.com/location/ip?ak=FIkaMovHOv5VAG1Ew9BbjKs8M6w7LvN7&coor=bd09ll";
        doGet(urlString);
    }





}