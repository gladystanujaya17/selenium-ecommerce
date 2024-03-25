package steps;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MagentoPurchase {

	WebDriver driver;
	public int scc = 0;
	
	@Given("User is on Magento page")
	public void userIsOnMagentoPage() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\dell\\\\Downloads\\\\chromedriver-win64\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("User click my account")
	public void userClickMyAccount() {
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
		driver.findElement(By.linkText("My Account")).click();

		// Switching to new window
		for (String handle : driver.getWindowHandles()) {
		   driver.switchTo().window(handle);
		}
	}

	@Then("User login with the account")
	public void userLoginWithTheAccount() {
		// E-mail address
	    driver.findElement(By.id("email")).sendKeys("tanujayagladys@gmail.com");
	    
	    // Password
	    driver.findElement(By.id("pass")).sendKeys("mar8abc");
	    
	    // Login button
	    driver.findElement(By.id("send2")).click();
	}

	@When("User click My Wishlist")
	public void userClickMyWishlist() {
	    driver.findElement(By.linkText("MY WISHLIST")).click();
	}

	@Then("User click Add to Cart")
	public void userClickAddToCart() {
	    driver.findElement(By.xpath("//*[@id=\"item_75830\"]/td[5]/div/button")).click();
	}

	@Then("User click Proceed to Checkout")
	public void userClickProceedToCheckout() {
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/div[1]/ul/li/button")).click();
	}

	@Then("User enter shipping information")
	public void userEnterShippingInformation() {
		WebElement radioShipping = driver.findElement(By.xpath("//*[@id=\"billing:use_for_shipping_yes\"]"));
		radioShipping.click();
		
		// Continue button in first step (BILLING INFORMATION)
		driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button")).click();
	}

	@Then("User click Continue button")
	public void userClickContinueButton() {
		// Continue button in third step (SHIPPING METHOD)
	    driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button")).click();
	}

	@Then("User choose Payment Information")
	public void userChoosePaymentInformation() {
	    // Radio button
		WebElement radioPayment = driver.findElement(By.xpath("//*[@id=\"p_method_checkmo\"]"));
		radioPayment.click();
		
		// Continue button
		driver.findElement(By.xpath("//*[@id=\"payment-buttons-container\"]/button")).click();
	}

	@Then("Verify shipping cost is added to total")
	public void verifyShippingCostIsAddedToTotal() throws IOException, InterruptedException {
		// Scroll down
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scroll(0, 300)");
		
		// Check the shipping cost
	    String shippingCost = driver.findElement(By.xpath("//*[@id=\"checkout-review-table\"]/tfoot/tr[2]/td[2]/span")).getText();
	    System.out.println("Shipping cost: " + shippingCost);
	}

	@Then("User click Place Order")
	public void userClickPlaceOrder() {
	    driver.findElement(By.xpath("//*[@id=\"review-buttons-container\"]/button")).click();
	}

	@Then("Verify Order is generated")
	public void verifyOrderIsGenerated() throws InterruptedException {
		String verifyMsg = driver.findElement(By.cssSelector("h1")).getText();
		System.out.println("Message: " + verifyMsg);
	    Thread.sleep(2000);
		driver.quit();
	}

	
}
