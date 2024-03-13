package steps;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class MagentoList {

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

	@When("User click Mobile link button")
	public void userClickMobileLinkButton() {
		WebElement buttonMobile = driver.findElement(By.xpath("//*[@id=\"nav\"]/ol/li[1]/a"));
		buttonMobile.click();
	}

	@Then("User is in Mobile page")
	public void userIsInMobilePage() {
	    System.out.println("Mobile page text: " + driver.findElement(By.cssSelector("h1")).getText());
	}

	@When("User click Sort by Name")
	public void userClickSortByName() {
	    WebElement dropdownSort = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select"));
	    Select selectName = new Select(dropdownSort);
	    selectName.selectByVisibleText("Name");
	}

	@Then("Product is sorted by name")
	public void productIsSortedByName() throws InterruptedException, IOException {
		// This will take a screenshot of the page after a successful login
		scc = (scc + 1);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String png = ("D:\\Guru99 eCommerce Live Project\\Day01_TestCase1\\Mobile Products are sorted" + scc + ".png");
		FileUtils.copyFile(scrFile, new File(png));
		// Wait for 2 seconds
		Thread.sleep(2000);
	    driver.quit();
	}
	
}