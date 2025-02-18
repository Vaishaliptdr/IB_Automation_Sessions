/*Parameterization using JSON file*/

package selenium;

import org.json.JSONArray;
import org.json.JSONObject;
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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class Parameterization3 {

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

    @Test(dataProvider = "jsonLoginData")
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

    @DataProvider(name = "jsonLoginData")
    public Object[][] provideLoginData() throws IOException {
        // Read the JSON file
        File file = new File("src/test/resources/credentials.json");
        FileReader reader = new FileReader(file);

        // Parse JSON
        StringBuilder jsonBuilder = new StringBuilder();
        int i;
        while ((i = reader.read()) != -1) {
            jsonBuilder.append((char) i);
        }
        reader.close();

        // Convert string to JSON
        JSONArray jsonArray = new JSONArray(jsonBuilder.toString());

        // Convert JSON array to Object[][]
        Object[][] data = new Object[jsonArray.length()][3];
        for (int index = 0; index < jsonArray.length(); index++) {
            JSONObject jsonObject = jsonArray.getJSONObject(index);
            data[index][0] = jsonObject.getString("username");
            data[index][1] = jsonObject.getString("password");
            data[index][2] = jsonObject.getString("expectedResult");
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