package com.atmosol.dataprovider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	
	@DataProvider(name="login")
	public static Object[][] getDataForPrice() throws IOException
	{
		return	ExcelUtility.getDataFromSheet("Login");
	}
	
	@DataProvider(name="userdetails")
	public static Object[][] getDataForUserChk() throws IOException
	{
		return ExcelUtility.getDataFromSheet("UserDetails");
	}
	
	
	@DataProvider(name="crmlogin")
	public static Object[][] getDataForSearch() throws IOException
	{
		return ExcelUtility.getDataFromSheet("crmlogin");
	}
	
}