package page_objects;

import generic_keywords.WebElementsInteractions;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPageObject extends WebElementsInteractions {

    public ProductPageObject(WebDriver driver)
    {
        super(driver);
    }

    //WebDriver driver;
    private final By getTitleOfProductPage = By.xpath("//span[contains(text(), 'Products')]");

    public String getTitleOfPage() {
        return retriveTextdata(getTitleOfProductPage);
    }
}
