package pageobjects;

import helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private static Logger Log = LogManager.getLogger(MainPage.class);

    WebDriver driver;
    WebDriverWait waiter;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
    }

    By loc_login_registr = By.cssSelector("button[data-modal-id='new-log-reg']");


    public SignInPage clickOnSignInButton()
    {
        try {
            TestHelper.clickOnElem(waiter, loc_login_registr, "SignIn and Registr btn");
            return new SignInPage(driver);
        } catch (Exception e){
            return null;
        }
    }
}
