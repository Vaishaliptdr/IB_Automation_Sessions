package selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandling {
	public static void main(String args[]) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\vaishali.potdar\\\\Desktop\\\\Softwares\\\\chromedriver-win64\\\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.toolsqa.com/selenium-training/");		
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//a[text()='Demo Site']")).click();

		System.out.println("Main window Title: "+driver.getTitle());
		String main_window_handle = driver.getWindowHandle();
		System.out.println("Main window handle: "+main_window_handle);
		
		//Print- How many windows are opened?
		Set<String> allWindowsId=driver.getWindowHandles();
		System.out.println("All windows id = "+allWindowsId);
		System.out.println("Total number of windows are = "+allWindowsId.size());

		Iterator<String> Iterator= allWindowsId.iterator();

		while(Iterator.hasNext()) 
		{
			String child_window_handle=Iterator.next();

			if(!main_window_handle.equals(child_window_handle)) 
			{
				driver.switchTo().window(child_window_handle);
				System.out.println("Switched to Child Window");
				Thread.sleep(3000);
				System.out.println("Child window Title: "+driver.getTitle());

				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("scrollBy(0, 1000)"); 

				// Perform actions in the child window
				driver.findElement(By.xpath("//h5[text()='Forms']")).click();
				driver.close();
			}
		}
		driver.switchTo().window(main_window_handle);
		System.out.println("Switched to Main Window");
		System.out.println("Main window Title: "+driver.getTitle());
		driver.quit();
	}
}
