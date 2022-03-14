package pages;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.inventory.InventoryPage;
import pages.manufacturing.ManufacturingPage;

public class HomePage {
    WebDriver driver;
    private By btnDiscuss=By.xpath("//div[.='Discuss']");
    private By btnInventory=By.xpath("//div[.='Inventory']");
    private By btnManufacturing=By.xpath("//div[.='Manufacturing']");
    private By spanUserNameLogin=By.xpath("//img[contains(@class,'user_avatar')]/../span");

    CommonActions commonActions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        commonActions=new CommonActions(driver);
    }

    public InventoryPage openInventoryPage()
    {
        commonActions.waitForPageLoaded();
        commonActions.clickElement(btnInventory);
        return new InventoryPage(driver);
    }

    public ManufacturingPage openManufacturingPage()
    {
        commonActions.clickElement(btnManufacturing);
        return new ManufacturingPage(driver);
    }

    public boolean isHomePageDisplayed(){
        commonActions.waitForPageLoaded();
        return (commonActions.isElementExist(btnDiscuss)&commonActions.isElementExist(btnInventory)&commonActions.isElementExist(btnManufacturing));
    }

    public String getUserName(){
        return commonActions.getTextElement(spanUserNameLogin);
    }

}
