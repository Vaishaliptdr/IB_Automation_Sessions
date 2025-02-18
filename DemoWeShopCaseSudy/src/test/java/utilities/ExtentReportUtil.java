package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil {
    private static ExtentReports extent;
    private static ExtentTest test;

    public ExtentReports createInstance(String filePath) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
     // Set up graphical report configuration
        sparkReporter.config().setDocumentTitle("TestNG Automation Report");  // Report title
        sparkReporter.config().setReportName("BlazeDemo Test Report");        // Report name
        sparkReporter.config().setTheme(Theme.DARK);                          // Set dark theme
        sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");    // Time format
        
        return extent;
    }

    public ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }
}
