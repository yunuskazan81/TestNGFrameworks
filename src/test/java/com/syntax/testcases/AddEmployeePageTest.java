package com.syntax.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.Test;

import com.syntax.pages.AddEmployeePage;
import com.syntax.pages.HomePage;
import com.syntax.pages.LoginPage;
import com.syntax.utils.BaseClass;
import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;


	/*
	
	US24548: Add Employee Details

TestCase: Add Employee Verification
Step 1: Login to the OrangeHRM
Step 2: Click on PIM
Step 3: Click on Add Employee
Step 4: Enter fName, lName, select Location
Step 5 Click Save
Step 6: Verify employee is added

	 */


	public class AddEmployeePageTest extends CommonMethods {

		@Test
		public void addEmployee() throws InterruptedException {

			LoginPage login = new LoginPage();
			HomePage home =new HomePage();
			AddEmployeePage addEmp=new AddEmployeePage();
			
			login.Login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
			
			click(home.PIM);
			click(home.addEmployee);
			
			sendText(addEmp.EmpFirstName, "John");
			sendText(addEmp.EmpLastName, "Mikence");
			sendText(addEmp.EmployeeID, "00012");
			
			 click(addEmp.locationDropDown);
			
			 List<WebElement> listLocations = addEmp.locationList.findElements(By.tagName("li"));
				
				for (WebElement li : listLocations) {
					CommonMethods.waitForElementBeClickable(li, 10);
					String liText=li.getText();
					if (liText.contains("HeadQuarter")) {
						li.click();
						break;
					}
				}
			
			
				click(addEmp.checkCredentials);
				
				sendText(addEmp.username, "JohnMikence");
				sendText(addEmp.password, "yK198781.");
				sendText(addEmp.confirmPass, "yK198781.");
				
				click(addEmp.status);
				
				List<WebElement> EmpStatus = addEmp.statusList.findElements(By.tagName("li"));
				
				for (WebElement statusli : EmpStatus) {
					String liText=statusli.getText().trim();
					
					if (liText.equals("Enabled")) {
						click(statusli);
						break;
					}
				}
				
				selectValueFromDD(addEmp.AdminRole, "Global Admin");
				click(addEmp.EmpSaveBtn);
				
				Thread.sleep(10000);
				
				
		}

	}

