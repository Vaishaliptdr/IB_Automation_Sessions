package selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Extent {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        // Initialize ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        
        // Set up graphical report configuration
        sparkReporter.config().setDocumentTitle("TestNG Automation Report");  // Report title
        sparkReporter.config().setReportName("BlazeDemo Test Report");        // Report name
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
    public void testHomePageLoad() {
        test = extent.createTest("Test Home Page Load"); // Create test in extent report
        driver.get("https://blazedemo.com/");

        String expectedTitle = "BlazeDemo1"; // Incorrect title to demonstrate failure
        try {
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            test.log(Status.PASS, "Homepage title verified successfully.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Homepage title verification failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(groups = {"regression", "smoke"})
    public void testFlightSearch() {
        test = extent.createTest("Test Flight Search"); // Create test in extent report
        driver.get("https://blazedemo.com/");

        // Select departure city
        WebElement departureCity = driver.findElement(By.name("fromPort"));
        departureCity.click();
        departureCity.findElement(By.xpath("//option[text()='Boston']")).click();

        // Select destination city
        WebElement destinationCity = driver.findElement(By.name("toPort"));
        destinationCity.click();
        destinationCity.findElement(By.xpath("//option[text()='New York']")).click();

        // Click "Find Flights" button
        WebElement findFlightsButton = driver.findElement(By.cssSelector("input[type='submit']"));
        findFlightsButton.click();

        String expectedTitle = "BlazeDemo - reserve";  // Correct title
        try {
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            test.log(Status.PASS, "Flight search results page loaded successfully.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Flight search results page title verification failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();  // Ensure the report is saved at the end of the test execution
        }
    }
}