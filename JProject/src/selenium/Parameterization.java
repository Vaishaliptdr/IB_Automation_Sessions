/*Parameterization in TeastNG using @DataProvider
 * Adding two numbers*/

package selenium;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Parameterization {

    @Test(dataProvider = "addNumbersData")
    public void addNumbers(int num1, int num2, int expectedSum) {
        int actualSum = num1 + num2;
        System.out.println("Adding " + num1 + " and " + num2 + ": Expected = " + expectedSum + ", Actual = " + actualSum);
        Assert.assertEquals(actualSum, expectedSum, "Addition result is incorrect!");
    }

    @DataProvider(name = "addNumbersData")
    public Object[][] provideData() {
        return new Object[][]{
            {10, 20, 30}, // Test case 1
            {5, 15, 20},  // Test case 2
            {0, 0, 0},    // Test case 3
            {-5, 5, 0}    // Test case 4
        };
    }
}
