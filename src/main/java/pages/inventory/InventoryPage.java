package pages.inventory;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class InventoryPage {
    WebDriver driver;
    private By linkNextToHomeIcon=By.xpath("//a[@title='Home menu']/following-sibling::a");
    private By menuProducts=By.xpath("//button[@title='Products']");
    private By subMenuProducts=By.xpath("//button[@title='Products']/../div/a[.='Products']");
    private By iconApplication=By.xpath("//a[@title='Home menu']");

    CommonActions commonActions;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        commonActions=new CommonActions(driver);
    }

    public String getTextNextHomeIcon(){
        commonActions.waitForPageLoaded();
        return commonActions.getTextElement(linkNextToHomeIcon);
    }
    public ProductPage goToProductPage(){
        commonActions.clickElement(menuProducts);
        commonActions.clickElement(subMenuProducts);
        return new ProductPage(driver);
    }

    public HomePage clickApplicationIcon(){
        commonActions.clickElement(iconApplication);
        return new HomePage(driver);
    }

}
