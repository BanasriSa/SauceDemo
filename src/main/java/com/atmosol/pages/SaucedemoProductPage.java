package com.atmosol.pages;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SaucedemoProductPage {

	WebDriver driver;

	public SaucedemoProductPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	// 2. Click on Open Menu button on the top left corner (Its a small box with 3
	// lines)
	private By openMenuBtn = By.xpath("//button[text()='Open Menu']");
	// 3. click on aboutUs
	private By clickAboutUs = By.xpath("//nav[@class='bm-item-list']/descendant::a[text()='About']");
	private By sortproduct = By.className("product_sort_container");
	private By PrilceList = By.cssSelector("[class='inventory_item_price']");
	private By ProductList = By.cssSelector("[class='inventory_item_name']");
	private By addTocartbtn = By.xpath("//button[text()='Add to cart']");
	private By shoppingCart = By.cssSelector("[class='shopping_cart_link']");
	private By checkoutbtn=  By.name("checkout");

	public SaucedemoProductPage() {
		PageFactory.initElements(driver, this);
	}

	public void OpenMenu() {
		driver.findElement(openMenuBtn).click();

	}

	public void AboutUs() {
		driver.findElement(clickAboutUs).click();
	}

	// 4. Check if user is taken to https://saucelabs.com/ site
	public String validatePage() {
		return driver.getCurrentUrl();
	}

	// 5. Click on browser back button and Validate if you are taken back to
	// saucedemo PRODUCTS page
	public String validateNavigation() {
		driver.navigate().back();
		return driver.getCurrentUrl();
	}

	// 6. Select the item with highest price (The code should dynamically select the
	// highest priced product)

	// filter the price from dropdown
	public void SelectSortProduct() {
		WebElement sortList = driver.findElement(sortproduct);
		Select select = new Select(sortList);
		 select.selectByVisibleText("Price (high to low)");
	}

	// list all the products price
	public void VerifyListPrice() {
		List<WebElement> priceElements = driver.findElements(PrilceList);
		for (int i = 0; i < priceElements.size(); i++) {
			System.out.println(Integer.parseInt(priceElements.get(i).getText().replaceAll("[^a-zA-Z0-9\\s]", "")));
		}
	}

	// list all the products List
	public void VerifyListProduct() {
		List<WebElement> nameElements = driver.findElements(ProductList);
		for (int i = 0; i < nameElements.size(); i++) {
			System.out.println(nameElements.get(i).getText());
			// select 1st highest item
			nameElements.get(0).click();

		}
	}

	public void clickAddToCart() {
		driver.findElement(addTocartbtn).click();
	}

	// 7. Click on the Cart button available at the top right corner
		public void clickShoppingCart() {
			driver.findElement(shoppingCart).click();
		}
		
	// 8. Validate if you are taken to YOUR CART page
	public String CheckCartNavigation() {
		return driver.getTitle();
		
	}
	
	public void clickCheckOut() {
		driver.findElement(checkoutbtn).click();
	}

	
	

}