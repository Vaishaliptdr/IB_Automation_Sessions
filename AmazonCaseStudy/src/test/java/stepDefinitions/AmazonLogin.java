package stepDefinitions;

import Utilities.BaseClass;
import io.cucumber.java.en.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AmazonLogin extends BaseClass {
    private WebDriver driver;

    @Given("I log in to Amazon")
    public void iLogInToAmazon() {
        initializeDriver();
        driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.amazon.in/");

        // Perform login steps
        driver.findElement(By.xpath("//a[@id='nav-link-accountList']")).click();
        driver.findElement(By.id("ap_email")).sendKeys("7387895978");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("P@ssword@123");
        driver.findElement(By.id("signInSubmit")).click();
        Assert.assertTrue(driver.getTitle().contains("Amazon"), "Login failed.");
    }
}
