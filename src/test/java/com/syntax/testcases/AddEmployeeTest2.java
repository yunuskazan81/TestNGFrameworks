package com.syntax.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.syntax.pages.AddEmployeePage;
import com.syntax.pages.HomePage;
import com.syntax.pages.LoginPage;
import com.syntax.utils.BaseClass;
import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;

public class AddEmployeeTest2 extends BaseClass {

	@Test(dataProvider="employee details")
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
		CommonMethods.click(addEmp.locationDropDown);
		CommonMethods.selectList(addEmp.locationList, location);
		CommonMethods.click(addEmp.EmpSaveBtn);
		//verify employee is added
		CommonMethods.waitForElementBeClickable(addEmp.empCheck, 20);
		String verifText=addEmp.empCheck.getText();
		System.out.println(verifText);
		Assert.assertEquals(verifText, fName+" "+lName);
	}
	
	@DataProvider(name = "employee details")
	public Object[][] getData() {
		
		Object[][] data=new Object[3][4];
		//1 set
		data[0][0]="John";
		data[0][1]="Snow";
		data[0][2]="White";
		data[0][3]="HQ";
		//2 set
		data[1][0]="Jane";
		data[1][1]="Rain";
		data[1][2]="Yellow";
		data[1][3]="West Office";
		//3 set
		data[2][0]="Arya";
		data[2][1]="Sunny";
		data[2][2]="Blue";
		data[2][3]="North Office";
		return data;
	}
}
