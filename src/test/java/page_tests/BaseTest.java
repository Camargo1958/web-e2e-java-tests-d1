package page_tests;

import base.AppConstants;
import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.util.test.SimpleTestResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static utils.ExtentReportHelper.getReportObject;

public class BaseTest {

    protected WebDriver driver;
    protected String browser;

    protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
    private static final ExtentReports reports = getReportObject();

    @Parameters({"browserName"})
    @BeforeMethod
    public void setupTest(@Optional String browserName, ITestResult iTestResult) {
//        browser = AppConstants.browserName;
//        ChromeOptions co = new ChromeOptions();
//        FirefoxOptions fo = new FirefoxOptions();
        if(browserName!=null) {
            browser = browserName;
        } else {
            browser=AppConstants.browserName;
        }

        System.out.println("Browser name is: "+browser);

        if(browser.equalsIgnoreCase("chrome")) {
            if(AppConstants.platform.equalsIgnoreCase("local")) {
                //co.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
            }
        }

        else if(browser.equalsIgnoreCase("firefox")) {
            if(AppConstants.platform.equalsIgnoreCase("local")) {
                //fo.addArguments("--remote-allow-origins=*");
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
            }
        }

        else {System.out.println("Browser name entered is not supported!!!");}

        ExtentTest test = reports.createTest(iTestResult.getMethod().getMethodName());
        testLogger.set(test);
        testLogger.get().log(Status.INFO, "Driver Start Time: "+ LocalDateTime.now());
    }

    @AfterMethod
    public void tearDownTest(ITestResult iTestResult) {
        if(iTestResult.isSuccess()) {
            testLogger.get().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName()+" is successful!!", ExtentColor.GREEN));
        } else {
            testLogger.get().log(Status.FAIL, "Test failed due to: "+ iTestResult.getThrowable());
            String screenshot = BasePage.getScreenShot(iTestResult.getMethod().getMethodName()+".jpg", driver);
            testLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.convertImg_Base64(screenshot)).build());
        }

        testLogger.get().log(Status.INFO, "Driver End Time: "+ LocalDateTime.now());

        driver.quit();
    }

    @AfterClass
    public void flushTestreport() {
        reports.flush();
    }

}
