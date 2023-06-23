package com.SauceDemo.pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SaucedemoLoginPages {

	WebDriver driver;

		public SaucedemoLoginPages(WebDriver driver) {
			super();
		this.driver = driver;
	}

	//1. Login to https://www.saucedemo.com/ using username "standard_user" and password "secret_sauce" 
	private By username = By.xpath("//input[@name='user-name']");
	private By password = By.xpath("//input[@name='password']");
	private By loginbutton = By.xpath("//input[@name='login-button']");
	
	public SaucedemoLoginPages() {
		PageFactory.initElements(driver, this);
	}
	
	public SaucedemoProductPage enterCredential1(String uname, String pward) {
				driver.findElement(username).sendKeys(uname);
				driver.findElement(password).sendKeys(pward);
				driver.findElement(loginbutton).click();
				return new SaucedemoProductPage(driver);		
	}
	
	public void clickLogin() {
		driver.findElement(loginbutton).click();
		
	}
				

		
		

	
	
	
}