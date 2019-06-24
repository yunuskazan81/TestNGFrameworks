package com.syntax.practices;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.syntax.pages.AddEmployeePage;
import com.syntax.pages.HomePage;
import com.syntax.pages.LoginPage;
import com.syntax.utils.BaseClass;
import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;
import com.syntax.utils.Constants;
import com.syntax.utils.ExcelUtility;

public class ExcetoDataProvider extends BaseClass {
	
	
	@Test(dataProvider="Excel")
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
	
	@DataProvider(name="Excel")
	public Object[][]  excelTest() {
		
		ExcelUtility obj= new ExcelUtility();
		
		obj.openExcel(Constants.XL_FILEPATH, "Locations");
		
		
		
		int row= obj.getRowNum();
		int colm= obj.getColNum(0);
		
		
		Object  [][] data = new Object[row-1][colm];
		
		for(int i=1; i<row; i++) {
			for(int j=0; j<colm; j++) {
				
				String cellData= obj.getCellData(i, j);
				
				data[i-1][j]= cellData;
				
				//System.out.print(data[i-1][j]+" ");
			}
			
		//System.out.println(" "+" ");
		}
		return data;
	
		
	}
}
