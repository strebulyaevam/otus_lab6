package pageobjects;

import helpers.TestHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
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
        deleteAllContacts();
    }

    By loc_fname = By.cssSelector("input[name='fname']");
    By loc_fname_lat = By.cssSelector("input[name='fname_latin']");
    By loc_lname = By.cssSelector("input[name='lname']");
    By loc_lname_lat = By.cssSelector("input[name='lname_latin']");
//    By loc_blogname = By.cssSelector("input[name='blog_name']");
//    By loc_birthdate = By.cssSelector("input[name='date_of_birth']");
//    By loc_blogname = By.cssSelector("input[name='blog_name']");
    By loc_country = By.cssSelector("div.select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-master.js-lk-cv-custom-select div.input.input_full.lk-cv-block__input.lk-cv-block__input_fake.lk-cv-block__input_select-fake.js-custom-select-presentation");
    By loc_country_Rus = By.cssSelector("button.lk-cv-block__select-option.js-custom-select-option[title='Россия']");
    By loc_city = By.cssSelector("div.select.lk-cv-block__input.lk-cv-block__input_full.js-lk-cv-dependent-slave-city.js-lk-cv-custom-select div.input.input_full.lk-cv-block__input.lk-cv-block__input_fake.lk-cv-block__input_select-fake.js-custom-select-presentation");
    By loc_city_Spb = By.cssSelector("button.lk-cv-block__select-option.js-custom-select-option[title='Санкт-Петербург']");
    By loc_add_btn = By.cssSelector("button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add.js-lk-cv-custom-select-add");
    By loc_phone = By.cssSelector("div.input-group.input-group_right input[name='phone']");
    By loc_del_btn = By.cssSelector("div.container__col.container__col_12.container__col_md-0 button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-delete");

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

    public void setPhone (String phone) throws Exception {
        TestHelper.sendKeysForElem(driver, waiter, loc_phone, phone, "Телефон");
    }

    public void addFacebookContact () throws Exception {
        TestHelper.clickOnElem(waiter, loc_add_btn, "Добавить");
        TestHelper.clickOnElem(waiter, loc_country_Rus, "Россия");
    }

}
