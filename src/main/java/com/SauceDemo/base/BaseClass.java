package com.SauceDemo.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.SauceDemo.dataprovider.ConfigUtlity;
import com.SauceDemo.factory.BrowserFactory;

public class BaseClass {

	public WebDriver driver;
	Logger logger;
	@BeforeClass
	public void setup() {
		System.out.println("LOG:INFO-Setup is running");
		driver = BrowserFactory.startBrowser(ConfigUtlity.getValue("browser"));
		driver.get(ConfigUtlity.getValue("url"));
		
//		logger=Logger.getLogger("atmosol");
//		PropertyConfigurator.configure("log4j.properties");
//		System.out.println("LOG:INFO-Browser is up and running");
	}

	@AfterClass
	public void tearDown() {
		System.out.println("LOG:INFO-Teardown is running");
		BrowserFactory.closeBrowser(driver);
		// Assert.assertNull(driver);
		System.out.println("LOG:INFO-Session terminated");
	}
	
	public static void captureScreenshot(WebDriver driver , String tname) {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File dest = new File("./Screenshots/eBay_" + getCurrentDateTime() + ".png");

		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			System.out.println("Something went wrong while copying file " + e.getMessage());
		}

	}
	public static String getCurrentDateTime() {

		return new SimpleDateFormat("HH_mm_ss_dd_MMM_yyyy").format(new Date());

		/*
		 * Date currentDate=new Date();
		 * 
		 * SimpleDateFormat myDateFormat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		 * 
		 * System.out.println(myDateFormat.format(currentDate));
		 * 
		 * 
		 */

	}

}