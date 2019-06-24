package com.syntax.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.syntax.utils.BaseClass;
import com.syntax.utils.CommonMethods;
public class LoginPage extends BaseClass{

		
		//locating WebElement using @FindBy annotation
		@FindBy(id="txtUsername")
		public WebElement userName;
		
		@FindBy(name="txtPassword")
		public WebElement password;
		
		@FindBy(id="btnLogin")
		public WebElement btnLogin;
		
		@FindBy(css="img[src*='logo.png']")
		public WebElement logo;
		
		
		@FindBy(xpath="//div[@class='toast-message']")
		public WebElement message;
		
		
		//initialize all of our variables
		public LoginPage() {
			PageFactory.initElements(driver, this);
		}
				
		public void Login(String uname, String pwd) {
			
			CommonMethods.sendText(userName, uname);
			CommonMethods.sendText(password, pwd);
			CommonMethods.click(btnLogin);
			
	   }
		
		
	}

