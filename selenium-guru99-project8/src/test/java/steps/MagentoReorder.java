package steps;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MagentoReorder {

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

	@When("User click Reorder")
	public void userClickReorder() {
	    driver.findElement(By.xpath("//*[@id=\"my-orders-table\"]/tbody/tr[5]/td[6]/span/a[2]")).click();
	}

	@Then("User change the Quantity and update it")
	public void userChangeTheQuantityAndUpdateIt() throws InterruptedException {
	    WebElement inputQty = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/input"));
	    Thread.sleep(2000);
	    inputQty.clear();
	    inputQty.click();
	    inputQty.sendKeys("10");
	    driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/button")).click();
	}

	@Then("User verify the grand total is changed")
	public void userVerifyTheGrandTotalIsChanged() {
	    String grandTotalText = driver.findElement(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span")).getText();
	    System.out.println("Grand Total after changed: " + grandTotalText);
	}

	@When("User click Proceed to Checkout")
	public void userClickProceedToCheckout() {
	    driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/div[3]/div/ul/li[1]/button")).click();
	}

	@Then("User complete the shipping information")
	public void userCompleteTheShippingInformation() {
		// 1. Billing Information
		WebElement radioShipping = driver.findElement(By.xpath("//*[@id=\"billing:use_for_shipping_yes\"]"));
		radioShipping.click();
		
		// Continue button in first step (BILLING INFORMATION)
		driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button")).click();
		
		// 3. Shipping Method
		String flatRate = driver.findElement(By.xpath("//*[@id=\"checkout-shipping-method-load\"]/dl/dd/ul/li/label")).getText();
		System.out.println("Flat rate: " + flatRate);
		
		// Continue button to fourth step (PAYMENT INFORMATION)
		driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button")).click();
		
		// 4. Payment Method
		WebElement radioPayment = driver.findElement(By.xpath("//*[@id=\"p_method_checkmo\"]"));
		radioPayment.click();
		
		// Continue button to fifth step (ORDER REVIEW)
		driver.findElement(By.xpath("//*[@id=\"payment-buttons-container\"]/button")).click();
		
		// 5. ORDER REVIEW
		// Click Place Order
	    driver.findElement(By.xpath("//*[@id=\"review-buttons-container\"]/button")).click();
	}

	@Then("User verify order is generated")
	public void userVerifyOrderIsGenerated() throws InterruptedException, IOException {
		String verifyMsg = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div[1]/h1")).getText();
		System.out.println("Message: " + verifyMsg);
		
		// Take screenshot as a proof
		scc = (scc + 1);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String png = ("D:\\Guru99 eCommerce Live Project\\Day08_TestCase8\\Reorder Screenshot " + scc + ".png");
		FileUtils.copyFile(scrFile, new File(png));
		
	    Thread.sleep(2000);
		driver.quit();
	}
}
