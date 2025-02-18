package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class Dropdown {
	public static void main(String[] args) throws InterruptedException {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/books");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle()); 
		System.out.println(driver.getCurrentUrl());
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement orderByDropdown = driver.findElement(By.id("products-orderby"));

        // Create a Select object to interact with the dropdown
        Select dropdown = new Select(orderByDropdown);
		dropdown.selectByIndex(2);
		Thread.sleep(2000);
		//dropdown.selectByValue("https://demowebshop.tricentis.com/books?orderby=6");
		Thread.sleep(2000);
		//dropdown.selectByVisibleText("Created on");
		

	}
}
