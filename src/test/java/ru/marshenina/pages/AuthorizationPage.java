package ru.marshenina.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {


    private SelenideElement formTitle = $(".text-center"),
                            emailAuth = $("#email"),
                            emailInput = $(By.name("email")),
                            submitButton = $(byText("Продолжить")),
                            passwordInput = $(By.name("passwordSingIn")),
                            inputEmailError = $(".input-hyandai_error__3Fm3m");

    public void openAuthPage() {
        open("https://id.hyundai.ru/auth/signIn");
    }

    public void setEmailAuth() {
        emailAuth.click();
    }

    public void typeValidEmail(String value) {
        emailInput.setValue(value);
    }

    public void typeInvalidEmail(String value) {

        emailInput.setValue(value).pressEnter();
    }

    public void typeNotRegisteredEmail(String value) {

        emailInput.setValue(value).pressEnter();
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void typePassword(String value) {
        passwordInput.setValue(value);
    }

    public void checkInvalidEmailError() {
        inputEmailError.shouldHave(text(String.valueOf(InputErrors.INVALID_EMAIL_ERROR_TEXT)));
    }

    public void checkNotRegisteredEmailError() {
        inputEmailError.shouldHave(text(String.valueOf(InputErrors.NOT_REGISTERED_EMAIL_ERROR_TEXT)));
    }

    public void openRegistrationPage() {
        $(byText("Регистрация")).click();
    }

    public void openPasswordRecoveryPage() {
        $(byText("Забыли пароль?")).click();
    }

    public void checkRegistrationFormTitle() {
        formTitle.shouldHave(text(String.valueOf(PageTitles.REGISTRATION_FORM_TITLE)));
    }

    public void checkPasswordRecoveryTitle() {
        formTitle.shouldHave(text(String.valueOf(PageTitles.PASSWORD_RECOVERY_FORM_TITLE)));
    }

    public void checkAuthFormTitle() {
        formTitle.shouldHave(text(String.valueOf(PageTitles.AUTH_FORM_TITLE)));
    }
}
