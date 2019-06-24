package com.syntax.practices;

import org.testng.annotations.Test;

import com.syntax.utils.Constants;
import com.syntax.utils.ExcelUtility;

public class ExcelFunctionsTest {

	@Test
	public void excelTest() {
		
		ExcelUtility obj=new ExcelUtility();
		obj.openExcel(Constants.XL_FILEPATH, "Locations");
		String value = obj.getCellData(2, 3);
		System.out.println(value);
	
	}
}