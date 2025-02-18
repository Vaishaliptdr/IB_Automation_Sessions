package selenium;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ContextClick {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	

		WebElement RightClick=driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));

		Actions act=new Actions(driver);
		act.contextClick(RightClick).perform();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//span[text()='Copy']")).click();

		Alert ok=driver.switchTo().alert();
		System.out.println("Alert text: "+ok.getText());
		Thread.sleep(2000);
		ok.accept();
		System.out.println("Alert accepted");

		WebElement DoubleClick= driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
		act.doubleClick(DoubleClick).perform();

		Thread.sleep(2000);
		
		Alert Click=driver.switchTo().alert();
		System.out.println("Alert text: "+Click.getText());
		
		Thread.sleep(2000);
		ok.accept();
		System.out.println("Alert accepted");

	}
}