package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebElement;

public class FirstProgram {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle()); 
		System.out.println(driver.getCurrentUrl());

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		WebElement tagname=driver.findElement(By.xpath("//span[text()='Admin']"));
		System.out.println("Tagname for Admin: "+tagname.getTagName());
		System.out.println("Text for Admin: "+tagname.getText());
		
		//System.out.println("Attribute name for Admin: "+tagname.getAttribute(TextName));
		
		driver.findElement(By.xpath("//span[text()='Admin']")).click();
		
		
		driver.close();
	}

}
