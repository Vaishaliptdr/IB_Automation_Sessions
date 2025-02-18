package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragDrop {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Actions act=new Actions(driver);

		WebElement from=driver.findElement(By.id("box6"));
		WebElement to=driver.findElement(By.id("box106"));
		WebElement from1=driver.findElement(By.id("box4"));
		WebElement to1=driver.findElement(By.id("box104"));
		
		act.dragAndDrop(from, to).perform();
		act.dragAndDrop(from1, to1).perform();		

	}
}
