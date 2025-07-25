package com.cts.justdial.fwutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    //method to write in excel from a Map data structure
    public static void writeMapToExcel(Map<String, String> dataMap, String keyColName, String valueColName, int keyColNo, int valueColNo,String fileName) {
        File filePath = new File("testdata/"+fileName);
        XSSFWorkbook wb;
        XSSFSheet sheet;

        try {
            if (filePath.exists()) {
                FileInputStream fis = new FileInputStream(filePath);
                wb = new XSSFWorkbook(fis);
                sheet = wb.getSheet("data1");
                fis.close();
            } else {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet("data1");
            }

            // Create header row
            XSSFRow headerRow = sheet.getRow(0);
            if (headerRow == null) {
                headerRow = sheet.createRow(0);
            }
            headerRow.createCell(keyColNo).setCellValue(keyColName);
            headerRow.createCell(valueColNo).setCellValue(valueColName);

            // Write map data
            int rowIndex = 1;
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    row = sheet.createRow(rowIndex);
                }
                row.createCell(keyColNo).setCellValue(entry.getKey());
                row.createCell(valueColNo).setCellValue(entry.getValue());
                rowIndex++;
            }

            FileOutputStream fileOut = new FileOutputStream(filePath);
            wb.write(fileOut);
            fileOut.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method to write in excel from a list
    public static void writeListToExcel(List<WebElement> data, String colName, int colNo, String fileName) {
        File filePath = new File("testdata/"+fileName);
        XSSFWorkbook wb;
        XSSFSheet sheet;
        try {
            if (filePath.exists()) {
                FileInputStream fis = new FileInputStream(filePath);
                wb = new XSSFWorkbook(fis);
                sheet = wb.getSheet("data1");
                fis.close();
            } else {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet("data1");
            }
            int size = data.size();
            XSSFRow first_row = sheet.getRow(0);
            if (first_row == null) {
                first_row = sheet.createRow(0);
            }
            first_row.createCell(colNo).setCellValue(colName);
            for (int i = 1; i <= size; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    row = sheet.createRow(i);
                }
                row.createCell(colNo).setCellValue(data.get(i - 1).getText());
            }
            FileOutputStream fileOut = new FileOutputStream(filePath);
            wb.write(fileOut);
            fileOut.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method to write pass or fail status in the test case data excel
    public static void writeResult(String status, int rowNum) {
        try (FileInputStream file = new FileInputStream("testdata/"+PropertiesFileReader.getPropertyValue("config","testdata"))) {
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);

            // Column is set as 7 since it is the status column no. in the test data excel
            XSSFCell statusCell = row.getCell(7);
            if (statusCell == null) {
                statusCell = row.createCell(7);
            }
            statusCell.setCellValue(status);

            try (FileOutputStream out = new FileOutputStream("testdata/"+PropertiesFileReader.getPropertyValue("config","testdata"))) {
                wb.write(out);
            }

            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

