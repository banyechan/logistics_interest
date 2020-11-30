package com.chyer.logistics_interest.serviceImpl;

import com.chyer.logistics_interest.service.GongshiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
@Service
public class GongshiServiceImpl implements GongshiService {


    //直接在代码中写死，不易于扩展
    private final String __RequestVerificationToken = "MI9ZtsHAc9ec077qhhln3YEYhd8Ser7A9cgcZXr/vYV0535YbaBdvk+ar73mhL2bXzhm4dgoCLudrIz2q8a3FQYzyYYfjrcfjjSuW90KO0g=";

    @Override
    public String listPageInfo(Integer pageIndex) {
        return null;
    }





    public static String doPost(Integer pageNum) {
        String result = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost("http://113.140.78.198:801/pub/GongShiSearch");

            request.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            request.setHeader("Cookie", "__RequestVerificationToken_Lw__=3Dda7JpMybkNScOTdcriPmJuYCpW33D8IjXnlq/md8YODAvhAETjErmKcsbkVsUlcjRX/QBIAtvy4t1ro1us3jYwtLkH3bmiWOFMUFb6JTY=");

            Map<String, String> formParams = new HashMap<String, String>();
            formParams.put("__RequestVerificationToken", "3Dda7JpMybkNScOTdcriPmJuYCpW33D8IjXnlq/md8YODAvhAETjErmKcsbkVsUlcjRX/QBIAtvy4t1ro1us3jYwtLkH3bmiWOFMUFb6JTY=");
            formParams.put("JiJuLeiXing", "轮式拖拉机");
            formParams.put("JiJuLeiXingCode", "140101");

            if(pageNum != null && pageNum > 0){
                formParams.put("PageIndex",pageNum.toString());
            }

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            if (formParams != null) {
                Set<String> keySet = formParams.keySet();
                for (String key : keySet) {
                    nvps.add(new BasicNameValuePair(key, formParams.get(key)));
                }
            }

            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    //获得总页数
    public static Integer getTotalPageNum(String html){
        Integer num = 0;
        Document doc = Jsoup.parse(html);
        Element pager = doc.getElementById("pager");
        Elements a2 = pager.getElementsContainingOwnText("尾页");
        String href = a2.first().attr("href");
        int equleIndex = href.indexOf("=");
        String tailNum = href.substring(equleIndex+1);
        if(tailNum != null && tailNum.length() > 0){
            num = Integer.valueOf(tailNum);
        }
        return num;
    }

    //获得每页数据的记录
    public static List<StringBuilder> getRecordList(String html){
        List<StringBuilder> result = new ArrayList<>();

        Document doc = Jsoup.parse(html);
        Element tableBody = doc.getElementById("list-pub");
        Elements trList = tableBody.children();
        if(trList != null && trList.size() > 0){
            for(int i = 0 ; i < trList.size() ; i++){
                Element tr = trList.get(i);
                //log.info("------第{}行的记录 = {}",i,tableBody.toString());

                StringBuilder sb = new StringBuilder();
                Elements tdList = tr.children();
                if(tdList != null && tdList.size() > 0){
                    for(Element td : tdList){
                        sb.append(td.text()).append("****");
                    }
                    result.add(sb);
                    //log.info("------第{}行的记录 = {}",(i+1),sb.toString());
                }
            }
        }

        return result;
    }


    public static void writeExcle(Sheet sheet,List<StringBuilder> records,int time ) throws IOException {
        log.info("第{}次调用writeExcke",time);

        if(time == 1){
            String[] headArr = {"序号","县","所在乡(镇)","所在村组","购机者姓名","机具品目","生产厂家","产品名称",
                    "购买机型","购买数量(台)","经销商","购机日期","单台销售价格(元)","单台补贴额(元)","总补贴额(元)","出厂编号","状态"};
            Row row = sheet.createRow(0);
            for(int m =0 ; m< headArr.length ; m++){
                Cell mCell = row.createCell(m);
                mCell.setCellValue(headArr[m]);
            }
        }

        if(records != null && records.size() > 0){
            for(int n = 0; n<records.size();n++){
                String record = records.get(n).toString();
                log.info("------第{}行的记录 = {}",(n+1),record);
                String [] aArr = record.split("\\*\\*\\*\\*");
                Integer rowNum = Integer.valueOf(aArr[0]);
                Row bodyRow = sheet.createRow(rowNum);
                for(int j = 0 ; j< aArr.length; j++){
                    Cell cell = bodyRow.createCell(j);
                    cell.setCellValue(aArr[j]);
                }

            }
        }
    }



//    public static void writeExcle(List<StringBuilder> records,int time ) throws IOException {
//        log.info("第{}次调用writeExcke",time);
//        Workbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet();
//
//        if(time == 1){
//            String[] headArr = {"序号","县","所在乡(镇)","所在村组","购机者姓名","机具品目","生产厂家","产品名称",
//                    "购买机型","购买数量(台)","经销商","购机日期","单台销售价格(元)","单台补贴额(元)","总补贴额(元)","出厂编号","状态"};
//            Row row = sheet.createRow(0);
//            for(int m =0 ; m< headArr.length ; m++){
//                Cell mCell = row.createCell(m);
//                mCell.setCellValue(headArr[m]);
//            }
//        }
//
//        if(records != null && records.size() > 0){
//            for(int n = 0; n<records.size();n++){
//                String record = records.get(n).toString();
//                log.info("------第{}行的记录 = {}",(n+1),record);
//                String [] aArr = record.split("\\*\\*\\*\\*");
//                Integer rowNum = Integer.valueOf(aArr[0]);
//                Row bodyRow = sheet.createRow(rowNum);
//                for(int j = 0 ; j< aArr.length; j++){
//                    Cell cell = bodyRow.createCell(j);
//                    cell.setCellValue(aArr[j]);
//                }
//
//            }
//        }
//
//        FileOutputStream fout=new FileOutputStream("E:\\pictureFiles\\test.xlsx");
//        wb.write(fout);//Workbook提供了write的方法
//        fout.close();//将输出流关闭
//    }


    public static void main(String[] args) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();

        String firstHtml = doPost(0);
        Integer totalNum = getTotalPageNum(firstHtml);
        List<StringBuilder> firstRecord = getRecordList(firstHtml);
        writeExcle(sheet,firstRecord,1);

        if(totalNum != null && totalNum > 0){
            int time = 2;
            for(int i =2 ; i< totalNum; i++){
                String html = doPost(i);
                List<StringBuilder>  temList = getRecordList(html);

                //recordTotal.addAll(temList);
                writeExcle(sheet,temList,time);
                time = time + 1;
            }
        }

        FileOutputStream fout=new FileOutputStream("E:\\pictureFiles\\test.xlsx");
        wb.write(fout);//Workbook提供了write的方法
        fout.close();//将输出流关闭

    }










//        public static void main(String args[]) throws Exception {
//        String result = null;
//        try {
//            HttpClient httpClient= HttpClients.createDefault();
//            HttpPost request=new HttpPost("http://113.140.78.198:801/pub/GongShiSearch");
//
//            request.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
//            request.setHeader("Cookie","__RequestVerificationToken_Lw__=3Dda7JpMybkNScOTdcriPmJuYCpW33D8IjXnlq/md8YODAvhAETjErmKcsbkVsUlcjRX/QBIAtvy4t1ro1us3jYwtLkH3bmiWOFMUFb6JTY=");
//
//            Map<String,String> formParams = new HashMap<String,String>();
//            formParams.put("__RequestVerificationToken","3Dda7JpMybkNScOTdcriPmJuYCpW33D8IjXnlq/md8YODAvhAETjErmKcsbkVsUlcjRX/QBIAtvy4t1ro1us3jYwtLkH3bmiWOFMUFb6JTY=");
//            formParams.put("JiJuLeiXing","轮式拖拉机");
//            formParams.put("JiJuLeiXingCode","140101");
//
//            formParams.put("PageIndex","2");
//
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//
//            if (formParams != null) {
//                Set<String> keySet = formParams.keySet();
//                for (String key : keySet) {
//                    nvps.add(new BasicNameValuePair(key, formParams.get(key)));
//                }
//            }
//            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
//
//            HttpResponse response = httpClient.execute(request);
//
//            if(response.getStatusLine().getStatusCode()==200){
//                HttpEntity entity = response.getEntity();
//                result= EntityUtils.toString(entity,"utf-8");
//                //log.info("------result=" + result);
//
//                Document doc = Jsoup.parse(result);
//                Elements conent = doc.getElementsByClass("list_bg");
////                Element a = conent.first();
////                Elements table = a.getElementsByAttribute("table");
////                Elements tbody = table.first().getElementsByAttribute("tbody");
//
//
//                Element tableBody = doc.getElementById("list-pub");
//
//                Element pager = doc.getElementById("pager");
//                Elements pageNum = pager.children();
//
//
//                Elements a2 = pager.getElementsContainingOwnText("尾页");
//                String href = a2.first().attr("href");
//                int equleIndex = href.indexOf("=");
//                String tailNum = href.substring(equleIndex+1);
//
//                //log.info("*********************** tableBody = {}",tableBody.toString());
//
//                //Elements trList = tableBody.getElementsByAttribute("tr");
//
//                Elements trList = tableBody.children();
//                List<StringBuilder> sbList = new ArrayList<>();
//                if(trList != null && trList.size() > 0){
//                    for(int i = 0 ; i < trList.size() ; i++){
//                        Element tr = trList.get(i);
//                        //log.info("------第{}行的记录 = {}",i,tableBody.toString());
//
//                        StringBuilder sb = new StringBuilder();
//                        Elements tdList = tr.children();
//                        if(tdList != null && tdList.size() > 0){
//                            for(Element td : tdList){
//                                sb.append(td.text()).append("****");
//                            }
//                            sbList.add(sb);
//                            log.info("------第{}行的记录 = {}",(i+1),sb.toString());
//                        }
//                    }
//                }
//
//            }
//        } catch (Exception e) {
//            log.info("发送 POST 请求出现异常！" + e);
//            e.printStackTrace();
//        }
//
//
//        writeExcle();
//    }





}
