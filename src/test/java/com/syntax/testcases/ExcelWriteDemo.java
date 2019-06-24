package com.syntax.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.syntax.utils.BaseClass;

public class ExcelWriteDemo {
    
    @Test
    
    public void writeExcel() throws Exception {
        String xlPath="src/test/resources/testdata/OrangeHrmData.xlsx";
        
        FileInputStream fis=new FileInputStream(xlPath);
        
        //Open WorkBook
        
        XSSFWorkbook wbook=new XSSFWorkbook(fis);
        
        XSSFSheet sheet=wbook.getSheet("Locations");
        
        // write to the excel
        
        sheet.getRow(0).createCell(4).setCellValue("Result");
        
        sheet.getRow(1).createCell(4).setCellValue("Pass");
        
        sheet.getRow(2).createCell(4).setCellValue("Fail");

        sheet.getRow(3).createCell(4).setCellValue("Pass");
        
        sheet.getRow(4).createCell(4).setCellValue("Check it again");
        

        //write to excel
        FileOutputStream fos= new FileOutputStream(xlPath);
        wbook.write(fos);
        //closer file and opened streeams
        fos.close();
        wbook.close();
        fis.close();
    
        
        
        
    }

}