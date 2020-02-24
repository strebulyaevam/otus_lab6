package pageobjects;

import helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class PersonalPage {
    private static Logger Log = LogManager.getLogger(PersonalPage.class);

    WebDriver driver;
    WebDriverWait waiter;

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
        TestHelper.isPageLoad(waiter, loc_fname, "/lk/biography/personal/");
    }

    By loc_fname = By.cssSelector("input[name='fname']");
    By loc_fname_lat = By.cssSelector("input[name='fname_latin']");
    By loc_lname = By.cssSelector("input[name='lname']");
    By loc_lname_lat = By.cssSelector("input[name='lname_latin']");
    By loc_country = By.cssSelector("div.select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-master.js-lk-cv-custom-select div.input.input_full.lk-cv-block__input.lk-cv-block__input_fake.lk-cv-block__input_select-fake.js-custom-select-presentation");
    By loc_country_Rus = By.cssSelector("button.lk-cv-block__select-option.js-custom-select-option[title='Россия']");
    By loc_city = By.cssSelector("div.select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-slave-city.js-lk-cv-custom-select div.input.input_full.lk-cv-block__input.lk-cv-block__input_fake.lk-cv-block__input_select-fake.js-custom-select-presentation");
    By loc_city_Spb = By.cssSelector("button.lk-cv-block__select-option.js-custom-select-option[title='Санкт-Петербург']");
    By loc_add_btn = By.cssSelector("button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add.js-lk-cv-custom-select-add");
    By loc_del_btn = By.cssSelector("div.container__col.container__col_12.container__col_md-0 button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-delete");
    By loc_connect_type = By.xpath("//div[@class='input input_full lk-cv-block__input input_straight-bottom-right input_straight-top-right input_no-border-right lk-cv-block__input_fake lk-cv-block__input_select-fake js-custom-select-presentation']//span[contains(text(), 'Способ связи')]");
    By loc_facebook = By.cssSelector("button[data-value='facebook']");
    By loc_fbookacc = By.cssSelector("input[id='id_contact-2-value'");
    By loc_telegram = By.cssSelector("button[data-value='telegram']");
    By loc_telegramacc = By.cssSelector("input[id='id_contact-3-value'");
    By loc_save_and_continue = By.cssSelector("button.button.button_md-4.button_gray.lk-cv-action-buttons__button.lk-cv-action-buttons__button_gray.js-disable-on-submit");
    By loc_1stconnect_type = By.xpath("//label[input[@name='contact-0-service']]//div");
    By loc_2ndtconnect_type = By.xpath("//label[input[@name='contact-1-service']]//div");
    By loc_fbookacc_fill = By.cssSelector("input[name='contact-0-value']");
    By loc_telegramacc_fill = By.cssSelector("input[name='contact-1-value']");

    public void deleteAllContacts()
    {
        int count = 0;
        List<WebElement> elements = Collections.EMPTY_LIST;

        elements = driver.findElements(loc_del_btn);
        Log.info("Amount of contacts to delete - " + elements.size());

        if (elements == null)
            return;

        for (WebElement element : elements) {
            count++;
            if(element.isDisplayed()) {
                Log.info(count + " contact was deleted");
                element.click();
            }
        }
        return;
    }

    public void setUpFname (String fname) throws Exception {
        TestHelper.sendKeysForElem(driver, waiter, loc_fname, fname, "Имя");
    }

    public void setFname_lat (String fname_lat) throws Exception {
        TestHelper.sendKeysForElem(driver, waiter, loc_fname_lat, fname_lat, "Имя латиницей");
    }

    public void setUpLname (String lname) throws Exception {
        TestHelper.sendKeysForElem(driver, waiter, loc_lname, lname, "Имя");
    }

    public void setLname_lat (String lname_lat) throws Exception {
        TestHelper.sendKeysForElem(driver, waiter, loc_lname_lat, lname_lat, "Имя латиницей");
    }

    public void setCountryRussia () throws Exception {
        TestHelper.clickOnElem(waiter, loc_country, "Страна");
        TestHelper.clickOnElem(waiter, loc_country_Rus, "Россия");
    }

    public void setCitySbp () throws Exception {
        TestHelper.clickOnElem(waiter, loc_city, "Город");
        TestHelper.clickOnElem(waiter, loc_city_Spb, "Санкт-Петербург");
    }

    public void addFacebookContact (String facebookacc) throws Exception {
        TestHelper.clickOnElem(waiter, loc_add_btn, "Добавить");
        TestHelper.clickOnFirstVisibleElem(driver, loc_connect_type, "Способ связи");
        TestHelper.clickOnFirstVisibleElem(driver, loc_facebook, "facebook item in the list");
       TestHelper.sendKeysForElem(driver, waiter, loc_fbookacc, facebookacc, "Facebook acc");
    }

    public void addTelegramContact (String telegramacc) throws Exception {
        TestHelper.clickOnElem(waiter, loc_add_btn, "Добавить");
        TestHelper.clickOnFirstVisibleElem(driver, loc_connect_type, "Способ связи");
        TestHelper.clickOnFirstVisibleElem(driver, loc_telegram, "telegram item in the list");
        TestHelper.sendKeysForElem(driver, waiter, loc_telegramacc, telegramacc, "Telegram acc");
    }

    public void clickOnSaveAndContinue () throws Exception {
        TestHelper.clickOnElem(waiter, loc_save_and_continue, "Сохранить заполнить позже");
    }

    public String getFname () throws Exception {
        return TestHelper.getTextFromValAttr(driver, waiter, loc_fname, "Имя");
    }

    public String getFname_lat () throws Exception {
        return TestHelper.getTextFromValAttr(driver, waiter, loc_fname_lat, "Имя латиница");
    }

    public String getLname () throws Exception {
        return TestHelper.getTextFromValAttr(driver, waiter, loc_lname, "Фамилия");
    }

    public String getLname_lat () throws Exception {
        return TestHelper.getTextFromValAttr(driver, waiter, loc_lname_lat, "Фамилия латиница");
    }

    public String getCountry () throws Exception {
        return TestHelper.getTextFromElem(driver, waiter, loc_country, "Страна");
    }

    public String getCity () throws Exception {
        return TestHelper.getTextFromElem(driver, waiter, loc_city, "Страна");
    }

    public String get1stConnectType () throws Exception {
        return TestHelper.getTextFromElem(driver, waiter, loc_1stconnect_type, "1st connect type");
    }

    public String get2ndConnectType () throws Exception {
        return TestHelper.getTextFromElem(driver, waiter, loc_2ndtconnect_type, "2nd connect type");
    }

    public String getFacebookAcc () throws Exception {
        return TestHelper.getTextFromValAttr(driver, waiter, loc_fbookacc_fill, "Facebook Account");
    }

    public String getTelegramAcc () throws Exception {
        return TestHelper.getTextFromValAttr(driver, waiter, loc_telegramacc_fill, "Telegram Account");
    }
}
