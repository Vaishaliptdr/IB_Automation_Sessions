package stepDefinitions;

import Utilities.BaseClass;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

//import com.aventstack.extentreports.Status;

public class AddToCart extends BaseClass {
    private WebDriver driver;

    @Given("I search for {string}")
    public void iSearchFor(String product) {
        driver = getDriver();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);
       driver.findElement(By.id("nav-search-submit-button")).click();
       Assert.assertTrue(driver.getTitle().contains(product), "Search failed.");
    }

    @When("I add the product to the cart")
    public void iAddTheProductToTheCart() throws InterruptedException {
//        driver.findElement(By.cssSelector("span.a-size-medium.a-color-base.a-text-normal")).click();
//        driver.findElement(By.id("add-to-cart-button")).click();
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;  
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0, 3000)");
		Thread.sleep(2000);

		WebElement targetProduct = driver.findElement(By.xpath("//span[text()='iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Black']"));
		js.executeScript("arguments[0].scrollIntoView(true);", targetProduct);
		targetProduct.click();

		Thread.sleep(3000);
		String main_window_handle = driver.getWindowHandle();
		System.out.println("Main window handle: "+main_window_handle);
		Thread.sleep(5000);
	
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
				//String expectedTitle1 = "iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Black : Amazon.in: Electronics";  // Correct title
				

				Thread.sleep(5000);
				WebElement BuyNow=driver.findElement(By.id("buy-now-button"));

				js.executeScript("arguments[0].scrollIntoView(true);",BuyNow );
				BuyNow.click();

				driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();

				driver.findElement(By.xpath("//a[text()='Enter card details']")).click();
				driver.switchTo().frame(0);

				driver.findElement(By.xpath("//input[@name='addCreditCardNumber']")).sendKeys("2222 4000 6000 0007");
				driver.findElement(By.xpath("//input[@name='ppw-widgetEvent:AddCreditCardEvent']")).click();

				driver.switchTo().defaultContent();
			}
		}
		driver.switchTo().window(main_window_handle);
		
    }

    @Then("I proceed to checkout")
    public void iProceedToCheckout() {
//        driver.findElement(By.id("nav-cart")).click();
//        driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
//        Assert.assertTrue(driver.getTitle().contains("Checkout"), "Checkout failed.");
    	System.out.println("Switched to Main Window");
		System.out.println("Main window Title: "+driver.getTitle());
    }
}
