package page_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductPageObject;

public class ProductsPageTests extends BaseTest{

    LoginPageObject loginPageObject;
    ProductPageObject productPageObject;

    private static final Logger logger = LogManager.getLogger(ProductsPageTests.class);

    @Test
    public void testItemName() {
        String username = "performance_glitch_user";
        String password = "secret_sauce";
        loginPageObject = new LoginPageObject(driver);
        productPageObject = loginPageObject.userLogin(username,password);
        logger.info("Username is: "+username+" Password is: "+password);
        System.out.println(productPageObject.getTitleOfPage());
        System.out.println(productPageObject.getItemName());
    }

}
