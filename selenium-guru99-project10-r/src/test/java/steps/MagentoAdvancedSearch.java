package steps;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class MagentoAdvancedSearch {
	WebDriver driver;
	
	@Given("User is on Parabank homepage")
	public void userIsOnParabankHomepage() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\dell\\\\Downloads\\\\chromedriver-win64\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("User click Advanced Search")
	public void userClickAdvancedSearch() {
	    driver.findElement(By.linkText("ADVANCED SEARCH")).click();
	    // driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[3]/div/div[3]/ul/li[3]/a")).click();
	}

	@Then("User in the Advanced Search page")
	public void userInTheAdvancedSearchPage() {
	    // Compare title
	    String strTitle = ("CATALOG ADVANCED SEARCH");
	    String strCompare = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/h1")).getText();
	    try {
	    	assertEquals(strTitle, strCompare);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	@When("User enter price filed")
	public void userEnterPriceFiled() {
	    // Filter 1
		driver.findElement(By.xpath("//*[@id=\"price\"]")).sendKeys("0");
		
		// Filter 2
		driver.findElement(By.xpath("//*[@id=\"price_to\"]")).sendKeys("150");
		
	}

	@Then("User click Search")
	public void userClickSearch() {
	    driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();
	}

	@Then("User note the Price and Product Name")
	public void userNoteThePriceAndProductName() {
		System.out.println("PENCARIAN PERTAMA");

		// Scroll down
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scroll(0, 300)");

		// First Product
		String product_one = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[3]/ul/li[1]/div/h2/a")).getText();
		String product_one_price = driver.findElement(By.xpath("//*[@id=\"product-price-1\"]/span")).getText();
		System.out.println("Produk pertama: " + product_one);
		System.out.println("Harga produk pertama: " + product_one_price);
		
		// Second Product
		String product_two = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[3]/ul/li[2]/div/h2/a")).getText();
		String product_two_price = driver.findElement(By.xpath("//*[@id=\"product-price-3\"]")).getText();
		System.out.println("Produk kedua: " + product_two);
		System.out.println("Harga produk kedua: " + product_two_price);
	}

	@When("User reenter price field")
	public void userReenterPriceField() {
		// Go to Advanced Search
	    driver.findElement(By.linkText("ADVANCED SEARCH")).click();
	    
	    // Filter 1
	 	driver.findElement(By.xpath("//*[@id=\"price\"]")).sendKeys("151");
	 		
	 	// Filter 2
	 	driver.findElement(By.xpath("//*[@id=\"price_to\"]")).sendKeys("1000");
	}

	@Then("User click Search again")
	public void userClickSearchAgain() {
	    driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();
	}

	@Then("User note the Price and Product Name again")
	public void userNoteThePriceAndProductNameAgain() throws InterruptedException {
		System.out.println("PENCARIAN KEDUA");
		
		// Scroll down
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scroll(0, 300)");

		// First Product
		String product_one_r = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[3]/ul/li[1]/div/h2/a")).getText();
		String product_one_price_r = driver.findElement(By.xpath("//*[@id=\"product-price-2\"]/span")).getText();
		System.out.println("Produk pertama: " + product_one_r);
		System.out.println("Harga produk pertama: " + product_one_price_r);
				
		// Second Product
		String product_two_r = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[3]/ul/li[2]/div/h2/a")).getText();
		String product_two_price_r = driver.findElement(By.xpath("//*[@id=\"product-price-4\"]")).getText();
		System.out.println("Produk kedua: " + product_two_r);
		System.out.println("Harga produk kedua: " + product_two_price_r);
		
		// Third Product
		String product_three_r = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[3]/ul/li[3]/div/h2/a")).getText();
		String product_three_price_r = driver.findElement(By.xpath("//*[@id=\"product-price-5\"]/span")).getText();
		System.out.println("Produk ketiga: " + product_three_r);
		System.out.println("Harga produk ketiga: " + product_three_price_r);
		
		Thread.sleep(2000);
		driver.quit();
	}
}
