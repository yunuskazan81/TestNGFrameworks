package com.syntax.practices;

import org.testng.Assert;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class VerifyDownloadfile {
	
		private WebDriver driver;
		
		private String downloadPath = "/src/test/resource";
		private String URL="http://spreadsheetpage.com/index.php/file/C35/P10/"; 
		
		
		public boolean isFileDownloaded(String downloadPath, String fileName) {
			boolean flag = false;
		    File dir = new File(downloadPath);
		    File[] dir_contents = dir.listFiles();
		  	    
		    for (int i = 0; i < dir_contents.length; i++) {
		        if (dir_contents[i].getName().equals(fileName))
		            return flag=true;
		            }

		    return flag;
		}
		
		
		private boolean isFileDownloaded_Ext(String dirPath, String ext){
			boolean flag=false;
		    File dir = new File(dirPath);
		    File[] files = dir.listFiles();
		    if (files == null || files.length == 0) {
		        flag = false;
		    }
		    
		    for (int i = 1; i < files.length; i++) {
		    	if(files[i].getName().contains(ext)) {
		    		flag=true;
		    	}
		    }
		    return flag;
		    
		   
		}
		
		
		
		/* Get the latest file from a specific directory*/
		private File getLatestFilefromDir(String dirPath){
		    File dir = new File(dirPath);
		    File[] files = dir.listFiles();
		    if (files == null || files.length == 0) {
		        return null;
		    }
		
		    File lastModifiedFile = files[0];
		    for (int i = 1; i < files.length; i++) {
		       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
		           lastModifiedFile = files[i];
		       }
		    }
		    return lastModifiedFile;
		}
		
		@BeforeClass
		public void testSetup() throws Exception{
			
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");			
			driver = new ChromeDriver();	
//			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().fullscreen();
			driver.manage().window().maximize();
		}
		
		@Test
		public void example_VerifyDownloadWithFileName()  {
			driver.get(URL);
		    driver.findElement(By.linkText("mailmerge.xls")).click();
		    Assert.assertTrue(isFileDownloaded(downloadPath, "mailmerge.xls"), "Failed to download Expected document");
		}
		
	        @Test
		public void example_VerifyDownloadWithFileExtension()  {
			driver.get(URL);
		    driver.findElement(By.linkText("mailmerge.xls")).click();
		    Assert.assertTrue(isFileDownloaded_Ext(downloadPath, ".xls"), "Failed to download document which has extension .xls");
		}

		@Test
		public void example_VerifyExpectedFileName() {
			driver.get(URL);
		    driver.findElement(By.linkText("mailmerge.xls")).click();
		    File getLatestFile = getLatestFilefromDir(downloadPath);
		    String fileName = getLatestFile.getName();
		    Assert.assertTrue(fileName.equals("mailmerge.xls"), "Downloaded file name is not matching with expected file name");
		}
		
		
		
		

		@AfterClass
		public void tearDown() {
			driver.quit();
		}

}
