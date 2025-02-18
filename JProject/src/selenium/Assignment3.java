/* Assignment 3
 * 1. Open URL: https://blazedemo.com/
 * 2. Select locations
 * 3. Click on Find Flight
 * 4. Select Flight
 * 5. Enter Details
 * 6. Click on purchase Flight
 * 7. Print Result
 * */

package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment3 {
	public static void main(String args[]) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://blazedemo.com/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Select fromDrop=new Select(driver.findElement(By.xpath("//select[@name='fromPort']")));
		fromDrop.selectByValue("Boston");

		Select ToDrop=new Select(driver.findElement(By.xpath("//select[@name='toPort']")));
		ToDrop.selectByValue("New York");

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("(//input[@type='submit'])[3]")).click();

		String Header=driver.findElement(By.xpath("//h2[text()='Your flight from TLV to SFO has been reserved.']")).getText();
		System.out.println("User navigated to: "+Header);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println("Your Flight reserved successfully");

		//String ID=driver.findElement(By.xpath("//td[text()='1732604688525']")).getText();
		String Amount=driver.findElement(By.xpath("//td[text()='555 USD']")).getText();

		System.out.println("Flight Amount: "+Amount);

	}

}
