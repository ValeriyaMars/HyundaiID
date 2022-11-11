package ru.marshenina.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.marshenina.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PersonalDataPage {

    public CalendarComponent calendar = new CalendarComponent();

    private SelenideElement tabPersonalData = $(byText("Личные данные")),
            tabPersonalDataTitle = $(".personal_title__1GXGt"),
            nameInput = $(By.name("name")),
            lastnameInput = $(By.name("lastname")),
            saveButton = $(byText("Сохранить")),
            userNameInTabPersonalData = $(".personal_userName__1FnPj"),
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

    public void changeBirthDate() {
        calendar.setDate("3", "1989", "18");
    }

    public void changeCity(String value) {
        cityInput.click();
        cityInput.sendKeys(deleteString);
        cityInput.setValue(value).pressEnter();
    }

    public void changeAddress(String value) {
        addressInput.click();
        addressInput.sendKeys(deleteString);
        addressInput.setValue(value).pressEnter();
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

    public void checkBirthDate() {
        birthDateInput.shouldHave(value("18.04.1989"));
    }

    public void checkCity(String value) {
        cityInput.shouldHave(value(value));
    }

    public void checkAddress(String city,String address) {
        addressInput.shouldHave(value("Самарская обл, г " + city + ", ул " + address));
    }
}
