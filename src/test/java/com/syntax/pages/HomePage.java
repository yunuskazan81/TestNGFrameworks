package com.syntax.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.syntax.utils.BaseClass;

public class HomePage extends BaseClass{

	@FindBy(xpath="//li[text()='Dashboard']")
    public WebElement dashboard;
	
	@FindBy(xpath= "//span[text()='PIM']")
	public WebElement PimMenu;
	
	@FindBy(xpath= "//span[text()='Add Employee']")
	public WebElement AddEmp;
	
	@FindBy(partialLinkText="PIM")
    public WebElement PIM;
	
    @FindBy(partialLinkText="Add Employee")
    public WebElement addEmployee;
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
}
