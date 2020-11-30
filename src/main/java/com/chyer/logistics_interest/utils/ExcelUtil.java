package com.chyer.logistics_interest.utils;

import com.chyer.logistics_interest.mapper.GreenhouseMonitorDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";



    public static Workbook createWorkbook(){
        Workbook wb = new XSSFWorkbook();
        return wb;
    }

    public static Sheet createSheet(Workbook wb){
        Sheet sheet = wb.createSheet();
        return sheet;
    }




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

    public static void writeExcel(SXSSFWorkbook workbook, String mainTitle, String[] titles, LinkedList<Object[]> contents, Integer currentSheet){
        Row row = null;
        org.apache.poi.ss.usermodel.Cell cell = null;
        int rows = 0;
        int cells = titles.length;
        int maxRows = 65536;
        boolean last = false;
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Sheet"+currentSheet);
        /** ***************以下是EXCEL第一，二行列标题样式********************* */
        org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 10);
        CellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCellStyle.setFont(titleFont);
        /** ***************以下是EXCEL正文样式********************* */
        org.apache.poi.ss.usermodel.Font textFont = workbook.createFont();
        textFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 10);
        CellStyle textCellStyle = workbook.createCellStyle();
        textCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        textCellStyle.setFont(textFont);
        //** ***************以下是EXCEL第一行列标题********************* */
        if (mainTitle != null && !mainTitle.equals("")) {
            row = sheet.createRow(rows++);
            cell = row.createCell(0);
            cell.setCellStyle(titleCellStyle);
            cell.setCellValue(mainTitle);
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,titles.length-1));
        }
        /** ***************以下是EXCEL第二行列标题********************* */
        if (titles != null && cells > 1) {
            row = sheet.createRow(rows++);
            for (int i = 0; i < cells; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(titleCellStyle);
                cell.setCellValue(titles[i]);
            }
        }
        /** ***************以下是EXCEL正文数据********************* */
        if (maxRows > contents.size()){
            maxRows = contents.size()+1;
            last = true;
        }
        for (int i = 1; i <= maxRows-1; i++) {// row
            if (rows >= maxRows && !last){
                currentSheet ++;
                writeExcel( workbook, mainTitle, titles, contents, currentSheet);
            }else {
                row = sheet.createRow(rows++);
                Object[] rowContent = contents.getFirst();
                for (int j = 0; j < cells; j++) { // cell
                    String content = "";
                    if (j < rowContent.length) {
                        if(rowContent[j]!="null" && !StringUtils.isEmpty(rowContent[j])) {
                            content = rowContent[j].toString();
                        }
                    }
                    cell = row.createCell(j);
                    cell.setCellStyle(textCellStyle);
                    cell.setCellValue(content);
                }
                contents.removeFirst();
            }
        }
    }

}
