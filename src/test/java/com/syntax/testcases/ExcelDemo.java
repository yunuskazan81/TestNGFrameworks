package com.syntax.testcases;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelDemo {


    @Test
    public void readExcel() throws IOException {

        String xlPath="src/test/resources/testdata/OrangeHrmData.xlsx";

        FileInputStream fis =new FileInputStream(xlPath);

        XSSFWorkbook workbook=new XSSFWorkbook(fis);

        XSSFSheet sheet=workbook.getSheet("Locations");
        //accessing 2 row and 4 column
        String value = sheet.getRow(1).getCell(3).toString();

        System.out.println(value);
        //accessing 5 row and 1 column
        String value2=sheet.getRow(4).getCell(0).toString();
        System.out.println(value2);

        //how to find number of rows
        int rowNum=sheet.getPhysicalNumberOfRows();
        //how to find number of cells
        int colNum=sheet.getRow(0).getLastCellNum();

        System.out.println(rowNum);
        System.out.println(colNum);

        //retrieve all data

        for (int i=0; i<rowNum; i++) {
            for (int j=0; j<colNum; j++) {
                String cellValue=sheet.getRow(i).getCell(j).toString();
                System.out.print(cellValue+"  ");
            }
            System.out.println();
        }
        workbook.close();
        fis.close();
    }

}
