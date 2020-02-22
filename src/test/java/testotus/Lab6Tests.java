package testotus;

import helpers.TestHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import pageobjects.HomePage;
import pageobjects.MyAccountPage;
import pageobjects.PersonalPage;


public class Lab6Tests {

    private static Logger Log = LogManager.getLogger(Lab6Tests.class);

    WebDriver driver;


    @BeforeClass
    public void init(){

        try{
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } catch (Exception e){
            Log.fatal("New driver for Chrome browser isn't created");
            Assert.fail();
        }
    }

    @Parameters({"hostname", "login", "pwd"})
    @Test
    public void checkDataSaving(String hostname, String login, String pwd) throws Exception {
        HomePage homePage = null;
        PersonalPage personalPage = null;

        TestHelper.getCleanURL(driver, hostname);
        driver.manage().window().maximize();
        HomePage mainPage = new HomePage(driver);
        homePage = mainPage.clickOnSignInButton().login(login, pwd);
        Assert.assertNotNull(homePage, "Login is failed");
        personalPage = homePage.getMyAccountPage().clickOnPersonalInfo();
        Assert.assertNotNull(personalPage, "Get Personal info is failed");
   }

    @Parameters({"browser"})
    @AfterClass
    public void quitBrowser (String browser) {
        if(driver!=null){
//            Log.info("Quit from " + browser);
//            driver.quit();
        }
    }
}
