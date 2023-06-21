package com.atmosol.pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SaucedemoCheckoutPages {

	WebDriver driver;

	public SaucedemoCheckoutPages(WebDriver driver) {
		this.driver = driver;
	}

	// enter firstname
	private By firstname = By.name("firstName");
	private By lastname = By.name("lastName");
	private By zipcode= By.id("postal-code");
	private By continuebtn= By.name("continue");
	private By producttotalPrice= By.cssSelector("[class='summary_info_label summary_total_label']");
	private By finishBtn = By.id("finish");

	public SaucedemoCheckoutPages() {
		PageFactory.initElements(driver, this);
	}
	
	//pass username passwordfirstname,lastname,zipcode
	public SaucedemoCheckoutPages EnterSauceCheckOut(String fname, String lname, String zipCode) {
		driver.findElement(firstname).sendKeys(fname);
		driver.findElement(lastname).sendKeys(lname);
		driver.findElement(zipcode).sendKeys(zipCode);	
		return new SaucedemoCheckoutPages(driver);		
	}
	
	
		public void Clickcontinue() {
		driver.findElement(continuebtn).click();
		}
		
		//Validate if you are navigated to CHECKOUT: OVERVIEW
		public String validateCheckoutOverview() {
			return driver.getCurrentUrl();
		}
		
		// Verify if the total price is displayed in the format "$xx.yy"
		public WebElement CheckProductTotalP() {
			 return driver.findElement(producttotalPrice);
		}
		
		public void clickFinish() {
			driver.findElement(finishBtn).click();
		}

	

		
		

		

		
				


		

		
		

	
	
	
}