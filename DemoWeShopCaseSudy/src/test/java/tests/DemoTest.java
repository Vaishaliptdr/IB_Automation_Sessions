package tests;

//import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import pages.*;
import utilities.DriverFactory;
import utilities.ExtentReportUtil;


public class DemoTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ComputersPage computersPage;
    private NotebookPage notebookPage;
    private LandingPage landingPage;
    private ExtentReportUtil report;
    private ExtentReports extentReport;
    

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://demowebshop.tricentis.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        computersPage = new ComputersPage(driver);
        notebookPage = new NotebookPage(driver);
        landingPage = new LandingPage(driver); 
        report = new ExtentReportUtil();
        extentReport= report.createInstance("C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\Eclipse Workspace\\DemoWeShopCaseSudy\\test-output\\extent.html");

    }

    @Test
    public void completeFlow() {
    	landingPage.landing();
        loginPage.login("hikih76853@modotso.com", "123456");
        homePage.selectComputersCategory();
        computersPage.selectNotebooks();
        notebookPage.selectLaptopAndAddToCart();
        String successMessage = notebookPage.getSuccessMessage();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Assert.assertTrue(successMessage.contains("The product has been added to you"));
        report.createTest("Demo web shop");
        
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    extentReport.flush();
    }
}
