package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Base {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }



    public WebDriver setDriver(String browser){
       switch (browser) {
           case "chrome":
               return initializeChromeDriver();

           case "firefox":
               return initializeFireFoxDriver();

           default:
               return initializeChromeDriver();
       }

   }
    private WebDriver initializeChromeDriver() {
        System.out.println("Launching Chrome browser");
        Reporter.log("Open Chrome.");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initializeFireFoxDriver() {
        System.out.println("Launching Firefox browser");
        Reporter.log("Open Firefox.");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser) {
        try {
            //initializeChromeDriver();
            setDriver(browser);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }

}
