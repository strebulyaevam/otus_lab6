package pageobjects;

import driverconfig.DriverServies;
import helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static Logger Log = LogManager.getLogger(HomePage.class);

    WebDriver driver;
    WebDriverWait waiter;
    DriverServies driverServies;

    public HomePage(DriverServies driverServies) {
        this.driverServies = driverServies;
        this.driver = driverServies.getDriver();
        waiter = new WebDriverWait(driver, 4);
        TestHelper.isPageLoad(waiter, loc_course_item, "Otus home");
    }

    By loc_login_registr = By.cssSelector("button[data-modal-id='new-log-reg']");
    By loc_user_name = By.cssSelector("p.header2-menu__item-text.header2-menu__item-text__username");
    By loc_course_item = By.xpath("//p[@class = 'header2-menu__item-text' and contains(text(), 'Курсы')]");

    public SignInPage clickOnSignInButton()
    {
        try {
            TestHelper.clickOnElem(waiter, loc_login_registr, "SignIn and Registr btn");
            return new SignInPage(driverServies);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            return null;
        }
    }

    public MyAccountPage getMyAccountPage()
    {
        try {
            waiter.until(ExpectedConditions.visibilityOfElementLocated(loc_user_name)).click();
            TestHelper.getURL(driver, "https://otus.ru/learning/");
            return new MyAccountPage(driverServies);
        } catch (Exception e){
            return null;
        }
    }

    public void getCleanPage (String hostname){
        TestHelper.getCleanURL(driver, hostname);
    }
}
