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
    //private final By getTextOfFirstItem = By.xpath("//a[@id='item_111_title_link']/div"); // For fail test
    private final By getTextOfFirstItem = By.xpath("//a[@id='item_4_title_link']/div");

    public String getTitleOfPage() {
        return retrieveTextData(getTitleOfProductPage);
    }

    public String getItemName() {
        return retrieveTextData(getTextOfFirstItem);
    }
}
