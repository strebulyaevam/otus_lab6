package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalPage {
    private static Logger Log = LogManager.getLogger(PersonalPage.class);

    WebDriver driver;
    WebDriverWait waiter;

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
        waiter.until(ExpectedConditions.elementToBeClickable(loc_fname));
    }

    By loc_personalinfo = By.cssSelector("div.nav.nav_mobile-fix.no-print.js-overflow-scroll[style=''] a[href='/lk/biography/personal/']");
    By loc_fname = By.cssSelector("input[name='fname']");

}
