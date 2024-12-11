package page_tests;

//import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.InternalException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductPageObject;

public class LoginPageTests extends BaseTest {

    LoginPageObject loginPageObject;
    ProductPageObject productPageObject;

    private static final Logger logger = LogManager.getLogger(LoginPageTests.class);

    @Test
    public void userLoginTest() throws InterruptedException {
        String username = "standard_user";
        String password = "secret_sauce";
        loginPageObject = new LoginPageObject(driver);
        productPageObject = loginPageObject.userLogin(username,password);
        logger.info("Username is: "+username+" Password is: "+password);
        System.out.println(productPageObject.getTitleOfPage());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
