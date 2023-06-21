package Atmosol;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.atmosol.base.BaseClass;
import com.atmosol.pages.SaucedemoCheckoutPages;
import com.atmosol.pages.SaucedemoLoginPages;
import com.atmosol.pages.SaucedemoProductPage;

import com.atmosol.dataprovider.DataProviders;

public class LoginTest extends BaseClass {
	SaucedemoLoginPages loginpage;
	SaucedemoProductPage product;
	SaucedemoCheckoutPages checkout;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void pagecall() {
		loginpage = new SaucedemoLoginPages(driver);
	}

	@BeforeMethod
	public void productPageCall() {
		product = new SaucedemoProductPage(driver);
	}
	
	@BeforeMethod
	public void productCheckoutCall() {
		checkout = new SaucedemoCheckoutPages(driver);
	}

	@Test(description = "Login", priority = 1, dataProvider = "login", dataProviderClass = DataProviders.class)

	public void LoginCredential(String username, String password) {
		loginpage = new SaucedemoLoginPages(driver);
		SaucedemoProductPage hpage = loginpage.enterCredential1(username, password);
		try {
			loginpage.clickLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());;
		}
	}

	@Test(description = "ProductPageMenu", priority = 2)

	public void PageMenu() {
		product = new SaucedemoProductPage(driver);
		product.OpenMenu();
		product.AboutUs();

	}

	@Test(description = "Page Redirection to saucelabs site", priority = 3)
	public void PageRedirectedToSaucelab() {
		product = new SaucedemoProductPage(driver);
		product.validatePage();
		String rurl = product.validatePage();
		System.out.println(rurl);
		Assert.assertEquals(rurl, "https://saucelabs.com/", "User is not redirected to Saucelabs site");

	}

	@Test(description = "Page Navigation to Product", priority = 4)
	public void PageNavigatedToProduct() {
		product = new SaucedemoProductPage(driver);
		product.validateNavigation();
		String nurl = product.validatePage();
		System.out.println(nurl);
		Assert.assertEquals(nurl, "https://www.saucedemo.com/inventory.html",
				"User is not navigated to Product Page");

	}

	@Test(description = "Sort Product From Dropdown", priority = 5)
	public void SortAllProduct() {
		product = new SaucedemoProductPage(driver);
		product.SelectSortProduct();
	}

	@Test(description = "List of All Price", priority = 6)
	public void AllPrice() {
		product = new SaucedemoProductPage(driver);
		product.VerifyListPrice();
		System.out.println(product);
	}

	@Test(description = "List of All Product", priority = 7)
	public void AllProduct() {
		product = new SaucedemoProductPage(driver);
		product.VerifyListProduct();
		System.out.println(product);
	}

	@Test(description = "Add to Cart", priority = 8)
	public void AddToCartButton() {
		product = new SaucedemoProductPage(driver);
		product.clickAddToCart();
	}
	
	@Test(description = "Shopping cart", priority = 9)
	public void ShoppingCart() {
		product = new SaucedemoProductPage(driver);
		product.clickShoppingCart();
	}

	@Test(description = "Verification on Navigated to cart", priority = 10)

	public void CartNavigation() {
		product = new SaucedemoProductPage(driver);
		product.CheckCartNavigation();
		String carttitle = product.CheckCartNavigation();
		System.out.println(carttitle);
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(carttitle, "Your Cart", "User is not navigated to cart");
	}

	@Test(description = "Click on Checkout Button", priority = 11)
	public void CheckoutButton() {
		product = new SaucedemoProductPage(driver);
		product.clickCheckOut();
	}
	

	@Test(description = "Sauce Product Checkout User Details", priority = 12, dataProvider = "userdetails", dataProviderClass = DataProviders.class)

	public void SauceCheckOut(String fname, String lname, String zipCode) {

		checkout = new SaucedemoCheckoutPages(driver);
		SaucedemoCheckoutPages pcheckout = checkout.EnterSauceCheckOut(fname, lname, zipCode);
		checkout.Clickcontinue();
	}

	@Test(description = "Navigated CHECKOUT: OVERVIEW", priority = 13)

	public void CheckoutOverviewNavigation() {
		checkout = new SaucedemoCheckoutPages(driver);
		checkout.validateCheckoutOverview();
		String checkouttitle = checkout.validateCheckoutOverview();
		System.out.println(checkouttitle);
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(checkouttitle, "product checkout",
				"User is not being navigated to CHECKOUT: info page");
	}

	@Test(description = "Checkout price format", priority = 14)
	public void Priceformat() {
		checkout = new SaucedemoCheckoutPages(driver);
		checkout.CheckProductTotalP();
		WebElement checkouturl = checkout.CheckProductTotalP();
		System.out.println(checkouturl);		
		Assert.assertEquals("Total: $53.99", "Total: $53.99"); 


	}

	@Test(description = "Process Finished", priority = 15)
	public void Finish() {
		checkout = new SaucedemoCheckoutPages(driver);
		checkout.clickFinish();

	}

}
