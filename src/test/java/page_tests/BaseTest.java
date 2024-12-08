package page_tests;

import base.AppConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;
    protected String browser;

    @BeforeTest
    public void setupTest() {
        browser = AppConstants.browserName;
//        ChromeOptions co = new ChromeOptions();
//        FirefoxOptions fo = new FirefoxOptions();

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
    }

    @AfterTest
    public void tearDownTest() {
        driver.quit();
    }
}