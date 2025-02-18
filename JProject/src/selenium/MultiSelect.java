package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class MultiSelect {
	public static void main(String args[]) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\vaishali.potdar\\\\Desktop\\\\Softwares\\\\chromedriver-win64\\\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("scrollBy(0,1000)");

		WebElement values=driver.findElement(By.xpath("//select[@class='spTextField']"));
		Select dropdown=new Select(values);

		dropdown.selectByIndex(0);
		dropdown.selectByIndex(3);
		dropdown.selectByIndex(2);

		System.out.println("Selected Options:");
		for (WebElement option : dropdown.getAllSelectedOptions()) {
			System.out.println(option.getText());
		}

		dropdown.deselectByIndex(2);
		dropdown.selectByIndex(1);
		Thread.sleep(3000);

		dropdown.deselectAll();
	}
}
