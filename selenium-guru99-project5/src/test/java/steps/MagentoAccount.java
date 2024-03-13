package steps;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MagentoAccount {

	WebDriver driver;
	public String firstName = "Gladys";      
	public String lastName = "Tanujaya";
	
	@Given("User is on Magento page")
	public void userIsOnMagentoPage() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\dell\\\\Downloads\\\\chromedriver-win64\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("User click My Account link")
	public void userClickMyAccountLink() {
	    driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
	    driver.findElement(By.linkText("My Account")).click();

	    // Switching to new window
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    }

	}

	@Then("User click Create Account")
	public void userClickCreateAccount() {
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div/div[1]/div[2]/a")).click();
	}

	@Then("User fill user information")
	public void userFillUserInformation() {
	    // Fill the first name
		driver.findElement(By.id("firstname")).sendKeys("Gladys");
		
		// Fill the last name
		driver.findElement(By.id("lastname")).sendKeys("Tanujaya");
		
		// E-mail 
		driver.findElement(By.id("email_address")).sendKeys("tanujayagladys@gmail.com");
		
		// Password
		driver.findElement(By.id("password")).sendKeys("mar8abc");
		
		// Confirm Password
		driver.findElement(By.id("confirmation")).sendKeys("mar8abc");
		
		// Click Register button
		driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();
	}

	@Then("User verify registration is done")
	public void userVerifyRegistrationIsDone() {
		String helloMessage = ("Hello, " + firstName + " " + lastName + "!");
	    String verifMsg = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div/div[2]/p[1]/strong")).getText();
	    try {
	    	assertEquals(helloMessage, verifMsg);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	@When("User go to TV menu")
	public void userGoToTVMenu() {
	    driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[2]/a")).click();
	}

	@Then("User add product")
	public void userAddProduct() {
	    driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/ul/li[1]/a")).click();

	}
	
	@When("User click SHARE WISHLIST")
	public void userClickSHAREWISHLIST() {
	    driver.findElement(By.xpath("//*[@id=\"wishlist-view-form\"]/div/div/button[1]")).click();
	}

	@Then("User enter e-mail and message")
	public void userEnterEMailAndMessage() {
	    // Enter e-mail address
		driver.findElement(By.id("email_address")).sendKeys("gladystanujaya0030@gmail.com");
		
		// Enter message
		driver.findElement(By.id("message")).sendKeys("This is my TV wishlist");
	}

	@Then("User click SHARE WISHLIST again")
	public void userClickSHAREWISHLISTAgain() {
		driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();
	}

	@Then("Wishlist is shared")
	public void wishlistIsShared() throws InterruptedException {
	    String wishlistMessage = ("Your Wishlist has been shared.");
	    String wishMsg = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
	    try {
	    	assertEquals(wishlistMessage, wishMsg);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    Thread.sleep(2000);
	    driver.quit();
	}
}
