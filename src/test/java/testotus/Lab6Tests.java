package testotus;

import config.Lab6Config;
import helpers.TestHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
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

import java.io.IOException;
import java.util.Properties;


public class Lab6Tests {

    private static Logger Log = LogManager.getLogger(Lab6Tests.class);

    WebDriver driver;
    Lab6Config cfg;


    @BeforeClass
    public void init(){

        try{
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } catch (Exception e){
            Log.fatal("New driver for Chrome browser isn't created");
            Assert.fail();
        }
        cfg = ConfigFactory.create(Lab6Config.class);
    }

/*
    Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/Lab6Config.properties"));
        return properties;
    }

    @Test
    public void testProperties() throws IOException {
        Properties properties = loadProperties();
        Log.info("lname = " + properties.getProperty("lname"));
    }
*/


    //@Parameters({"hostname", "login", "pwd"})
    @Test
    public void checkDataSaving() throws Exception {
        String hostname = cfg.hostname();
//        hostname ="https:\\otus.ru";
        String login = cfg.login();
        String pwd = cfg.pwd();

        HomePage homePage = null;
        PersonalPage personalPage = null;

        TestHelper.getCleanURL(driver, hostname);
        driver.manage().window().maximize();
        HomePage mainPage = new HomePage(driver);
        homePage = mainPage.clickOnSignInButton().login(login, pwd);
        Assert.assertNotNull(homePage, "Login is failed");
        personalPage = homePage.getMyAccountPage().clickOnPersonalInfo();
        Assert.assertNotNull(personalPage, "Get Personal info is failed");
        personalPage.setUpFname(cfg.fname());
        personalPage.setFname_lat(cfg.fname_lat());
        personalPage.setUpLname(cfg.lname());
        personalPage.setLname_lat(cfg.lname_lat());
        personalPage.setCountryRussia();
        personalPage.setCitySbp();
        personalPage.setPhone(cfg.phone());
   }

//    @Parameters({"browser"})
    @AfterClass
    public void quitBrowser () {
        if(driver!=null){
            Log.info("Quit from browser");
//            driver.quit();
        }
    }
}
