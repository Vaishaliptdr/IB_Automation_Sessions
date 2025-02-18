package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {
	private By Login = By.xpath("//a[text()='Log in']");


	public LandingPage(WebDriver driver) {
		super(driver);
	}

	public void landing() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(Login).click();

	}
}
