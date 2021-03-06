package pageobjects;

import driverconfig.DriverServies;
import helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private static Logger Log = LogManager.getLogger(SignInPage.class);

    WebDriver driver;
    WebDriverWait waiter;
    DriverServies driverServies;

    public SignInPage(DriverServies driverServies) {
        this.driverServies = driverServies;
        this.driver = driverServies.getDriver();
        waiter = new WebDriverWait(driver, 4);
        TestHelper.isPageLoad(waiter, loc_err_msg, "Login");
    }

    By loc_signinbtn = By.cssSelector("div[data-tab-id='login']");
    By loc_email = By.cssSelector ("form.new-log-reg__form.js-login input[name='email']");
    By loc_pwd = By.cssSelector ("form.new-log-reg__form.js-login input[name='password']");
    By loc_submit = By.cssSelector ("form.new-log-reg__form.js-login button.new-button.new-button_full.new-button_blue.new-button_md");
    By loc_err_msg = By.xpath("//span[@data-registration-type='main' and contains(text(), 'Войдите в свой аккаунт')]");

     public HomePage login(String login, String pwd) {
        Log.info("Login with credentials " + login + " / *** :");
        try {
            TestHelper.clickOnElem(waiter, loc_signinbtn, "SignIn link");
            TestHelper.sendKeysForElem(driver, waiter, loc_email, login, "e-mail");
            TestHelper.sendKeysForElem(driver, waiter, loc_pwd, pwd, "Input password");
            TestHelper.clickOnElem(waiter, loc_submit, "Enter btn");
            waiter.until(ExpectedConditions.invisibilityOfElementLocated(loc_err_msg));
            return new HomePage(driverServies);
            } catch (Exception e) {
                Log.error(e.getMessage(), e);
                return null;
            }
        }

    }
