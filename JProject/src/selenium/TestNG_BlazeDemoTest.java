/*TestNG Framework
 * URL: https://blazedemo.com/
 * Assert*/

package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_BlazeDemoTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver path manually
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaishali.potdar\\Desktop\\Softwares\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(groups = {"smoke"})
    public void testHomePageLoad() {
        // Navigate to BlazeDemo
        driver.get("https://blazedemo.com/");
        
        // Validate the homepage title
        String expectedTitle = "BlazeDemo1";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Homepage title does not match!");
    }

    @Test(groups = {"regression", "smoke"})
    public void testFlightSearch() {
        // Navigate to BlazeDemo
        driver.get("https://blazedemo.com/");
        
        // Select departure city
        WebElement departureCity = driver.findElement(By.name("fromPort"));
        departureCity.click();
        departureCity.findElement(By.xpath("//option[text()='Boston']")).click();
        
        // Select destination city
        WebElement destinationCity = driver.findElement(By.name("toPort"));
        destinationCity.click();
        destinationCity.findElement(By.xpath("//option[text()='New York']")).click();
        
        // Click "Find Flights" button
        WebElement findFlightsButton = driver.findElement(By.cssSelector("input[type='submit']"));
        findFlightsButton.click();

        // Validate the results page
        String expectedTitle = "BlazeDemo - reserve";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Results page title does not match!");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}