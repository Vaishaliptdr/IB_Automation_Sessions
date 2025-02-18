package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.POM_LoginPageOrangeHRM;
import Utilities.BaseClass;

public class POM_LoginOrangeHRM {
    private POM_LoginPageOrangeHRM loginPage;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        // Initialize WebDriver
        BaseClass base = new BaseClass();
        base.initializeDriver();
        
        // Create LoginPage object with driver
        loginPage = new POM_LoginPageOrangeHRM(BaseClass.getDriver());
        
        Thread.sleep(2000);
        // Navigate to the URL
        BaseClass.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void testInvalidLogin() {
        // Perform invalid login
        loginPage.enterUsername("InvalidUser");
        loginPage.enterPassword("InvalidPass");
        loginPage.clickLogin();
        
        // Verify error message
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Invalid credentials");
    }
    
    @Test
    public void testValidLogin() {
        // Perform invalid login
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        
        // Verify error message
//        String errorMessage = loginPage.getErrorMessage();
//        Assert.assertEquals(errorMessage, "Invalid credentials");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        BaseClass.tearDownDriver();
    }
}
