package pages.manufacturing;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewManufacturingOrderPage {
    WebDriver driver;
    private By txtProductName = By.xpath("//div[@name='product_id']//input");
    private By lblOrderNumber=By.xpath("//span[@name='name']");

    private By btnSave = By.xpath("//button[@title='Save record']");
    private By btnConfirm = By.xpath("//button[@name='action_confirm']");
    private By btnMarkAsDone = By.xpath("//button[@name='button_mark_done' and @class='btn btn-primary']");
    private By btnOKDialog = By.xpath("//div[@class='modal-dialog']//button[@class='btn btn-primary']");
    private By btnApplyDialog = By.xpath("//div[@class='modal-content']//button[@class='btn btn-primary']/span");
    private By btnCurrentStatus = By.xpath("//button[@title='Current state']");
    private By liFirstProduct=By.xpath("//a[contains(.,'Create and Edit...')]/../../li[1]");

    private By linkManufacturingOrder=By.xpath("//*[@class='breadcrumb']//li/a");

    //view
    private By lblProductName = By.xpath("//a[@name='product_id']/span");

    CommonActions commonActions;

    public NewManufacturingOrderPage(WebDriver driver) {
        this.driver = driver;
        commonActions = new CommonActions(driver);
    }

    public String createManufacturingOrder(String productName) throws InterruptedException {

        commonActions.inputTextBox(txtProductName, productName);
        commonActions.clickElement(liFirstProduct);
        commonActions.waitForPageLoaded();
        commonActions.clickElement(btnSave);
        commonActions.waitForPageLoaded();

        return commonActions.getTextElement(lblOrderNumber);
    }

    public void updateStatusToDone() throws InterruptedException {
        commonActions.waitForElementClickable(btnConfirm);
        commonActions.clickElement(btnConfirm);
        commonActions.waitForPageLoaded();

        commonActions.waitForElementClickable(btnMarkAsDone);
        commonActions.clickElement(btnMarkAsDone);
        commonActions.waitForPageLoaded();
        commonActions.clickElement(btnOKDialog);
        commonActions.waitForPageLoaded();

        Thread.sleep(3000);

        commonActions.clickElement(btnApplyDialog);
        Thread.sleep(2000);
        commonActions.waitForPageLoaded();
    }

    public String getCurrentStatus() {
        return commonActions.getTextElement(btnCurrentStatus);
    }

    public ManufacturingPage goToManufacturingPage(){
        commonActions.clickElement(linkManufacturingOrder);
        return new ManufacturingPage(driver);
    }

    public boolean isOrderInformationCorrectly(String productName){
       if(commonActions.getTextElement(lblProductName).equals(productName))
           return true;
        return false;
    }

}
