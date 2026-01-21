package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import reader.ReaderDataFromJson;
import utils.ScreenRecorderUtil;
import utils.UtilsTests;
import static reader.ReaderDataFromJson.dataModel;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.time.Duration;


public class BaseTest {
    protected WebDriver  driver;
    protected HomePage homePage;
    ReaderDataFromJson readerDataFromJson;
    ChromeOptions chromeOptions;
    FirefoxOptions firefoxOptions;
    UtilsTests utilsTests;


    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional("Chrome") String browser)  throws FileNotFoundException {
        setUpBrowser(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        homePage = new HomePage(driver);

    }


    @BeforeMethod
    public void gohome(Method method) throws Exception {
        readerDataFromJson = new ReaderDataFromJson();
        driver.get(dataModel().URL);
        //ScreenRecorderUtil.startRecord(method.getName());
        //driver.get("https://the-internet.herokuapp.com/");
    }


    @AfterMethod
    public void afterMethod(Method method, ITestResult result) throws Exception {
        utilsTests = new UtilsTests(driver);
        utilsTests.takeScreenShot(method);
        //ScreenRecorderUtil.stopRecord();
        utilsTests.setStatus(method , result);

    }

    @AfterClass
    public void close()
    {
        driver.quit();
    }

    @Parameters("browser")
    public void setUpBrowser(String browser){
        if (browser.equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("headlessChrome")) {
            chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        }
        else if (browser.equalsIgnoreCase("headlessFirefox")) {
            firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
        }

}

    @BeforeSuite
    public void beforeSuite(){
        utilsTests = new UtilsTests(driver);
        utilsTests.createReport();
    }
    @AfterSuite
    public void afterSuite(){
        utilsTests = new UtilsTests(driver);
        utilsTests.flushReport();
    }


}

