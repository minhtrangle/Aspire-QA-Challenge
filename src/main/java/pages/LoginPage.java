package pages;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private WebDriver driver;
    private By txtEmail=By.xpath("//input[@id='login']");
    private By txtPassword=By.xpath("//input[@id='password']");
    private By btnLogin=By.xpath("//button[@type='submit']");

    CommonActions commonActions;

    public LoginPage(WebDriver driver) {
        this.driver=driver;
        commonActions=new CommonActions(driver);
    }

    public HomePage login(String email, String password)  {
        commonActions.waitForPageLoaded();
        commonActions.inputTextBox(txtEmail,email);
        commonActions.inputTextBox(txtPassword,password);
        commonActions.clickElement(btnLogin);
        commonActions.waitForPageLoaded();
        return new HomePage(driver);
    }

}
