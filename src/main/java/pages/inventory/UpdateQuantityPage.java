package pages.inventory;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpdateQuantityPage {
    WebDriver driver;
    private By btnCreate=By.xpath("//button[@data-original-title='Create record']");//button[contains(@class,'button_add')]
    private By txtCountedQuantity=By.xpath("//input[@name='inventory_quantity']");
    private By btnSave=By.xpath("//button[@title='Save record']");

    CommonActions commonActions;

    public UpdateQuantityPage(WebDriver driver) {
        this.driver = driver;
        commonActions=new CommonActions(driver);
    }

    public void addQuantity(Integer quantity) {
        commonActions.clickElement(btnCreate);
        commonActions.inputTextBox(txtCountedQuantity,quantity.toString());
        commonActions.clickElement(btnSave);

    }
}
