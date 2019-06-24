package com.syntax.testcases;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.syntax.pages.HomePage;
import com.syntax.pages.LoginPage;
import com.syntax.pages.LoginPageWithoutPageFactory;
import com.syntax.utils.BaseClass;
import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;
import com.syntax.utils.Constants;

public class LoginPageTest extends CommonMethods{

	@Test(enabled = false)
	public void loginToOrangeHRM() {
        
		LoginPage login=new LoginPage();
		
      ConfigsReader.readProperties(Constants.CREDENTIALS_FILEPATH);
      String username= ConfigsReader.getProperty("username");
      String password= ConfigsReader.getProperty("password");
        
		sendText(login.userName, username);
		sendText(login.password, password);
		click(login.btnLogin);

	   HomePage home=new HomePage();
	   Assert.assertEquals(home.dashboard.isDisplayed(), true);
	   System.out.println("Successfully login!");	
	}
	
	
	@Test(enabled = true)
	 public void doLogin() {
		
		
	        LoginPage login= new LoginPage();
	        ConfigsReader.readProperties(Constants.CREDENTIALS_FILEPATH);
	        CommonMethods.sendText(login.userName,ConfigsReader.getProperty("username"));
	        CommonMethods.sendText(login.password,ConfigsReader.getProperty("password"));
	        CommonMethods.click(login.btnLogin);
	        HomePage home=new HomePage();
	        Assert.assertEquals(home.dashboard.isDisplayed(), true);
	        System.out.println("Successfully login!");
	        
}
	
	@Test
	public void negativeLogin() {
		
	LoginPage login= new LoginPage();
		
	sendText(login.userName, "Admin");
	sendText(login.password, "ljowiew");
	login.btnLogin.click();
	String message= login.message.getText();
	Assert.assertEquals(message, "Invalid Credentials");
	
	
		
		
	}
}