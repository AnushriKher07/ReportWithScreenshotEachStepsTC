package StepDefinations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utility.Configreader;
import Utility.Utility;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginStepDefinations {
	WebDriver driver;
	ExtentReports extent;
    ExtentTest logger;
    
    public void LoginTest(WebDriver driver, ExtentReports extent, ExtentTest logger) {
        this.driver = driver;
        this.extent = extent;
        this.logger = logger;
    }


	public void setup() {
		System.setProperty("webdriver.gecko.driver", Configreader.getPropertyValue("webdriver.gecko.driver"));
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        
        
     // Initialize ExtentReports instance
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/learn_automation1.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Create a test in the Extent report
        logger = extent.createTest("LoginTest");
    }
	
	
	@After
    public void tearDown() {
		 extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }
	
    @Given("User is on the Google homepage")
    public void user_is_on_the_google_homepage() {
        
        driver.get(Configreader.getPropertyValue("base.url"));
        // Capture screenshot for step
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            // Attach screenshot to Extent report
            logger.pass("Navigated to Google homepage", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @Then("Page title should contain {string}")
    public void page_title_should_contain(String expectedTitle) {
        String actualTitle = driver.getTitle();
        // Capture screenshot for step
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            // Attach screenshot to Extent report
            logger.pass("Page title is: " + actualTitle, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(actualTitle.contains(expectedTitle), "Page title doesn't contain expected text");
    }


	
}
