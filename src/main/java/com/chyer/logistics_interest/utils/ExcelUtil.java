package com.chyer.logistics_interest.utils;

import com.chyer.logistics_interest.mapper.GreenhouseMonitorDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";


    public static void main(String[] args) {
        Workbook wb = null;
        String path = "E:\\zselfWorkSpase\\温室种植\\新泰长兴温室数据.xlsx";

        try {
            File file = new File(path);
            FileInputStream in = new FileInputStream(file);
            if (file.getName().endsWith(EXCEL_XLS)) {     //Excel&nbsp;2003
                wb = new HSSFWorkbook(in);
            } else if (file.getName().endsWith(EXCEL_XLSX)) {    // Excel 2007/2010
                wb = new XSSFWorkbook(in);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Integer sheetNum = wb.getNumberOfSheets();  //sheet个数
        log.info("****本excle含有的sheet个数  sheetNum=" + sheetNum);
        //Sheet sheet = wb.getSheetAt(0);
        if (sheetNum != null && sheetNum > 0) {
            for (int x = 0; x < sheetNum; x++) {
                Sheet sheet = wb.getSheetAt(x);
                Integer rowNo = sheet.getPhysicalNumberOfRows(); //本sheet含有的总行数
                log.info("第" + x + "个sheet含有的总行数 rowNo ==" + rowNo);

                Row row0 = sheet.getRow(0);
                if (row0 == null) {
                    continue;
                }
                int cellTotalNum0 = row0.getPhysicalNumberOfCells();
                if (cellTotalNum0 > 0) {
                    for (int i = 0; i < cellTotalNum0; i++) {
                        Cell cell1 = row0.getCell(i);
                        if (cell1 != null) {
                            String content = cell1.toString();

                            log.info("--- 第0 row- 第" + i + "个单元格cell的内容=" + content);
                        }
                    }

                }

                Integer cellTotalNum = 0;
                for (int m = 1; m < 3; m++) {
                    Row row = sheet.getRow(m);
                    if (row == null) {
                        continue;
                    }
                    cellTotalNum = row.getPhysicalNumberOfCells();
                    if (cellTotalNum > 0 && m < rowNo - 1) {
                        for (int i = 0; i < cellTotalNum; i++) {
                            Cell cell1 = row.getCell(i);
                            if (cell1 != null) {
                                String content = cell1.toString();
                                //String content2 = cell1.getStringCellValue();
                                log.info("--- 第" + m + "row 第" + i + "个单元格cell" + content);
                            }
                        }

                    }
                }


            }
        }


    }


    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException {
        log.info("-------判断Excel的版本,获取Workbook-------");
        Workbook wb = null;
        String path = "E:\\zselfWorkSpase\\温室种植\\新泰长兴温室数据.xlsx";
        FileInputStream in = new FileInputStream(file);
        if (file.getName().endsWith(EXCEL_XLS)) {     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }


    public static List<GreenhouseMonitorDataMapper> readExcel(Workbook wb) {
        List<GreenhouseMonitorDataMapper> trainList = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        Integer sheetNum = wb.getNumberOfSheets();  //sheet个数
        log.info("****本excle含有的sheet个数  sheetNum=" + sheetNum);
        //Sheet sheet = wb.getSheetAt(0);
        if (sheetNum != null && sheetNum > 0) {
            for (int x = 0; x < sheetNum; x++) {
                Sheet sheet = wb.getSheetAt(x);

                Integer rowNo = sheet.getPhysicalNumberOfRows(); //本sheet含有的总行数
                log.info("第" + x + "个sheet含有的总行数 rowNo ==" + rowNo);
                Integer airtempNO = 0;
                Integer airhumiNO = 0;
                Integer soiltempNO = 0;
                Integer soilhumiNO = 0;
                Integer co2No = 0;
                Integer sundataNO = 0;

                Integer cellTotalNum = 0;


                for (int m = 1; m < rowNo; m++) {
                    Row row = sheet.getRow(m);
                    if (row == null) {
                        continue;
                    }
                    cellTotalNum = row.getPhysicalNumberOfCells();
                    if (cellTotalNum > 0 && m < rowNo - 1) {
                        Cell cell1 = row.getCell(1);
                        if (cell1 != null) {
                            String content = cell1.toString();
                            if (content != null && !"".equals(content) && "车次".equals(content)) {
                                checiNO = m;
                                log.info("----车次所在的行数  checiNO  -----" + checiNO);
                            } else if (content != null && !"".equals(content) && "始发站".equals(content)) {
                                startStationNO = m;
                                log.info("----  始发站所在的行数 startStationNO==-----" + startStationNO);
                            } else if (content != null && !"".equals(content) && "终到站".equals(content)) {
                                endStation = m;
                                log.info("----  终点站所在的行数endStation  -----" + endStation);
                            } else if (content != null && !"".equals(content) && "郑州东京广场".equals(content)) {
                                zzjgNum = m;
                                log.info("----郑州东京广场所在的行数  zzjgNum  -----" + zzjgNum);
                            } else {
                                continue;
                            }
                        }
                    } else if ((cellTotalNum == 0 && m > 10) || m == rowNo - 1) {
                        Row checiRow = sheet.getRow(checiNO); //车次行
                        Integer cellNum = checiRow.getPhysicalNumberOfCells();
                        Row zzjgRow = sheet.getRow(zzjgNum);  //郑州东京广场          到站时间行
                        Row zzjgLZRow = sheet.getRow(zzjgNum + 1);//郑州东京广场        离站时间行
                        for (int a = 2; a < cellNum; a++) {
                            String checiName = checiRow.getCell(a).toString();
                            String reachTimeStr = "";
                            String leaveTimeStr = "";
                            String startStrationName = "";
                            String endStrationName = "";
                            String frontStationName = "";
                            String afterStationName = "";
                            Integer pickUpTrack = 0;

                            if (checiName != null && !"".equals(checiName)) {
                                reachTimeStr = zzjgRow.getCell(a).toString().trim();
                                leaveTimeStr = zzjgLZRow.getCell(a).toString().trim();
                                if ((reachTimeStr == null || "".equals(reachTimeStr)) && (leaveTimeStr == null || "".equals(leaveTimeStr))) {
                                    continue;
                                } else {
                                    //股道数
                                    if (zzjgRow.getCell(a + 1).toString() != null && !"".equals(zzjgRow.getCell(a + 1).toString())) {
                                        pickUpTrack = Integer.valueOf(zzjgRow.getCell(a + 1).toString());
                                    }
                                    //始发站         终到站
                                    for (CellRangeAddress temCell : cellRangeList) {
                                        if (temCell.getFirstRow() == startStationNO && (a >= temCell.getFirstColumn() && a <= temCell.getLastColumn())) {//始发站
                                            Row startRow = sheet.getRow(startStationNO);
                                            Cell startCell = startRow.getCell(temCell.getFirstColumn());
                                            startStrationName = startCell.toString();
                                        } else if (temCell.getFirstRow() == endStation && (a >= temCell.getFirstColumn() && a <= temCell.getLastColumn())) { //终到站
                                            Row endRow = sheet.getRow(endStation);
                                            Cell startCell = endRow.getCell(temCell.getFirstColumn());
                                            endStrationName = startCell.toString();
                                        } else {
                                            continue;
                                        }
                                    }
                                    // 提取车次的 前方站
                                    for (int i = zzjgNum - 1; i > checiNO + 2; i = i - 2) {
                                        Row leaveTimeRow = sheet.getRow(i);
                                        Row reachTimeRow = sheet.getRow(i - 1);
                                        String temLeaveTime = leaveTimeRow.getCell(a).toString();
                                        String temReachTime = reachTimeRow.getCell(a).toString();
                                        if ((temLeaveTime != null && !"".equals(temLeaveTime)) || (temReachTime != null && !"".equals(temReachTime))) {
                                            frontStationName = reachTimeRow.getCell(1).toString();
                                            break;
                                        }
                                    }
                                    // 提取车次的 后方站
                                    for (int i = zzjgNum + 2; i < m; i = i + 2) {
                                        Row reachTimeRow = sheet.getRow(i);
                                        Row leaveTimeRow = sheet.getRow(i + 1);
                                        String temLeaveTime = leaveTimeRow.getCell(a).toString();
                                        String temReachTime = reachTimeRow.getCell(a).toString();
                                        if ((temLeaveTime != null && !"".equals(temLeaveTime)) || (temReachTime != null && !"".equals(temReachTime))) {
                                            afterStationName = reachTimeRow.getCell(1).toString();
                                            break;
                                        }
                                    }
                                    System.out.println("车次名称：" + checiName + "-所在列-" + a + "-始发站=" + startStrationName + "-终到站=" + endStrationName
                                            + "---前方站=" + frontStationName + "---后方站=" + afterStationName
                                            + "-到站时间=" + reachTimeStr + "-离站时间=" + leaveTimeStr + "-股道=" + pickUpTrack.toString());

                                    //处理 到站/离站时间
                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                    Date reachTime = null;
                                    Date leaveTime = null;

                                    try {
                                        if (reachTimeStr != null && !"".equals(reachTimeStr) && !"...".equals(reachTimeStr) && !"--".equals(reachTimeStr)) {
                                            if (!reachTimeStr.contains(":") && reachTimeStr.length() == 2 && leaveTimeStr.contains(":")) {
                                                String hourNum = leaveTimeStr.substring(0, leaveTimeStr.indexOf(":"));
                                                reachTimeStr = hourNum + ":" + reachTimeStr + ":00";
                                            } else if (!reachTimeStr.contains(":") && reachTimeStr.length() == 4 && leaveTimeStr.contains(":")) {
                                                String hourNum = leaveTimeStr.substring(0, leaveTimeStr.indexOf(":"));
                                                String minNum = reachTimeStr.substring(0, 2);
                                                String secNum = reachTimeStr.substring(2, 4);
                                                reachTimeStr = hourNum + ":" + minNum + ":" + secNum;
                                            } else if (reachTimeStr.contains(":") && reachTimeStr.length() <= 5) {
                                                reachTimeStr = reachTimeStr + ":00";
                                            } else if (reachTimeStr.contains(":") && reachTimeStr.length() > 5) {
                                                String fStr = reachTimeStr.substring(0, reachTimeStr.length() - 2);
                                                String aStr = reachTimeStr.substring(reachTimeStr.length() - 2, reachTimeStr.length());
                                                reachTimeStr = fStr + ":" + aStr;
                                            }
                                            if (reachTimeStr.length() > 6) {
                                                reachTime = sdf.parse(reachTimeStr);
                                            }
                                        }

                                        if (leaveTimeStr != null && !"".equals(leaveTimeStr) && !"...".equals(leaveTimeStr) && !"--".equals(leaveTimeStr)) {
                                            if (!leaveTimeStr.contains(":") && leaveTimeStr.length() == 2 && reachTimeStr.contains(":")) {
                                                String hourNum = reachTimeStr.substring(0, reachTimeStr.indexOf(":"));
                                                leaveTimeStr = hourNum + ":" + leaveTimeStr + ":00";
                                            } else if (!leaveTimeStr.contains(":") && leaveTimeStr.length() == 4 && reachTimeStr.contains(":")) {
                                                String hourNum = reachTimeStr.substring(0, reachTimeStr.indexOf(":"));
                                                String minNum = leaveTimeStr.substring(0, 2);
                                                String secNum = leaveTimeStr.substring(2, 4);
                                                leaveTimeStr = hourNum + ":" + minNum + ":" + secNum;
                                            } else if (leaveTimeStr.contains(":") && leaveTimeStr.length() <= 5) {
                                                leaveTimeStr = leaveTimeStr + ":00";
                                            } else if (leaveTimeStr.contains(":") && leaveTimeStr.length() > 5) {
                                                String fStr = leaveTimeStr.substring(0, leaveTimeStr.length() - 2);
                                                String aStr = leaveTimeStr.substring(leaveTimeStr.length() - 2, leaveTimeStr.length());
                                                leaveTimeStr = fStr + ":" + aStr;
                                            }

                                            if (leaveTimeStr.length() > 6) {
                                                leaveTime = sdf.parse(leaveTimeStr);
                                            }

                                        }

                                    } catch (ParseException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }

//                                    TrainModel temTrain = new TrainModel();
//                                    temTrain.setTrainName(checiName);
//                                    temTrain.setStartStationName(startStrationName);
//                                    temTrain.setEndStationName(endStrationName);
//                                    temTrain.setFrontStationName(frontStationName);
//                                    temTrain.setAfterStationName(afterStationName);
//                                    temTrain.setReachTime(reachTime);
//                                    temTrain.setLeaveTime(leaveTime);
//                                    temTrain.setPickUpTrack(pickUpTrack);
//                                    temTrain.setDepartureTrack(pickUpTrack);
//
//                                    trainList.add(temTrain);
                                }
                            }
                        }
                    }
                }

            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("方法运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return trainList;
    }


//    public static List<GreenhouseMonitorDataMapper> readExcel(Workbook wb) {
//        List<GreenhouseMonitorDataMapper> trainList = new ArrayList<>();
//        long  startTime=System.currentTimeMillis();
//        Integer sheetNum = wb.getNumberOfSheets();  //sheet个数
//        log.info("****本excle含有的sheet个数  sheetNum=" + sheetNum);
//        //Sheet sheet = wb.getSheetAt(0);
//        if(sheetNum != null && sheetNum > 0) {
//            for(int x=0;x < sheetNum;x++) {
//                Sheet sheet = wb.getSheetAt(x);
//                Integer mergeCellNum = sheet.getNumMergedRegions(); //sheet含有的合并单元格的个数
//                log.info("第"+ x +"个sheet含有的合并单元格的个数  mergeCellNum=" + mergeCellNum);
//
//                List<CellRangeAddress> cellRangeList = new ArrayList<CellRangeAddress>();
//                cellRangeList = sheet.getMergedRegions();
//                Integer rowNo = sheet.getPhysicalNumberOfRows(); //本sheet含有的总行数
//                log.info("第"+ x +"个sheet含有的总行数 rowNo ==" + rowNo);
//                Integer checiNO = 0;
//                Integer startStationNO =0;
//                Integer endStation = 0;
//                Integer cellTotalNum = 0;
//                Integer zzjgNum = 0;
//
//                for(int m=1;m<rowNo;m++) {
//                    Row row = sheet.getRow(m);
//                    if(row == null) {
//                        continue;
//                    }
//                    cellTotalNum = row.getPhysicalNumberOfCells();
//                    if(cellTotalNum >0 && m < rowNo-1) {
//                        Cell cell1 = row.getCell(1);
//                        if(cell1 != null) {
//                            String content =  cell1.toString();
//                            if(content != null && !"".equals(content) && "车次".equals(content)){
//                                checiNO = m ;
//                                log.info("----车次所在的行数  checiNO  -----" + checiNO);
//                            }else if(content != null && !"".equals(content) && "始发站".equals(content)){
//                                startStationNO = m ;
//                                log.info("----  始发站所在的行数 startStationNO==-----" + startStationNO);
//                            }else if(content != null && !"".equals(content) && "终到站".equals(content)){
//                                endStation = m ;
//                                log.info("----  终点站所在的行数endStation  -----" + endStation);
//                            }else if(content != null && !"".equals(content) && "郑州东京广场".equals(content)) {
//                                zzjgNum = m ;
//                                log.info("----郑州东京广场所在的行数  zzjgNum  -----" + zzjgNum);
//                            }else {
//                                continue;
//                            }
//                        }
//                    }else if((cellTotalNum == 0 && m >10) || m == rowNo-1){
//                        Row checiRow = sheet.getRow(checiNO); //车次行
//                        Integer cellNum = checiRow.getPhysicalNumberOfCells();
//                        Row zzjgRow = sheet.getRow(zzjgNum);  //郑州东京广场          到站时间行
//                        Row zzjgLZRow = sheet.getRow(zzjgNum +1);//郑州东京广场        离站时间行
//                        for(int a =2;a<cellNum;a++) {
//                            String checiName = checiRow.getCell(a).toString();
//                            String reachTimeStr = "";
//                            String leaveTimeStr = "";
//                            String startStrationName = "";
//                            String endStrationName = "";
//                            String frontStationName = "";
//                            String afterStationName = "";
//                            Integer pickUpTrack = 0;
//
//                            if(checiName != null && !"".equals(checiName)) {
//                                reachTimeStr = zzjgRow.getCell(a).toString().trim();
//                                leaveTimeStr = zzjgLZRow.getCell(a).toString().trim();
//                                if((reachTimeStr == null || "".equals(reachTimeStr)) && (leaveTimeStr == null || "".equals(leaveTimeStr) )) {
//                                    continue;
//                                }else {
//                                    //股道数
//                                    if(zzjgRow.getCell(a+1).toString() != null && !"".equals(zzjgRow.getCell(a+1).toString())) {
//                                        pickUpTrack = Integer.valueOf(zzjgRow.getCell(a+1).toString());
//                                    }
//                                    //始发站         终到站
//                                    for(CellRangeAddress temCell : cellRangeList) {
//                                        if(temCell.getFirstRow() == startStationNO && (a >= temCell.getFirstColumn() && a <= temCell.getLastColumn())) {//始发站
//                                            Row startRow = sheet.getRow(startStationNO);
//                                            Cell startCell = startRow.getCell(temCell.getFirstColumn());
//                                            startStrationName = startCell.toString();
//                                        }else if(temCell.getFirstRow() == endStation && (a >= temCell.getFirstColumn() && a <= temCell.getLastColumn())) { //终到站
//                                            Row endRow = sheet.getRow(endStation);
//                                            Cell startCell = endRow.getCell(temCell.getFirstColumn());
//                                            endStrationName = startCell.toString();
//                                        }else {
//                                            continue;
//                                        }
//                                    }
//                                    // 提取车次的 前方站
//                                    for(int i =zzjgNum-1;i> checiNO+2;i=i-2 ) {
//                                        Row leaveTimeRow = sheet.getRow(i);
//                                        Row reachTimeRow = sheet.getRow(i-1);
//                                        String temLeaveTime = leaveTimeRow.getCell(a).toString();
//                                        String temReachTime = reachTimeRow.getCell(a).toString();
//                                        if((temLeaveTime != null && !"".equals(temLeaveTime)) || (temReachTime != null && !"".equals(temReachTime))) {
//                                            frontStationName = reachTimeRow.getCell(1).toString();
//                                            break;
//                                        }
//                                    }
//                                    // 提取车次的 后方站
//                                    for(int i =zzjgNum+2;i<m;i=i+2 ) {
//                                        Row reachTimeRow = sheet.getRow(i);
//                                        Row leaveTimeRow = sheet.getRow(i+1);
//                                        String temLeaveTime = leaveTimeRow.getCell(a).toString();
//                                        String temReachTime = reachTimeRow.getCell(a).toString();
//                                        if((temLeaveTime != null && !"".equals(temLeaveTime)) || (temReachTime != null && !"".equals(temReachTime))) {
//                                            afterStationName = reachTimeRow.getCell(1).toString();
//                                            break;
//                                        }
//                                    }
//                                    System.out.println("车次名称：" + checiName +"-所在列-" + a +"-始发站="+startStrationName+"-终到站="+endStrationName
//                                            +"---前方站="+ frontStationName + "---后方站="+afterStationName
//                                            +"-到站时间="+ reachTimeStr + "-离站时间="+leaveTimeStr +"-股道="+pickUpTrack.toString());
//
//                                    //处理 到站/离站时间
//                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//                                    Date reachTime = null;
//                                    Date leaveTime = null;
//
//                                    try {
//                                        if(reachTimeStr != null && !"".equals(reachTimeStr) && !"...".equals(reachTimeStr) && !"--".equals(reachTimeStr)) {
//                                            if(!reachTimeStr.contains(":") && reachTimeStr.length() == 2 && leaveTimeStr.contains(":")) {
//                                                String hourNum = leaveTimeStr.substring(0, leaveTimeStr.indexOf(":"));
//                                                reachTimeStr = hourNum + ":" + reachTimeStr + ":00";
//                                            }else if(!reachTimeStr.contains(":") && reachTimeStr.length() == 4 && leaveTimeStr.contains(":")) {
//                                                String hourNum = leaveTimeStr.substring(0, leaveTimeStr.indexOf(":"));
//                                                String minNum = reachTimeStr.substring(0,2);
//                                                String secNum = reachTimeStr.substring(2,4);
//                                                reachTimeStr = hourNum + ":" + minNum + ":" + secNum;
//                                            }else if(reachTimeStr.contains(":") && reachTimeStr.length() <= 5) {
//                                                reachTimeStr = reachTimeStr + ":00";
//                                            }else if(reachTimeStr.contains(":") && reachTimeStr.length() > 5) {
//                                                String fStr = reachTimeStr.substring(0,reachTimeStr.length()-2);
//                                                String aStr = reachTimeStr.substring(reachTimeStr.length()-2,reachTimeStr.length());
//                                                reachTimeStr = fStr + ":" + aStr;
//                                            }
//                                            if(reachTimeStr.length() > 6) {
//                                                reachTime = sdf.parse(reachTimeStr);
//                                            }
//                                        }
//
//                                        if(leaveTimeStr != null && !"".equals(leaveTimeStr) && !"...".equals(leaveTimeStr) && !"--".equals(leaveTimeStr)) {
//                                            if(!leaveTimeStr.contains(":") && leaveTimeStr.length() == 2 && reachTimeStr.contains(":")) {
//                                                String hourNum = reachTimeStr.substring(0, reachTimeStr.indexOf(":"));
//                                                leaveTimeStr = hourNum + ":" + leaveTimeStr + ":00";
//                                            }else if(!leaveTimeStr.contains(":") && leaveTimeStr.length() == 4 && reachTimeStr.contains(":")) {
//                                                String hourNum = reachTimeStr.substring(0, reachTimeStr.indexOf(":"));
//                                                String minNum = leaveTimeStr.substring(0,2);
//                                                String secNum = leaveTimeStr.substring(2,4);
//                                                leaveTimeStr =  hourNum + ":" + minNum + ":" + secNum;
//                                            }else if(leaveTimeStr.contains(":") && leaveTimeStr.length() <= 5) {
//                                                leaveTimeStr = leaveTimeStr + ":00";
//                                            }else if(leaveTimeStr.contains(":") && leaveTimeStr.length() > 5) {
//                                                String fStr = leaveTimeStr.substring(0,leaveTimeStr.length()-2);
//                                                String aStr = leaveTimeStr.substring(leaveTimeStr.length()-2,leaveTimeStr.length());
//                                                leaveTimeStr = fStr + ":" + aStr;
//                                            }
//
//                                            if(leaveTimeStr.length() > 6) {
//                                                leaveTime = sdf.parse(leaveTimeStr);
//                                            }
//
//                                        }
//
//                                    } catch (ParseException e) {
//                                        // TODO Auto-generated catch block
//                                        e.printStackTrace();
//                                    }
//
////                                    TrainModel temTrain = new TrainModel();
////                                    temTrain.setTrainName(checiName);
////                                    temTrain.setStartStationName(startStrationName);
////                                    temTrain.setEndStationName(endStrationName);
////                                    temTrain.setFrontStationName(frontStationName);
////                                    temTrain.setAfterStationName(afterStationName);
////                                    temTrain.setReachTime(reachTime);
////                                    temTrain.setLeaveTime(leaveTime);
////                                    temTrain.setPickUpTrack(pickUpTrack);
////                                    temTrain.setDepartureTrack(pickUpTrack);
////
////                                    trainList.add(temTrain);
//                                }
//                            }
//                        }
//                    }
//                }
//
//            }
//        }
//
//        long  endTime=System.currentTimeMillis();
//        System.out.println("方法运行时间："+String.valueOf(endTime-startTime)+"ms");
//        return trainList;
//    }


//       //提取出所有的站名，去重进库
//    public static Set<String> pickUpStationName(Workbook wb) throws IOException {
//        Set<String> stationNameSet = new HashSet<String>();
//
//        Integer sheetNum = wb.getNumberOfSheets();  //sheet个数
//        if(sheetNum != null && sheetNum > 0) {
//            System.out.println("****本excle含有的sheet个数  sheetNum=" + sheetNum+"*******");
//        }
//
//        for(int a=0;a<sheetNum;a++) {
//            Integer chezhanNO = 0;   //车站所在的行数
//            Integer startStationNO =0; //始发站所在的行数
//            Integer endStation = 0; //终到站站所在的行数
//            Integer cellTotalNum = 0;
//
//            Sheet sheet = wb.getSheetAt(a);
//
//            Integer rowNo = sheet.getPhysicalNumberOfRows(); //本sheet含有的总行数
//            System.out.println("第"+a+"个sheet含有的总行数 rowNo ==" + rowNo);
//            for(int m=1;m<rowNo;m++) {
//                Row row = sheet.getRow(m);
//                if(row == null) {
//                    continue;
//                }
//                cellTotalNum = row.getPhysicalNumberOfCells();
//                if(cellTotalNum >0 && m < rowNo-1) {
//                    Cell cell1 = row.getCell(1);
//                    if(cell1 != null) {
//                        String content =  cell1.toString();
//                        if(content != null && !"".equals(content) && "车站".equals(content)){
//                            chezhanNO = m ;
//                            System.out.println("----车站所在的行数  chezhanNO=" + chezhanNO);
//                        }else if(content != null && !"".equals(content) && "始发站".equals(content)){
//                            startStationNO = m ;
//                            System.out.println("----始发站所在的行数 startStationNO=" + startStationNO);
//                        }else if(content != null && !"".equals(content) && "终到站".equals(content)){
//                            endStation = m ;
//                            System.out.println("----终点站所在的行数endStation=" + endStation);
//                        }else {
//                            continue;
//                        }
//                    }
//                }else if((cellTotalNum == 0 && m >10) || m == rowNo-1){
//                    System.out.println("**** 提取每一块的站名信息****");
//                    Row startRow = sheet.getRow(startStationNO);//始发站row
//                    Integer cellNum = startRow.getPhysicalNumberOfCells();
//                    for(int i=0;i< cellNum;i++) {
//                        Cell temCell = startRow.getCell(i);
//                        if(temCell != null) {
//                            String temStationName = temCell.toString();
//                            if(temStationName != null && temStationName.length() > 0) {
//                                stationNameSet.add(temStationName);
//                            }
//                        }
//                    }
//                    Row endRow = sheet.getRow(endStation);//终点站row
//                    cellNum = endRow.getPhysicalNumberOfCells();
//                    for(int i=0;i< cellNum;i++) {
//                        Cell temCell = endRow.getCell(i);
//                        if(temCell != null) {
//                            String temStationName = temCell.toString();
//                            if(temStationName != null && temStationName.length() > 0) {
//                                stationNameSet.add(temStationName);
//                            }
//                        }
//                    }
//                    if(chezhanNO > 0) {
//                        for(int r=chezhanNO;r<m;r++) {
//                            Row chezhanRow = sheet.getRow(r);
//                            String temStationName = chezhanRow.getCell(1).toString();
//                            if(temStationName != null && temStationName.length() > 0) {
//                                stationNameSet.add(temStationName);
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
//        logger.info("stationNameSet="  + stationNameSet.toString());
//        return stationNameSet;
//    }
//
//
//    //生成 <日车次计划>
//    public static List<DailyPlanTrainModel> createDailyPlan(Workbook wb) {
//        List<DailyPlanTrainModel> dailyPlanList = new ArrayList<DailyPlanTrainModel>();
//        Integer sheetNum = wb.getNumberOfSheets();  //sheet个数
//        if(sheetNum != null && sheetNum > 0) {
//            logger.info("****本excle含有的sheet个数  sheetNum=" + sheetNum+"*******");
//        }
//        if(sheetNum != null && sheetNum > 0) {
//            //for(int i=0;i<sheetNum;i++) {
//            int i=0;
//            Sheet sheet = wb.getSheetAt(0);
//            Integer rowNo = sheet.getPhysicalNumberOfRows(); //本sheet含有的总行数
//            logger.info("第"+i+"个sheet含有的总行数 rowNo ==" + rowNo);
//            if(rowNo != null && rowNo >0) {
//                for(int j=2;j<rowNo;j++) {
//                    Row row = sheet.getRow(j);
//                    DailyPlanTrainModel dailyPlan = new DailyPlanTrainModel();
//                    //Integer cellNum = row.getPhysicalNumberOfCells();
//                    dailyPlan.setTrainName(row.getCell(2).toString());//车次名称
//                    String marshNum = row.getCell(12).toString();
//                    if("8".equals(marshNum) || "8.0".equals(marshNum)) {
//                        dailyPlan.setMarshallingNumber("8");//编组数
//                        dailyPlan.setMarshallingType(1);
//                    }else if("16".equals(marshNum) || "16.0".equals(marshNum)) {
//                        dailyPlan.setMarshallingNumber("16");//编组数
//                        dailyPlan.setMarshallingType(2);
//                    }else {
//                        dailyPlan.setMarshallingNumber(marshNum);//编组数
//                        dailyPlan.setMarshallingType(3);
//                    }
//                    dailyPlan.setLocoName(row.getCell(13).toString());//车型
//                    dailyPlan.setBearBureau(row.getCell(14).toString());//担当局名称
//                    dailyPlan.setRemark(row.getCell(15).toString());//备注
//
//                    dailyPlanList.add(dailyPlan);
//                }
//            }
//
//            //}
//        }
//        logger.info("生成的车次日计划包含的车次数目 Num =" + dailyPlanList.size());
//        return dailyPlanList;
//    }


}
