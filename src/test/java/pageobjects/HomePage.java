package pageobjects;

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

    public HomePage(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
        waiter.until(ExpectedConditions.elementToBeClickable(loc_course_item));
    }

    By loc_login_registr = By.cssSelector("button[data-modal-id='new-log-reg']");
    By loc_user_name = By.cssSelector("p.header2-menu__item-text.header2-menu__item-text__username");
    By loc_course_item = By.xpath("//p[@class = 'header2-menu__item-text' and contains(text(), 'Курсы')]");

    public SignInPage clickOnSignInButton()
    {
        try {
            TestHelper.clickOnElem(waiter, loc_login_registr, "SignIn and Registr btn");
            return new SignInPage(driver);
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
            return new MyAccountPage(driver);
        } catch (Exception e){
            return null;
        }
    }
}
