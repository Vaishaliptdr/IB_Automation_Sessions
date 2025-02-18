/*Parameterization using YAML file*/


package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Parameterization2 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Setup WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(dataProvider = "yamlLoginData")
    public void testLogin(String username, String password, String expectedResult) {
        // Wait for the username field to be visible
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
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
                WebElement profileMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-userdropdown-name']")));
                Assert.assertTrue(profileMenu.isDisplayed(), "Login success verification failed.");
                System.out.println("Login successful for user: " + username);

                // Log out
                profileMenu.click();
                WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']")));
                logoutButton.click();
            } else {
                // Check for the error message on failed login
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")));
                Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid login.");
                System.out.println("Login failed as expected for user: " + username);
            }
        } catch (Exception e) {
            Assert.fail("Unexpected behavior during login test for user: " + username, e);
        }
    }

    @DataProvider(name = "yamlLoginData")
    public Object[][] provideLoginData() throws FileNotFoundException {
        // Load YAML file
        Yaml yaml = new Yaml();
        FileInputStream fileInputStream = new FileInputStream("src/Test/Resources/Credentials.yaml");

        // Parse YAML into a list of maps
        Map<String, List<Map<String, String>>> yamlData = yaml.load(fileInputStream);
        List<Map<String, String>> loginDataList = yamlData.get("loginData");

        // Convert to Object[][] for TestNG DataProvider
        Object[][] data = new Object[loginDataList.size()][3];
        for (int i = 0; i < loginDataList.size(); i++) {
            Map<String, String> loginData = loginDataList.get(i);
            data[i][0] = loginData.get("username");
            data[i][1] = loginData.get("password");
            data[i][2] = loginData.get("expectedResult");
        }
        return data;
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}