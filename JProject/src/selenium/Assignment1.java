/*Assignment 1*/

//
//abc
package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment1 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle()); 
		System.out.println(driver.getCurrentUrl());
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Vaishali");
		driver.findElement(By.id("LastName")).sendKeys("Potdar");
		driver.findElement(By.name("Email")).sendKeys("vaishali6@infobeans.com");
		driver.findElement(By.id("Password")).sendKeys("P@ssword@123");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("P@ssword@123");
		driver.findElement(By.name("register-button")).click();
		
		//driver.findElement(By.xpath("//a[text()='Log in']")).click();
		
//		driver.findElement(By.id("Email")).sendKeys("vaishali.potdar@infobeans.com");
//		driver.findElement(By.id("Password")).sendKeys("P@ssword@123");
//		driver.findElement(By.id("RememberMe")).click();
//		driver.findElement(By.className("button-1 login-button")).click();
		
		//Contains method used when there are some spaces in text also indexing is used
		driver.findElement(By.xpath("(//a[contains(text(),'Computers')])[1]")).click();
		driver.findElement(By.xpath("//a[@title='Show products in category Notebooks']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='button-2 product-box-add-to-cart-button']")).click();
		Thread.sleep(2000);
		System.out.println("Notification: "+driver.findElement(By.id("bar-notification")).getText());
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("/cart")).click();
		
	}
}
