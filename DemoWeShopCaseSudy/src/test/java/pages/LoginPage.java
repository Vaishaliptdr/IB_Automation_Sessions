package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By loginButton = By.cssSelector("input[value='Log in']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
