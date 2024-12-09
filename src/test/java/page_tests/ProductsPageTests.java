package page_tests;

import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductPageObject;

public class ProductsPageTests extends BaseTest{

    LoginPageObject loginPageObject;
    ProductPageObject productPageObject;

    @Test
    public void testItemName() {
        loginPageObject = new LoginPageObject(driver);
        productPageObject = loginPageObject.userLogin("performance_glitch_user","secret_sauce");
        System.out.println(productPageObject.getTitleOfPage());
        System.out.println(productPageObject.getItemName());
    }

}
