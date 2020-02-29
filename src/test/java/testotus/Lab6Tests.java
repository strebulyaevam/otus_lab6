package testotus;

import config.Lab6Config;
import driverconfig.DriverServies;
import helpers.TestHelper;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.PersonalPage;


public class Lab6Tests {

    private static Logger Log = LogManager.getLogger(Lab6Tests.class);

    Lab6Config cfg;
    DriverServies driverServies;

    @BeforeClass
    public void init(){
        cfg = ConfigFactory.create(Lab6Config.class);
        driverServies = new DriverServies();
    }


    //@Parameters({"hostname", "login", "pwd"})
    @Test
    public void checkDataSaving() throws Exception {
        String hostname = cfg.hostname();
//        hostname ="https:\\otus.ru";
        String login = cfg.login();
        String pwd = cfg.pwd();

        HomePage homePage = null;
        PersonalPage personalPage = null;

        TestHelper.getCleanURL(driverServies.getDriver(), hostname);
        HomePage mainPage = new HomePage(driverServies);
        homePage = mainPage.clickOnSignInButton().login(login, pwd);
        Assert.assertNotNull(homePage, "Login is failed");
        personalPage = homePage.getMyAccountPage().clickOnPersonalInfo();
        Assert.assertNotNull(personalPage, "Get Personal info is failed");
        personalPage.deleteAllContacts();
        personalPage.setUpFname(cfg.fname());
        personalPage.setFname_lat(cfg.fname_lat());
        personalPage.setUpLname(cfg.lname());
        personalPage.setLname_lat(cfg.lname_lat());
        personalPage.setCountryRussia();
        personalPage.setCitySbp();
        personalPage.addFacebookContact(cfg.facebook());
        personalPage.addTelegramContact(cfg.telegram());
        personalPage.clickOnSaveAndContinue();
        mainPage.getCleanPage(hostname);
        personalPage = mainPage.clickOnSignInButton().login(login, pwd).getMyAccountPage().clickOnPersonalInfo();
        Assert.assertEquals(personalPage.getFname(), cfg.fname(), "First Name is not equal");
        Log.info("First Name is OK");
        Assert.assertEquals(personalPage.getFname_lat(), cfg.fname_lat(), "First Name lat is not equal");
        Log.info("First Name lat is OK");
        Assert.assertEquals(personalPage.getLname(), cfg.lname(), "Last Name is not equal");
        Log.info("Last Name is OK");
        Assert.assertEquals(personalPage.getLname_lat(), cfg.lname_lat(), "Last Name lat is not equal");
        Log.info("Last Name lat is OK");
        Assert.assertEquals(personalPage.getCountry(), cfg.country(), "Country is not equal");
        Log.info("Country is OK");
        Assert.assertEquals(personalPage.get1stConnectType(), "Facebook", "Facebook is not equal");
        Assert.assertEquals(personalPage.getFacebookAcc(), cfg.facebook(), "Facebook account is not equal");
        Log.info("Facebook is OK");
        Assert.assertEquals(personalPage.get2ndConnectType(), "Тelegram", "Тelegram is not equal");
        Assert.assertEquals(personalPage.getTelegramAcc(), cfg.telegram(), "Тelegram account is not equal");
        Log.info("Тelegram is OK");
    }

//    @Parameters({"browser"})
    @AfterClass
    public void quitBrowser () {
        driverServies.closeDriver();
    }
}
