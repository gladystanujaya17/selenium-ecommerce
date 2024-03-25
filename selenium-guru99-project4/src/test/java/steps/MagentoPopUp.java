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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MagentoPopUp {

	WebDriver driver;
	public int scc = 0;
	public String strSonyList;
	public String striPhoneList;
	
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
	}

	@When("User click Add to Compare Sony XPeria")
	public void userClickAddToCompareSonyXPeria() {
		strSonyList = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/h2/a")).getText();
		System.out.println("First mobile: " + strSonyList);
	    driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a")).click();
	}

	@When("User click Add to Compare iPhone")
	public void userClickAddToCompareIPhone() {
		striPhoneList = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/h2/a")).getText();
		System.out.println("First mobile: " + striPhoneList);
	    driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a")).click();
	}

	@When("User click Compare button")
	public void userClickCompareButton() {
	    driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[3]/div[1]/div[2]/div/button")).click();
	    
	    // Switching to pop up
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    }
	}

	@Then("There will be pop up window")
	public void thereWillBePopUpWindow() throws IOException {
		// Take screenshot as a proof
	    scc = (scc + 1);
	    File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    String png = ("D:\\Guru99 eCommerce Live Project\\Day04_TestCase4\\Pop up result " + scc + ".png");
	    FileUtils.copyFile(screenshotFile, new File(png));
	    
	    // Compare 2 products
	    // a. Compare title
	    String strHead = ("COMPARE PRODUCTS");
	    String strCompare = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[1]/h1")).getText();
	    try {
	    	assertEquals(strHead, strCompare);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    // b. Compare first product
	    String strCompareSony = driver.findElement(By.xpath("//*[@id=\"product_comparison\"]/tbody[1]/tr[1]/td[1]/h2/a")).getText();
	    try {
	    	assertEquals(strSonyList, strCompareSony);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    // c. Compare second product
	    String strCompareiPhone = driver.findElement(By.xpath("//*[@id=\"product_comparison\"]/tbody[1]/tr[1]/td[2]/h2/a")).getText();
	    try {
	    	assertEquals(striPhoneList, strCompareiPhone);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	@Then("User close it")
	public void userCloseIt() throws InterruptedException {
		// Close the pop up Windows
	    driver.findElement(By.xpath("//button[@title='Close Window']")).click();
	    
	    // Switching to new window
	    for (String handle : driver.getWindowHandles()) {
	    	driver.switchTo().window(handle);
	    }
	    Thread.sleep(2000);
		driver.quit();
	}
}
