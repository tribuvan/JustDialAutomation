package com.cts.justdial.fwutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataProvider {
    //Data Provider code to read test case data from excel in TestNG framework.
    @DataProvider(name = "carWashTestData")
    public static String[][] getData(Method testMethod) {
        ArrayList<String[]> allRowsTestData = new ArrayList<>();
        try (FileInputStream fs = new FileInputStream("testdata/"+PropertiesFileReader.getPropertyValue("config","testdata"))) {
            Workbook wb = WorkbookFactory.create(fs);
            Sheet s = wb.getSheet("Sheet1"); // or wb.getSheet(0)
            int rowCount = s.getPhysicalNumberOfRows();
            for (int eachRow = 1; eachRow < rowCount; eachRow++) {
                Row r = s.getRow(eachRow);
                int cellCount = r.getPhysicalNumberOfCells();
                String tcn = r.getCell(2).getStringCellValue().trim().replaceAll(" ", "").toLowerCase();// return the value in the cell

                String tcrunStatus = r.getCell(3).getStringCellValue(); // return the Y/N values which are in the cell
                if (tcn.equalsIgnoreCase(testMethod.getName().toLowerCase()) && tcrunStatus.equalsIgnoreCase("Y")) {
                    ArrayList<String> allCellsData = new ArrayList<>();
                    //System.out.println(r.getPhysicalNumberOfCells());
                    for (int eachCell = 4; eachCell < 4+testMethod.getParameterCount()-1; eachCell++) {
                        String celldata = r.getCell(eachCell).getStringCellValue();
                        allCellsData.add(celldata);
                    }
                    allCellsData.add(String.valueOf(eachRow));
                    allRowsTestData.add(allCellsData.toArray(new String[0]));
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return allRowsTestData.toArray(new String[0][0]);

    }

    //method to read test case data in BDD framework
    public static Map<String, String> getTestData(String tcId) {
        Map<String, String> data = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("testdata/"+PropertiesFileReader.getPropertyValue("config","testdata"));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row.getCell(1).getStringCellValue().equalsIgnoreCase(tcId)) {
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell headerCell = headerRow.getCell(j);
                        Cell dataCell = row.getCell(j);
                        if (headerCell != null && dataCell != null) {
                            data.put(headerCell.getStringCellValue(), dataCell.toString());
                        }
                    }
                    break;
                }
            }
            workbook.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
