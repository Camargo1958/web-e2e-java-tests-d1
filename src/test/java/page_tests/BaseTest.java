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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;


import static utils.ExtentReportHelper.getReportObject;

public class BaseTest {

    protected WebDriver driver;
    protected String browser;

    protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
    private static final ExtentReports reports = getReportObject();

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Parameters({"browserName"})
    @BeforeMethod
    public void setupTest(@Optional String browserName, ITestResult iTestResult) {

        ChromeOptions co = new ChromeOptions();
        FirefoxOptions fo = new FirefoxOptions();

        if(browserName!=null) {
            browser = browserName;
        } else {
            browser=AppConstants.browserName;
        }

        logger.info("Browser name is: "+browser);

        if(browser.equalsIgnoreCase("chrome")) {
            if(AppConstants.platform.equalsIgnoreCase("local")) {
                //co.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
            } else if(AppConstants.platform.equalsIgnoreCase("remote")) {
                co.setPlatformName("linux");
                co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                //co.addArguments("--remote-allow-origins=*");
                try {
                    //driver = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), co); // standalone
                    //driver = new RemoteWebDriver(new URL("http://192.168.201.3:4444/wd/hub"), co); // hub
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), co); // hub
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            } else if(AppConstants.platform.equalsIgnoreCase("remote_git")) {
                    co.addArguments("--headless"); // for GitHub actions
                    co.addArguments("--disable-gpu");
                    co.addArguments("--no-sandbox");
                    co.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(co);
            } else {
                logger.error("Platform not supported!!");
            }
        }

        else if(browser.equalsIgnoreCase("firefox")) {
            if(AppConstants.platform.equalsIgnoreCase("local")) {
                //fo.addArguments("--remote-allow-origins=*");
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
            } else if(AppConstants.platform.equalsIgnoreCase("remote")) {
                co.setPlatformName("linux");
                co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //driver = new RemoteWebDriver(new URL("http://localhost:4442"), fo); // standalone
                    //driver = new RemoteWebDriver(new URL("http://172.17.208.1:4444/wd/hub"), fo); // hub
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), fo); // hub
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            } else if(AppConstants.platform.equalsIgnoreCase("remote_git")) {
                fo.addArguments("--headless"); // for GitHub actions
                fo.addArguments("--disable-gpu");
                fo.addArguments("--no-sandbox");
                fo.addArguments("--remote-allow-origins=*");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(fo);
            } else {
                logger.error("Platform not supported!!");
            }
        }

        else {logger.info("Invalid Browser Name");}

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
