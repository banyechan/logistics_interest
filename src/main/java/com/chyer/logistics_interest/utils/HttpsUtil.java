package com.chyer.logistics_interest.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.*;


@Slf4j
public class HttpsUtil {

    /**
     * get提交方式
     */
    public static String doGet(String urlString){
        String result=null;
        //log.info("----- HttpsUtil.doGet 请求地址为=" + urlString);
        try {
            CloseableHttpClient httpClient= HttpClients.createDefault();
            //创建GET对象
            URL url = new URL(urlString);
            URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            HttpGet httpGet = new HttpGet(uri);
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
            log.info("发送 GET 请求出现异常！" + e);
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
            //requestPost.addHeader("Content-type","application/json; charset=utf-8");
            requestPost.setHeader("Accept", "application/json");
            requestPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

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


    public static void main(String[] args) {
        //String urlString = "http://api.map.baidu.com/location/ip?ak=FIkaMovHOv5VAG1Ew9BbjKs8M6w7LvN7&coor=bd09ll";
        //doGet(urlString);

        String gongshi = "http://113.140.78.198:801/pub/GongShiSearch";
        Map<String,String> postParam = new HashMap<String,String>();
        postParam.put("__RequestVerificationToken","MI9ZtsHAc9ec077qhhln3YEYhd8Ser7A9cgcZXr/vYV0535YbaBdvk+ar73mhL2bXzhm4dgoCLudrIz2q8a3FQYzyYYfjrcfjjSuW90KO0g=");
        postParam.put("JiJuLeiXing","轮式拖拉机");
        postParam.put("JiJuLeiXingCode","140101");
        String postData = postParam.toString();

        String result = doPost(gongshi,postData);

        log.info("---------result = {}",result);

    }

}