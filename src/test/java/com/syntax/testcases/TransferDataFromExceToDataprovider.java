package com.syntax.testcases;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.syntax.pages.AddEmployeePage;
import com.syntax.pages.HomePage;
import com.syntax.pages.LoginPage;
import com.syntax.utils.BaseClass;
import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;

public class TransferDataFromExceToDataprovider extends BaseClass {

	public static XSSFWorkbook workbook;
	
	public static XSSFSheet worksheet;
	
	public static DataFormatter formatter= new DataFormatter();
	
	
	@Test(groups= "regression",dataProvider="Excel")
	public void addEmployee(String fName, String mName,String lName, String location) throws InterruptedException {

		LoginPage login = new LoginPage();
		HomePage home =new HomePage();
		AddEmployeePage addEmp=new AddEmployeePage();
		//login to the OrangeHRM
		login.Login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
		//navigate to add Employee
		CommonMethods.click(home.PIM);
		CommonMethods.click(home.addEmployee);
		//enter employee details
		CommonMethods.sendText(addEmp.EmpFirstName, fName);
		
		CommonMethods.sendText(addEmp.EmpLastName, lName);
		CommonMethods.waitForElementBeClickable(addEmp.locationDropDown, 20);
		CommonMethods.click(addEmp.locationDropDown);
		CommonMethods.selectList(addEmp.locationList, location);
		CommonMethods.click(addEmp.EmpSaveBtn);
		//verify employee is added
		CommonMethods.waitForElementBeClickable(addEmp.empCheck, 20);
		String verifText=addEmp.empCheck.getText();
		System.out.println(verifText);
		Assert.assertEquals(verifText, fName+" "+lName);
	
	}
	
	
	
	@DataProvider(name= "Excel")
    public static Object[][] ReadVariant() throws IOException
    {
		String file_location="src/test/resources/testdata/OrangeHrmData.xlsx";
		String SheetName= "Locations";
    FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
        worksheet=workbook.getSheet(SheetName);// get my sheet from workbook
        XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   
     
        int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum= Row.getLastCellNum(); // get last ColNum 
         
        Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
         
            for(int i=0; i<RowNum-1; i++) //Loop work for Rows
            {  
                XSSFRow row= worksheet.getRow(i+1);
                 
                for (int j=0; j<ColNum; j++) //Loop work for colNum
                {
                    if(row==null)
                        Data[i][j]= "";
                    else
                    {
                        XSSFCell cell= row.getCell(j);
                        if(cell==null)
                            Data[i][j]= ""; //if it get Null value it pass no data 
                        else
                        {
                            String value=formatter.formatCellValue(cell);
                            Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                        }
                    }
                }
            }
 
        return Data;
    }
}
	

