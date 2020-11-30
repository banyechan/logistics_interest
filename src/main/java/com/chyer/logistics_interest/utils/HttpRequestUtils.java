package com.chyer.logistics_interest.utils;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by sshine on 2018/10/18.
 */
public class HttpRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);


    public static  String doPost(String url,Map<String,String> params){
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String body = null;

        HttpPost post = postForm(url, params);

        body = invoke(httpclient, post);

        httpclient.getConnectionManager().shutdown();

        return body;
    }

    private static HttpPost postForm(String url, Map<String, String> params) {

        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        if (params != null) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpPost;
    }

    private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpPost) {
        HttpResponse response = sendRequest(httpclient, httpPost);
        String body = parseResponse(response);
        return body;
    }

    private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpPost) {
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String parseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(entity);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }


    public static void main(String[] args) throws IOException {
//        String gongshi = "http://113.140.78.198:801/pub/GongShiSearch";
//        Map<String,String> postParam = new HashMap<String,String>();
//        postParam.put("__RequestVerificationToken_Lw__","MI9ZtsHAc9ec077qhhln3YEYhd8Ser7A9cgcZXr/vYV0535YbaBdvk+ar73mhL2bXzhm4dgoCLudrIz2q8a3FQYzyYYfjrcfjjSuW90KO0g=");
//        postParam.put("JiJuLeiXing","轮式拖拉机");
//        postParam.put("JiJuLeiXingCode","140101");
//        //postParam.put("pageIndex", "10");
//
//
//        String result = doPost(gongshi,postParam);


        String a = "/pub/GongShiSearch?pageIndex=558";
        String href = "/pub/GongShiSearch?pageIndex=558";
        int equleIndex = href.indexOf("=");
        String tailNum = href.substring(equleIndex+1);
        logger.info("---- tailNum={}",tailNum);




    }


    public static void soupTest(){
        //Connection connect =  Jsoup.connect("http://113.140.78.198:801/pub/gongshi");
        //Document doc = Jsoup.parse(sbf.toString());
//        String name = doc2.title();
//        String url = doc2.nodeName();

        //        //JSONObject jsonResult = JSONObject.parseObject(result);
//
//        Document doc = Jsoup.parse(sbf.toString());
//        Element signDiv = doc.getElementById("sidebar-top");
//        Elements signSpan  = signDiv.getElementsByClass("signatures-number");
//        String signNumber = signSpan.html();
 //       logger.info("------result ={}",result);
//        //logger.info("------jsonResult ={}",jsonResult);

//        Document doc2 =  Jsoup.connect("http://113.140.78.198:801/pub/gongshi").post();
//
//        String name = doc2.title();
//        String url = doc2.nodeName();

    }








    private static void close(HttpURLConnection httpConn) {
        if (httpConn != null) {
            httpConn.disconnect();
        }
    }
    public static void close(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            }catch (IOException e) {
                // TODO Auto-generated catch block
                logger.info("释放OutputStream失败");
            }
        }
    }
    public static void close(BufferedReader in) {
        if (in != null) {
            try {
                in.close();
            }catch (IOException e) {
                // TODO Auto-generated catch block
                logger.info("释放BufferedReader失败");
            }
        }
    }





}
