package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Frames {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/frames");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//a[text()='iFrame']")).click();
		driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
		
		WebElement text=driver.findElement(By.xpath("//p[text()='Your content goes here.']"));
		System.out.println("Frame text: "+text.getText());
		
		driver.switchTo().defaultContent();
		
		WebElement text1=driver.findElement(By.xpath("//h3[text()='An iFrame containing the TinyMCE WYSIWYG Editor']"));
		System.out.println("Default content text: "+text1.getText());
		
		
	}
}
