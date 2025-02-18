/*Assignment 2: 
 * 1. Open "https://toolsqa.com/"
 * 2. Click on Tutorial>>Hover on "Front-End Testing Automation">>Click on "Selenium Java"
 * 3. Print URL and title
 * 4. Click Back 
 * 5. Click on Enroll Yourself
 * 6. Enter details
 * 7. Verify success and error message
 * 8. Print Result
 */
package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Assignment2 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://toolsqa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Clicking on Tutorial button
		driver.findElement(By.xpath("//a[@class='navbar__tutorial-menu']")).click();

		//Moving to element
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//span[text()='Front-End Testing Automation']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Selenium in Java")).click();
		System.out.println("Selenium tutorial link: "+driver.getCurrentUrl());
		System.out.println("Page Title: "+driver.getCurrentUrl());
		Thread.sleep(2000);
		driver.navigate().back();

		driver.findElement(By.xpath("//span[@class='navbar__tutorial-menu--menu-bars']")).click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("scrollBy(0, 100)");

		driver.findElement(By.xpath("//a[@class='btn btn-primary-shadow btn-block']")).click();
		driver.findElement(By.id("first-name")).sendKeys("Vaishali");
		driver.findElement(By.id("email")).sendKeys("vaishali@gmail.com");
		driver.findElement(By.id("mobile")).sendKeys("7898767890");

		Select country=new Select(driver.findElement(By.name("country")));
		country.selectByVisibleText("India");

		driver.findElement(By.id("city")).sendKeys("Pune");
		driver.findElement(By.name("message")).sendKeys("I want to enroll for this course");

		driver.findElement(By.id("code")).sendKeys("abcd");
		driver.findElement(By.xpath("//button[text()='Send']")).click();
		String Error=driver.findElement(By.xpath("//div[@class='alert alert-error']")).getText();

		System.out.println(Error);
		String message="We have received your message. We will soon get in touch.";

		if(Error.equalsIgnoreCase(message))
		{
			System.out.println("Registration Successful");
		}
		else
		{
			System.out.println("Registration Not Successful");
		}

	}
}
