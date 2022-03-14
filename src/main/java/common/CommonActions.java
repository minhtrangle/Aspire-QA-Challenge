package common;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CommonActions {
    private WebDriver driver;
    private WebDriverWait wait;
    private Integer timeoutPageLoadInSecond=20;
    private JavascriptExecutor js;

    static String projectPath = System.getProperty("user.dir") + "/";


    public CommonActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    public static void captureScreenshot(WebDriver driver, String screenName) {
        try {
            Reporter.log("Driver for Screenshot: " + driver);
            TakesScreenshot ts = (TakesScreenshot) driver;
            // get screenshot to convert to file
            File source = ts.getScreenshotAs(OutputType.FILE);
            //create folder to store screenshots
            File theDir = new File(projectPath + "Screenshots");
            if (!theDir.exists()){
                theDir.mkdirs();
            }
            // generate name for the file
            FileHandler.copy(source, new File(projectPath + "Screenshots" + "/" + screenName + "_" + getDateString() + ".png"));
            System.out.println("Screenshot taken: " + screenName);
            Reporter.log("Screenshot taken current URL: " + driver.getCurrentUrl(), true);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

    public void inputTextBox(By element, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);

    }

    public void clickElement(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
    }

    public void sendKeys(By element){
        driver.findElement(element).sendKeys(Keys.TAB);
    }

    public void clickElementByJS(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", driver.findElement(element));
    }

    public String getTextElement(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return  driver.findElement(element).getText();
    }

    public boolean isElementExist(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        List<WebElement> list= driver.findElements(element);
        if(list.size()>0)
            return true;
        return false;
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutPageLoadInSecond));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Page load timeout");
        }
    }

    public void waitForElementClickable(By element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //Utils methods
    public static String getDateString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        return dateFormat.format(new Date());

    }

    public static Integer randomNumber(Integer min, Integer max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
