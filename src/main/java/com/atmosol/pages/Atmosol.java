package com.atmosol.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Atmosol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium webdriver\\ChromeDriver\\chromedriver.exe");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.saucedemo.com");
		System.out.println(driver.getCurrentUrl());
		
		//1. Login to https://www.saucedemo.com/ using username "standard_user" and password "secret_sauce" 
		driver.findElement(By.xpath("//input[@name='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@name='login-button']")).click();

		//2. Click on Open Menu button on the top left corner (Its a small box with 3 lines) 
		driver.findElement(By.xpath("//button[text()='Open Menu']")).click();

		//3. Select ' About' 
		driver.findElement(By.xpath("//nav[@class='bm-item-list']/descendant::a[text()='About']")).click();

		// 4. Check if user is taken to https://saucelabs.com/ site
		System.out.println(driver.getCurrentUrl());
		String newurl = driver.getCurrentUrl();
		try {
			Assert.assertEquals(newurl, "https://saucelabs.com/", "user is not taken ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

// 5. Click on browser back button and Validate if you are taken back to saucedemo PRODUCTS page 

		driver.navigate().back();
		String producturl = driver.getCurrentUrl();
		Assert.assertEquals(producturl, "https://www.saucedemo.com/inventory.html", "user is not taken ");
		
		
//6. Select the item with highest price (The code should dynamically select the highest priced product) 
		
		//filter the price from dropdown
		WebElement sortList = driver.findElement(By.className("product_sort_container"));
		Select select= new Select(sortList);
		select.selectByVisibleText("Price (high to low)");
		
		//list all the products price 
		List<WebElement> priceElements= driver.findElements(By.cssSelector("[class='inventory_item_price']"));
		for (int i=0; i<priceElements.size();i++) {
			System.out.println(Integer.parseInt(priceElements.get(i).getText().replaceAll("[^a-zA-Z0-9\\s]", "")));
			
		}
		
		//list all the products List 
		List<WebElement> nameElements= driver.findElements(By.cssSelector("[class='inventory_item_name']"));
		for (int i=0; i<nameElements.size();i++) {
			System.out.println(nameElements.get(i).getText());
		}
		
		//select 1st highest item
		nameElements.get(0).click();
		
		//7. Click on the Cart button available at the top right corner  		
		driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		
		//Click on Cart
		driver.findElement(By.cssSelector("[class='shopping_cart_link']")).click();
				
		// 8. Validate if you are taken to YOUR CART page
		String carttitle = driver.getTitle();
		SoftAssert softassert=new SoftAssert();
		softassert.assertEquals(carttitle, "Your Cart", "User is not navigated to cart");
		
		
		
		// 9. Click Checkout
		driver.findElement(By.name("checkout")).click();
		
		// 10. Validate if you are navigated to CHECKOUT: YOUR INFORMATION page.
		String Echeckoutinfo = "Checkout: Your Information";
		String Acheckoutinfo = "Checkout: Your Information";
		try {
			Assert.assertTrue(Echeckoutinfo.contains(Acheckoutinfo), "User is not being navigated to CHECKOUT: info page");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

//. Enter all details in the text boxes and click CONTINUE 
		driver.findElement(By.name("firstName")).sendKeys("standard");
		driver.findElement(By.name("lastName")).sendKeys("user");
		driver.findElement(By.id("postal-code")).sendKeys("560016");
		driver.findElement(By.name("continue")).click();
		
//. Validate if you are navigated to CHECKOUT: OVERVIEW & Total Price is shown in $xx.yy format 
		// Verify the page title contains "CHECKOUT: OVERVIEW"
		String EcheckoutOverView = "CHECKOUT: OVERVIEW";
		String AcheckoutOverView = "CHECKOUT: OVERVIEW";
		Assert.assertEquals(EcheckoutOverView, AcheckoutOverView, "Not navigated to CHECKOUT: OVERVIEW page");

		// Verify if the total price is displayed in the format "$xx.yy"
		String totalPriceText = driver.findElement(By.cssSelector("[class='summary_info_label summary_total_label']")).getText();
		System.out.println("Total Price: " + totalPriceText);

		Assert.assertEquals(totalPriceText, "Total: $53.99", "Total price is not displayed in the format \"$xx.yy\"" );
		
		//click on finish
		driver.findElement(By.id("finish")).click();
		
			driver.quit();

	}

}
