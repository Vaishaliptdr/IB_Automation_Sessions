package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NotebookPage extends BasePage {
    private By laptopLink = By.linkText("14.1-inch Laptop");
    private By addToCartButton = By.id("add-to-cart-button-31");
    private By successMessage = By.xpath("//p[@class='content']");

    public NotebookPage(WebDriver driver) {
        super(driver);
    }

    public void selectLaptopAndAddToCart() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.findElement(laptopLink).click();
        driver.findElement(addToCartButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}
