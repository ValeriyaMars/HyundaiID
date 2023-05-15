package ru.marshenina.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {

    Faker faker = new Faker();

    public String hidEmailLogin = "lerun_m@mail.ru",
            hidPassword = "12345678",
            notRegisteredEmail = faker.internet().emailAddress();
    ;

    private SelenideElement formTitle = $(".text-center"),
            emailAuth = $("#email"),
            emailInput = $(By.name("email")),
            submitButton = $(byText("Продолжить")),
            passwordInput = $(By.name("passwordSingIn")),
            inputEmailError = $(".input-hyandai_error__3Fm3m");
    private final String AUTH_FORM_TITLE = "Авторизация",
            REGISTRATION_FORM_TITLE = "Регистрация",
            PASSWORD_RECOVERY_FORM_TITLE = "Восстановление пароля",
            INVALID_EMAIL_ERROR_TEXT = "Недопустимый формат email",
            NOT_REGISTERED_EMAIL_ERROR_TEXT = "Введенный email не найден. Проверьте корректность или пройдите регистрацию";

    public void openAuthPage() {
        open("/auth/signIn");
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
        inputEmailError.shouldHave(text(INVALID_EMAIL_ERROR_TEXT));
    }

    public void checkNotRegisteredEmailError() {
        inputEmailError.shouldHave(text(NOT_REGISTERED_EMAIL_ERROR_TEXT));
    }

    public void openRegistrationPage() {
        $(byText("Регистрация")).click();
    }

    public void openPasswordRecoveryPage() {
        $(byText("Забыли пароль?")).click();
    }

    public void checkRegistrationFormTitle() {
        formTitle.shouldHave(text(REGISTRATION_FORM_TITLE));
    }

    public void checkPasswordRecoveryTitle() {
        formTitle.shouldHave(text(PASSWORD_RECOVERY_FORM_TITLE));
    }

    public void checkAuthFormTitle() {
        formTitle.shouldHave(text(AUTH_FORM_TITLE));
    }
}
