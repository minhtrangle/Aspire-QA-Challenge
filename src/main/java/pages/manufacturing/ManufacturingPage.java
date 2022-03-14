package pages.manufacturing;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManufacturingPage {
    WebDriver driver;
    private By btnCreate=By.xpath("//button[@data-original-title='Create record']");
    private By btnClearOrderSearchOption=By.xpath("//div[@role='search']//*[@title='Remove']");
    private  By txtSearch=By.xpath("//input[@role='searchbox']");

    CommonActions commonActions;

    public ManufacturingPage(WebDriver driver) {
        this.driver = driver;
        commonActions=new CommonActions(driver);
    }

    public NewManufacturingOrderPage goToNewManufacturingOrderPage()    {
        commonActions.clickElement(btnCreate);
        return new NewManufacturingOrderPage(driver);
    }

}
