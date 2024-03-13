package steps;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class MagentoCost {

	WebDriver driver;
	String costListPage;
	String costDetailPage;
	
	@Given("User is on Magento page")
	public void userIsOnMagentoPage() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\dell\\\\Downloads\\\\chromedriver-win64\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("User click Mobile link button")
	public void userClickMobileLinkButton() {
		WebElement buttonMobile = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
		buttonMobile.click();
	}

	@Then("User is in Mobile page")
	public void userIsInMobilePage() {
	    System.out.println("Mobile page text: " + driver.findElement(By.cssSelector("h1")).getText());
	}

	@Then("User is read the cost of Sony Xperia mobile")
	public void userIsReadTheCostOfSonyXperiaMobile() {
		costListPage = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]/span")).getText();
	    System.out.println("Sony Xperia cost in list page: " + costListPage);
	}

	@When("User click Sony Xperia mobile")
	public void userClickSonyXperiaMobile() {
	    WebElement buttonSonyXperia = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/h2/a"));
	    buttonSonyXperia.click();
	}

	@Then("User read the Sony Xperia mobile from detail page")
	public void userReadTheSonyXperiaMobileFromDetailPage() {
		costDetailPage = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]/span")).getText();
	    System.out.println("Sony Xperia cost in Detail page: " + costDetailPage);
	}

	@Then("User compare value in list page and details page")
	public void userCompareValueInListPageAndDetailsPage() throws InterruptedException {
	   // Compare value in list page and details page
		try {
			assertEquals(costListPage, costDetailPage); // to compare the value
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		Thread.sleep(2000);
		driver.quit();
	}
	
}
