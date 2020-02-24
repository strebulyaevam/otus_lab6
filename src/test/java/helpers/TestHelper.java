package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;

public class TestHelper {

    private static Logger Log = LogManager.getLogger(TestHelper.class);


    public static void getURL(WebDriver driver, String url) throws Exception {
        Log.info("Try to get " + url);
        driver.manage().window().maximize();

        try {
            driver.get(url);
            Log.info(url + " was got successfully");
        } catch (Exception e) {
            Log.fatal("Host - " + url +" isn't available");
            Assert.fail();
        }
    }

    public static void clickOnElem (WebDriverWait waiter, By loc_elem, String nameOfElem) throws Exception
    {
        try {
            Log.info("Try to click on " + nameOfElem);
            waiter
                    .until(ExpectedConditions.elementToBeClickable(loc_elem)).click();
        } catch (Exception e) {
            Log.error("Error - " + nameOfElem + " isn't clickable at the page", e);
            throw e;
        }
        Log.info(nameOfElem + " was clicked successfully");
    }

    public static void sendKeysForElem (WebDriver driver, WebDriverWait waiter, By loc_elem, String text, String nameOfElem) throws Exception
    {
        try {
            Log.info("Try to input text in " + nameOfElem);
            waiter.until(ExpectedConditions.presenceOfElementLocated(loc_elem)).clear();
            driver.findElement(loc_elem).sendKeys(text);

        } catch (Exception e) {
            Log.error("Error - " + nameOfElem + " isn't presence at the page", e);
            throw e;
        }
        Log.info(nameOfElem + " was populated with text successfully");
    }

    public static String getTextFromValAttr(WebDriver driver, WebDriverWait waiter, By loc_elem, String nameOfElem) throws Exception
    {
        String result = null;
        try {
            Log.info("Try to get text from " + nameOfElem);
            result = waiter.until(ExpectedConditions.presenceOfElementLocated(loc_elem)).getAttribute("value");

        } catch (Exception e) {
            Log.error("Error - " + nameOfElem + " isn't presence at the page", e);
            throw e;
        }
        Log.info("Getting text from " + nameOfElem + " is successfully");
        return result;
    }

    public static String getTextFromElem(WebDriver driver, WebDriverWait waiter, By loc_elem, String nameOfElem) throws Exception
    {
        String result = null;
        try {
            Log.info("Try to get text from " + nameOfElem);
            result = waiter.until(ExpectedConditions.presenceOfElementLocated(loc_elem)).getText();

        } catch (Exception e) {
            Log.error("Error - " + nameOfElem + " isn't presence at the page", e);
            throw e;
        }
        Log.info("Getting text from " + nameOfElem + " is successfully");
        return result;
    }

    public static void getCleanURL (WebDriver driver, String url){
        driver.get(url);
        driver.manage().deleteAllCookies();
        driver.get(url);
    }

    public static void isPageLoad (WebDriverWait waiter, By loc_elem, String pageName)
    {
        try{
            waiter.until(ExpectedConditions.elementToBeClickable(loc_elem));
        }catch (Exception e){
            Log.error("Page - " + pageName +" is not loaded");
            Assert.fail("Page is not loaded");
        }
    }


    public static boolean clickOnFirstVisibleElem(WebDriver driver, By loc_elem, String elemName)
    {
        List<WebElement> elements = Collections.EMPTY_LIST;

        elements = driver.findElements(loc_elem);

        if (elements == null) {
            Log.error("Field '"+ elemName + "' is not found");
            return false;
        }

        for (WebElement element : elements) {
            if(element.isDisplayed()) {
                Log.info("Field '"+ elemName + "' is opened successfully");
                element.click();
                return true;
            }
        }
        Log.error("Field '"+ elemName + "' is not displayed");
        return false;
    }


}
