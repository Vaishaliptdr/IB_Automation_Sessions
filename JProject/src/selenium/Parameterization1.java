/*Parameterization for login data using @DataProvider*/

package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Parameterization1 {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Setup WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedResult) {
        // Locate username and password fields
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));

        // Enter login credentials
        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        // Click login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        // Verification
        try {
            if (expectedResult.equals("Success")) {
                // Wait for the profile menu to appear after login
                Thread.sleep(2000); // Replace with WebDriverWait for production code
                WebElement profileMenu = driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']"));
                Assert.assertTrue(profileMenu.isDisplayed(), "Login success verification failed.");
                System.out.println("Login successful for user: " + username);

                // Log out
                profileMenu.click();
                WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Logout']"));
                logoutButton.click();
            } else {
                // Check for the error message on failed login
                WebElement errorMessage = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']"));
                Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid login.");
                System.out.println("Login failed as expected for user: " + username);
            }
        } catch (Exception e) {
            Assert.fail("Unexpected behavior during login test for user: " + username, e);
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] provideLoginData() {
        return new Object[][]{
            {"Admin", "admin123", "Success"},        // Valid credentials
            {"invalidUser", "admin123", "Failure"}, // Invalid username
            {"Admin", "invalidPassword", "Failure"} // Invalid password
        };
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}