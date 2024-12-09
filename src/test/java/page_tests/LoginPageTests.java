package page_tests;

//import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.util.InternalException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductPageObject;

public class LoginPageTests extends BaseTest {

    LoginPageObject loginPageObject;
    ProductPageObject productPageObject;

    @Test
    public void userLoginTest() throws InterruptedException {
        loginPageObject = new LoginPageObject(driver);
        productPageObject = loginPageObject.userLogin("standard_user","secret_sauce");
        System.out.println(productPageObject.getTitleOfPage());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
