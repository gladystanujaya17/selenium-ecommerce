package steps;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MagentoCoupon {

	WebDriver driver;
    String keys = "GURU99";
	
	@Given("User is on Magento page")
	public void userIsOnMagentoPage() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\dell\\\\Downloads\\\\chromedriver-win64\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("User go to Mobile page")
	public void userGoToMobilePage() {
	    driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a")).click();
	}

	@Then("User add iPhone to cart")
	public void userAddIPhoneToCart() {
	    driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/button")).click();
	}

	@When("User enter coupon code")
	public void userEnterCouponCode() {
	    WebElement couponCode = driver.findElement(By.xpath("//*[@id=\"coupon_code\"]"));
	    couponCode.sendKeys("GURU50");
	    driver.findElement(By.xpath("//*[@id=\"discount-coupon-form\"]/div/div/div/div/button")).click();
	}

	@Then("User verify the discount is generated")
	public void userVerifyTheDiscountIsGenerated() throws InterruptedException {
	    String discount = driver.findElement(By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tbody/tr[2]/td[2]/span")).getText();
	    System.out.println("Discount: " + discount);
	    Thread.sleep(2000);
	    driver.quit();
	}
	
	@When("User enter different coupon code")
	public void userEnterDifferentCouponCode() {
		WebElement couponCode = driver.findElement(By.xpath("//*[@id=\"coupon_code\"]"));
		couponCode.sendKeys(keys);
	    driver.findElement(By.xpath("//*[@id=\"discount-coupon-form\"]/div/div/div/div/button")).click();
	}
	
	@Then("User verify the discount is not generated")
	public void userVerifyTheDiscountIsNotGenerated() throws InterruptedException {
		String errorMsg1 = "Coupon code \"GURU99\" is not valid.";
		String errorMsg2 = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")).getText();
		try {
			assertEquals(errorMsg1, errorMsg2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
	    driver.quit();
	}
}
