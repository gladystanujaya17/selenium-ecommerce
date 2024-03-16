package steps;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MagentoSavePDF {

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

	@When("User click on My Account")
	public void userClickOnMyAccount() {
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
		driver.findElement(By.linkText("My Account")).click();

		// Switching to new window
		for (String handle : driver.getWindowHandles()) {
		   driver.switchTo().window(handle);
		}
	}

	@Then("User login the application")
	public void userLoginTheApplication() {
		// E-mail address
	    driver.findElement(By.id("email")).sendKeys("tanujayagladys@gmail.com");
	    
	    // Password
	    driver.findElement(By.id("pass")).sendKeys("mar8abc");
	    
	    // Login button
	    driver.findElement(By.id("send2")).click();
	}

	@Then("User click My Orders")
	public void userClickMyOrders() {
	    driver.findElement(By.linkText("MY ORDERS")).click();
	}

	@Then("User click View Order")
	public void userClickViewOrder() {
		// Check the order status is "Pending" or not
		try {
			assertEquals("Pending", driver.findElement(By.cssSelector("em")).getText());
		} catch (Exception e) {
	    	System.out.println("*** Status of Pending failed to be confirmed for this recent order. ***");
	    	e.printStackTrace();  
		}
		
	    driver.findElement(By.xpath("//*[@id=\"my-orders-table\"]/tbody/tr[1]/td[6]/span/a[1]")).click();
	}

	@When("User verify order is displayed in Recent Orders and status is pending")
	public void userVerifyOrderIsDisplayedInRecentOrdersAndStatusIsPending() {
	    String verifyOrder = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div[1]/h1")).getText();
	    System.out.println("Order No: " + verifyOrder);
	}

	@Then("User click Print Order")
	public void userClickPrintOrder() {
	    driver.findElement(By.linkText("Print Order")).click();;
	}

	@Then("User verify order is saved as PDF")
	public void userVerifyOrderIsSavedAsPDF() throws InterruptedException, IOException {
		// Gatau cara buktiin save PDF gimana jadi pake screenshot saja xD
		
		// Switching to new window                                                                               
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    	}
	    		
		// This will take a screenshot of the page after a successful login
		scc = (scc + 1);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String png = ("D:\\Guru99 eCommerce Live Project\\Day07_TestCase7\\Print Order Screenshot " + scc + ".png");
		FileUtils.copyFile(scrFile, new File(png));
		
	    Thread.sleep(2000);
	    driver.quit();
	}
}
