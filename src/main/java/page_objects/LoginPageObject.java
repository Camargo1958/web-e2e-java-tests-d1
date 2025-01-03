package page_objects;

import generic_keywords.WebElementsInteractions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class LoginPageObject  extends WebElementsInteractions {

    //WebDriver driver;
    private final By userNameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");
    private final By loginBtn = By.id("login-button");

    public LoginPageObject(WebDriver driver)
    {
        super(driver);
    }

//    public void userLogin(String username, String password) {
//        goToApplication("https://www.saucedemo.com");
//        sendText(userNameTextField, username);
//        sendText(passwordTextField, password);
//        clickElement(loginBtn);
//    }

    public ProductPageObject userLogin(String username, String password) {
        goToApplication("https://www.saucedemo.com");
        sendText(userNameTextField, username);
        try {
            Thread.sleep(4000);
            sendText(passwordTextField, password);
            Thread.sleep(4000);
            clickElement(loginBtn);
            Thread.sleep(4000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ProductPageObject(driver);
    }
}
