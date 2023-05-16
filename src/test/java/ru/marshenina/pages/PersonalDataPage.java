package ru.marshenina.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.marshenina.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static java.lang.Long.valueOf;

public class PersonalDataPage {

    public CalendarComponent calendar = new CalendarComponent();

    private SelenideElement tabPersonalData = $(byText("Личные данные")),
            tabPersonalDataTitle = $(".personal_title__3pjfR"),
            nameInput = $(By.name("name")),
            lastnameInput = $(By.name("lastname")),
            saveButton = $(byText("Сохранить")),
            userNameInTabPersonalData = $(".personal_userName__Vi92T"),
            birthDateInput = $(By.name("birthdate")),
            cityInput = $("#city"),
            addressInput = $("#address");

    private final String TAB_PERSONAL_DATA_TITLE = "Личные данные";

    public static String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    public void openTabPersonalData() {
        tabPersonalData.click();
        tabPersonalDataTitle.shouldHave(text(TAB_PERSONAL_DATA_TITLE));
    }

    public void changeName(String value) {
        nameInput.sendKeys(deleteString);
        nameInput.click();
        nameInput.setValue(value);
    }

    public void changeLastname(String value) {
        lastnameInput.sendKeys(deleteString);
        lastnameInput.click();
        lastnameInput.setValue(value);
    }

    public PersonalDataPage changeBirthDate(String month, String year, String day) {
        calendar.setDate(month, year, day);
        return this;
    }

    public void changeCity(String value) {
        cityInput.sendKeys(deleteString);
        cityInput.setValue(value).pressEnter();
        sleep(1000); //ожидаем загрузку данных
        cityInput.pressEnter();
    }

    public void changeAddress(String value) {
        addressInput.click();
        addressInput.sendKeys(deleteString);
        addressInput.setValue(value).pressEnter();
        sleep(1000); //ожидаем загрузку данных
        addressInput.pressEnter();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void checkNameInTabPersonalData(String value) {
        userNameInTabPersonalData.shouldHave(text(value));
    }

    public void checkSurnameInTabPersonalData(String value) {
        userNameInTabPersonalData.shouldHave(text(value));
    }

    public void checkBirthDate(String day, String month, String year) {
        if (valueOf(month) < 10) {
            birthDateInput.shouldHave(value(day + ".0" + (valueOf(month) + 1) + "." + year));
        } else {
            birthDateInput.shouldHave(value(day + "." + (valueOf(month) + 1) + "." + year));
        }
    }

    public void checkCity(String value) {
        cityInput.shouldHave(value(value));
    }

    public void checkAddress(String city, String address) {
        addressInput.shouldHave(value("Самарская обл, г " + city + ", ул " + address));
    }
}
