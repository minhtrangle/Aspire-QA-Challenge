import common.Base;
import common.CommonActions;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.Assert;

import pages.HomePage;
import pages.inventory.InventoryPage;
import pages.LoginPage;
import pages.inventory.NewProductPage;
import pages.inventory.ProductPage;
import pages.inventory.UpdateQuantityPage;
import pages.manufacturing.ManufacturingPage;
import pages.manufacturing.NewManufacturingOrderPage;

public class TestCases extends Base {
    WebDriver driver;
    String userName = "User";
    String productName = "product" + CommonActions.getDateString();
    String unit = "g";
    Integer salePrice = 5;
    Integer cost = 6;
    Integer quantity = CommonActions.randomNumber(11, 1000);
    Integer toConsumeQuantity=CommonActions.randomNumber(1, 10);

    String orderNumber;

    LoginPage loginPage;
    HomePage homePage;
    InventoryPage inventoryPage;
    ProductPage productPage;
    NewProductPage newProductPage;
    UpdateQuantityPage updateQuantityPage;
    ManufacturingPage manufacturingPage;
    NewManufacturingOrderPage newManufacturingOrderPage;

    @BeforeClass
    public void init() {

        driver = getDriver();


    }

    @Parameters({"url", "email", "password"})
    @Test(priority = 0)
    public void login(String url, String email, String password) {
        Reporter.log("Navigate to Aspire site");
        driver.navigate().to(url);

        loginPage = new LoginPage(driver);
        Reporter.log("Login with valid username and password");
        homePage = loginPage.login(email, password);

        Reporter.log("Verify that login successfully: Homepage is displayed and Username is displayed on the top right.");
        Assert.assertTrue(homePage.isHomePageDisplayed(), "TestCases fail. Homepage is not displayed.");
        Assert.assertEquals(homePage.getUserName(), userName, "TestCases fail. Username is not displayed on the top right.");
    }

    @Test (priority = 1)
    public void createProductAndUpdateQuantity() throws InterruptedException {
        Reporter.log("Navigate to Inventory feature");
        inventoryPage = homePage.openInventoryPage();
        Reporter.log("Verify that Inventory page is displayed");
        Assert.assertEquals(inventoryPage.getTextNextHomeIcon(), "Inventory");

        Reporter.log("Select Products->Products");
        productPage = inventoryPage.goToProductPage();

        Reporter.log("Create a new product with information: Product name, Unit,...");
        newProductPage = productPage.goToNewProductPage();
        newProductPage.createProduct(productName, unit, salePrice);
        Reporter.log("Verify the product is created matched with the input data");
        Assert.assertTrue(newProductPage.isProductDataLoadedCorrectly(productName, unit, salePrice), "Product data is not loaded correctly");

        Reporter.log("Update quantity");
        updateQuantityPage = newProductPage.goToUpdateQuantityPage();
        updateQuantityPage.addQuantity(quantity);
        Reporter.log("Verify the quantity is created matched with the input data");
    }

    @Test(priority = 2)
    public void createManufacturingOrder() throws InterruptedException {
        Reporter.log("Click Application icon");
        homePage = inventoryPage.clickApplicationIcon();

        Reporter.log("Navigate the Manufacturing feature");
        manufacturingPage = homePage.openManufacturingPage();

        Reporter.log("Create a manufacturing order");
        newManufacturingOrderPage = manufacturingPage.goToNewManufacturingOrderPage();
        orderNumber=newManufacturingOrderPage.createManufacturingOrder(productName);

        Reporter.log("Update the status of NEW order to DONE");
        newManufacturingOrderPage.updateStatusToDone();
        Reporter.log("Verify the order status is updated to DONE successfully");
        Assert.assertEquals(newManufacturingOrderPage.getCurrentStatus(),"DONE", "Status is not Done");

        Reporter.log("Verify the created Order is created with correct information");
        Assert.assertTrue(newManufacturingOrderPage.isOrderInformationCorrectly(productName),"Order data is not loaded correctly.");

        //  Thread.sleep(5000);
    }

    @AfterMethod
    public void captureScreenshot() {
        CommonActions.captureScreenshot(driver, "a");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
