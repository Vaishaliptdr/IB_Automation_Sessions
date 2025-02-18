package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComputersPage extends BasePage {
    private By notebooksLink = By.linkText("Notebooks");

    public ComputersPage(WebDriver driver) {
        super(driver);
    }

    public void selectNotebooks() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.findElement(notebooksLink).click();
    }
}
