package page_tests;

//import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.util.InternalException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;

public class LoginPageTests extends BaseTest {

    LoginPageObject loginPageObject;

    @Test
    public void userLoginTest() throws InterruptedException {
        loginPageObject = new LoginPageObject(driver);
        loginPageObject.userLogin("standard_user","secret_sauce");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
