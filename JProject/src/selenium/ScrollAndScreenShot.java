package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

public class ScrollAndScreenShot {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone");
		driver.findElement(By.id("nav-search-submit-button")).click();
		WebElement targetProduct=driver.findElement(By.xpath("//span[text()='Apple iPhone SE 3rd Gen, 64GB, Starlight - Unlocked (Renewed)']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;

		js.executeScript("arguments[0].scrollIntoView(true);", targetProduct);

        // Highlight the element (optional, for visual confirmation)
       js.executeScript("arguments[0].style.border='5px solid red'", targetProduct);
		
		
}
}
