/*Parameterization using .csv file*/

package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class Parameterization4 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\vaishali.potdar\\\\Desktop\\\\Softwares\\\\chromedriver-win64\\\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        // Locate and interact with elements
    	WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        // Perform login action
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();

        // Add assertions or further steps if needed
        System.out.println("Attempted login with username: " + username + " and password: " + password);

        // Go back to the login page for the next iteration
        driver.navigate().back();
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        return readCsvData("C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\Eclipse Workspace\\JProject\\File\\Login.csv");
    }

    private Object[][] readCsvData(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int rowCount = 0;

        // Count rows
        while ((line = br.readLine()) != null) {
            rowCount++;
        }
        br.close();

        Object[][] data = new Object[rowCount - 1][2]; // Exclude header row
        br = new BufferedReader(new FileReader(filePath));
        br.readLine(); // Skip header row

        int index = 0;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            data[index][0] = values[0]; // username
            data[index][1] = values[1]; // password
            index++;
        }
        br.close();
        return data;
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
