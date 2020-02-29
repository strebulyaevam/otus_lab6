package pageobjects;

import driverconfig.DriverServies;
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
    DriverServies driverServies;

    public MyAccountPage(DriverServies driverServies) {
        this.driverServies = driverServies;
        this.driver = driverServies.getDriver();
        waiter = new WebDriverWait(driver, 4);
        TestHelper.isPageLoad(waiter, loc_personalinfo, "otus.ru/learning/");
    }

    By loc_personalinfo = By.cssSelector("div.nav.nav_mobile-fix.no-print.js-overflow-scroll a[href='/lk/biography/personal/']");

    public PersonalPage clickOnPersonalInfo()
    {
        try {
            TestHelper.clickOnElem(waiter, loc_personalinfo, "About Me link");
            return new PersonalPage(driverServies);
        } catch (Exception e){
            return null;
        }
    }


}
