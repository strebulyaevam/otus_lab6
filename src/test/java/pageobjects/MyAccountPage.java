package pageobjects;

import helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage {
    private static Logger Log = LogManager.getLogger(MyAccountPage.class);

    WebDriver driver;
    WebDriverWait waiter;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
        TestHelper.isPageLoad(waiter, loc_personalinfo, "otus.ru/learning/");
    }

    By loc_personalinfo = By.cssSelector("div.nav.nav_mobile-fix.no-print.js-overflow-scroll a[href='/lk/biography/personal/']");

    public PersonalPage clickOnPersonalInfo()
    {
        try {
            TestHelper.clickOnElem(waiter, loc_personalinfo, "About Me link");
            return new PersonalPage(driver);
        } catch (Exception e){
            return null;
        }
    }


}
