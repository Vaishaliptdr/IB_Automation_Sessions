package Utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
    private static WebDriver driver;

    public void initializeDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\vaishali.potdar\\\\Desktop\\\\Softwares\\\\chromedriver-win64\\\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new AssertionError("Driver is not initialized. Ensure login steps are executed first.");
        }
        return driver;
    }

    public static void tearDownDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
