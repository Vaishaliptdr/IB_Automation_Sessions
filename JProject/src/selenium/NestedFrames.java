//Nested frames and switching between frames
package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NestedFrames {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/frames");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//a[text()='Nested Frames']")).click();

		//Top Parent frame
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		System.out.println("Switched to top parent frame");


		driver.switchTo().frame("frame-left");
		System.out.println("Switched to left frame");
		WebElement LText=driver.findElement(By.xpath("//body[contains(text(),'LEFT')]"));
		System.out.println("Left frame text: "+LText.getText());
		driver.switchTo().parentFrame();

		driver.switchTo().frame("frame-middle");
		System.out.println("Switched to middle frame");
		WebElement MText=driver.findElement(By.id("content"));
		System.out.println("Middle frame text: "+MText.getText());
		driver.switchTo().parentFrame();

		driver.switchTo().frame("frame-right");
		System.out.println("Switched to right frame");
		WebElement RText=driver.findElement(By.xpath("//body[contains(text(),'RIGHT')]"));
		System.out.println("Right frame text: "+RText.getText());
		//driver.switchTo().parentFrame();

		driver.switchTo().defaultContent();

		//Bottom Parent frame
		driver.switchTo().frame("frame-bottom");
		System.out.println("Switched to bottom frame");
		WebElement BText=driver.findElement(By.xpath("//body[contains(text(),'BOTTOM')]"));
		System.out.println("Bottom frame text: "+BText.getText());
	}
}
