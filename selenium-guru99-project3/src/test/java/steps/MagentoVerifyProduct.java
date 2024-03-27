package steps;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MagentoVerifyProduct {

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

	@When("User click mobile link button")
	public void userClickMobileLinkButton() {
		WebElement buttonMobile = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
		buttonMobile.click();
	}

	@Then("User is in Mobile page")
	public void userIsInMobilePage() {
	    System.out.println("Mobile page text: " + driver.findElement(By.cssSelector("h1")).getText());
		boolean mobileText = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[1]/h1")).isDisplayed();
		Assert.assertTrue(mobileText);
		
	}

	@When("User click Add Cart in Sony Xperia mobile")
	public void userClickAddCartInSonyXperiaMobile() {
		driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/button")).click();
	}

	@Then("User change the QTY")
	public void userChangeTheQTY() {
		// Using CSS Selectors ID
		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();	    
	    driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("1000");	
	    driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();
	}

	@When("user click Empty Cart")
	public void userClickEmptyCart() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")));
		System.out.println("Error message: " + driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")).getText());
	}

	@Then("verify cart is empty")
	public void verifyCartIsEmpty() throws IOException, InterruptedException {
	    driver.findElement(By.xpath(".//*[@id='empty_cart_button']")).click();
	    scc = (scc + 1);
	    File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    String png = ("D:\\Guru99 eCommerce Live Project\\Day03_TestCase3\\Cart is empty " + scc + ".png");
	    FileUtils.copyFile(screenshotFile, new File(png));
	    Thread.sleep(2000);
	    driver.quit();
	}
	
	@Then("User rechange the QTY")
	public void userRechangeTheQTY() {
		// Using CSS Selectors ID
		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();	    
		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("10");	
		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();
	}

	@Then("User verify quantity is changed")
	public void userVerifyQuantityIsChanged() throws IOException, InterruptedException {
	    scc = (scc + 1);
	    File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    String png = ("D:\\Guru99 eCommerce Live Project\\Day03_TestCase3\\Qty is changed " + scc + ".png");
	    FileUtils.copyFile(screenshotFile, new File(png));
	    Thread.sleep(2000);
	    driver.quit();
	}

}
