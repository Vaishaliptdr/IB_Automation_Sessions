/*Case Study
Implement below scenario for Amazon (TestNG & Extent Report) 
1)Login
2)Search Product
3)Add to cart
4)Buy Now
5)Add CarD information
6)Click payment*/

package selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CaseStudyAmazon {
	private WebDriver driver;
	private ExtentReports extent;
	private ExtentTest test;

	@BeforeSuite
	public void setupReport() {
		// Initialize ExtentSparkReporter
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");

		// Set up graphical report configuration
		sparkReporter.config().setDocumentTitle("TestNG Automation Report");  // Report title
		sparkReporter.config().setReportName("Amazon Test Report");        // Report name
		sparkReporter.config().setTheme(Theme.DARK);                          // Set dark theme
		sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");    // Time format


		// Create an ExtentReports instance
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Add system/environment information
		extent.setSystemInfo("Host Name", "Localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Tester");
	}

	@BeforeClass
	public void setUp() {
		// Set up ChromeDriver path
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test(groups = {"smoke"})
	public void testLogin() throws InterruptedException {
		test = extent.createTest("Test Login"); // Create test in extent report
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");

		String expectedTitle = "Amazon.in"; // Incorrect title to demonstrate failure
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			test.log(Status.PASS, "Homepage title verified successfully.");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Homepage title verification failed: " + e.getMessage());
			throw e;
		}
		driver.findElement(By.xpath("//span[text()='Account & Lists']")).click();
		driver.findElement(By.id("ap_email")).sendKeys("7387895978");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("P@ssword@123");
		driver.findElement(By.id("signInSubmit")).click();
		Thread.sleep(10000);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println("Login Successful");
	}

	@Test(groups = {"regression", "smoke"})
	public void testSearchItem() throws InterruptedException {
		test = extent.createTest("Test Item Search"); // Create test in extent report

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Iphone");
		driver.findElement(By.id("nav-search-submit-button")).click();
		String expectedTitle = "Amazon.in : Iphone";  // Correct title
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			test.log(Status.PASS, "Item search results page loaded successfully.");
		} catch (AssertionError e) {
			test.log(Status.FAIL, "Item search results page title verification failed: " + e.getMessage());
			throw e;
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;  
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, 3000)");
		Thread.sleep(2000);

		WebElement targetProduct = driver.findElement(By.xpath("//span[text()='iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Black']"));
		js.executeScript("arguments[0].scrollIntoView(true);", targetProduct);
		targetProduct.click();

		Thread.sleep(3000);
		String main_window_handle = driver.getWindowHandle();
		System.out.println("Main window handle: "+main_window_handle);
		Thread.sleep(5000);
	
		Set<String> allWindowsId=driver.getWindowHandles();
		
		System.out.println("All windows id = "+allWindowsId);
		System.out.println("Total number of windows are = "+allWindowsId.size());

		Iterator<String> Iterator= allWindowsId.iterator();

		while(Iterator.hasNext()) 
		{
			String child_window_handle=Iterator.next();

			if(!main_window_handle.equals(child_window_handle)) 
			{
				driver.switchTo().window(child_window_handle);
				System.out.println("Switched to Child Window");
				Thread.sleep(3000);
				System.out.println("Child window Title: "+driver.getTitle());
				String expectedTitle1 = "iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Black : Amazon.in: Electronics";  // Correct title
				try {
					Assert.assertEquals(driver.getTitle(), expectedTitle1);
					test.log(Status.PASS, "Item description page loaded successfully.");
				} catch (AssertionError e) {
					test.log(Status.FAIL, "Item description page title verification failed: " + e.getMessage());
					throw e;
				}

				Thread.sleep(5000);
				WebElement BuyNow=driver.findElement(By.id("buy-now-button"));

				js.executeScript("arguments[0].scrollIntoView(true);",BuyNow );
				BuyNow.click();

				driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();

				driver.findElement(By.xpath("//a[text()='Enter card details']")).click();
				driver.switchTo().frame(0);

				driver.findElement(By.xpath("//input[@name='addCreditCardNumber']")).sendKeys("2222 4000 6000 0007");
				driver.findElement(By.xpath("//input[@name='ppw-widgetEvent:AddCreditCardEvent']")).click();

				driver.switchTo().defaultContent();
			}
		}
		driver.switchTo().window(main_window_handle);
		System.out.println("Switched to Main Window");
		System.out.println("Main window Title: "+driver.getTitle());

	}


	@AfterClass
	public void tearDown() {
		if (driver != null) {
			//driver.quit();
		}
	}

	@AfterSuite
	public void tearDownReport() {
		if (extent != null) {
			extent.flush();  
		}
	}
}
