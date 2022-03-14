package pages.inventory;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;
    private By btnCreateProduct=By.xpath("//button[@title='Create record']");

    CommonActions commonActions;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        commonActions=new CommonActions(driver);
    }

    public NewProductPage goToNewProductPage(){
        commonActions.clickElement(btnCreateProduct);
        return new NewProductPage(driver);
    }
}
