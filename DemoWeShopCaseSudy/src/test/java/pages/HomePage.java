package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By computersCategory = By.linkText("Computers");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectComputersCategory() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(computersCategory).click();
    }
}
