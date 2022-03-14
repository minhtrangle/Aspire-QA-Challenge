package pages.inventory;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewProductPage {
    WebDriver driver;
    //Create form
    private By txtProductName=By.xpath("//input[@name='name']");
    private By txtUnit=By.xpath("//div[@name='uom_id']//input");
    private By txtSalePrice=By.xpath("//div[@name='list_price']//input");
    private By btnUpdateQuantity=By.xpath("//button[@name='action_update_quantity_on_hand']");
    private By btnSave=By.xpath("//button[@title='Save record']");

    //View form
    private By lblProductName=By.xpath("//span[@name='name']");
    private By lblUnit=By.xpath("//a[@name='uom_id']/span");
    private By lblSalePrice=By.xpath("//span[@name='list_price']");

    CommonActions commonActions;

    public NewProductPage(WebDriver driver) {
        this.driver = driver;
        commonActions=new CommonActions(driver);
    }

    public void createProduct(String productName,String unit, Integer salePrice) throws InterruptedException {
        commonActions.inputTextBox(txtProductName,productName);
        commonActions.inputTextBox(txtUnit,unit);
        Thread.sleep(1000);
        commonActions.inputTextBox(txtSalePrice,salePrice.toString());
        commonActions.clickElement(btnSave);
        commonActions.waitForPageLoaded();
    }

    public UpdateQuantityPage goToUpdateQuantityPage(){
        commonActions.waitForElementClickable(btnUpdateQuantity);
        commonActions.clickElement(btnUpdateQuantity);
        return new UpdateQuantityPage(driver);
    }

    public boolean isProductDataLoadedCorrectly(String productName,String unit, Integer salePrice){
        String salePriceText="$ "+salePrice.toString()+".00";

        if(commonActions.getTextElement(lblProductName).equals(productName) & commonActions.getTextElement(lblUnit ).equals(unit)
                &commonActions.getTextElement(lblSalePrice).equals(salePriceText))
            return true;
        return false;
    }

}
