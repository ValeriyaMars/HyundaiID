package ru.marshenina.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.marshenina.config.CredentialsConfig;
import ru.marshenina.pages.AuthorizationPage;
import ru.marshenina.pages.MainPage;
import ru.marshenina.pages.PersonalDataPage;

import static io.qameta.allure.Allure.step;
import static ru.marshenina.tests.TestData.*;

public class HyundaiIdTests extends TestBase {

    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    PersonalDataPage personalDataPage = new PersonalDataPage();
    Faker faker = new Faker();
    String hidEmailLogin = credentials.hidEmailLogin();
    String hidPassword = credentials.hidPassword();
    public String notRegisteredEmail = faker.internet().emailAddress();
    public String firstName = faker.address().firstName();
    public String lastName = faker.address().lastName();


    @Tag("Positive")
    @Feature("Authorization")
    @DisplayName("Successful login by email")
    @Test
    @AllureId("12834")
    void loginByValidEmailTest() {
        step("Открыть страницу авторизации ", () -> {
            authorizationPage.openAuthPage();
        });

        step("Перейти на авторизацию по email", () -> {
            authorizationPage.setEmailAuth();
        });

        step("Ввести email", () -> {
            authorizationPage.typeValidEmail(hidEmailLogin);
        });
        step("Нажать на кнопку 'Продолжить'", () -> {
            authorizationPage.clickSubmit();
        });
        step("Ввести пароль", () -> {
            authorizationPage.typePassword(hidPassword);
        });
        step("Нажать на кнопку 'Продолжить'", () -> {
            authorizationPage.clickSubmit();
        });

        step("Проверить отображение заголовка на главной странице", () -> {
            mainPage.checkPageTitle();
        });
    }

    @Feature("Authorization")
    @Tag("Positive")
    @DisplayName("Check authorization form title")
    @Test
    @AllureId("12866")
    void checkAuthTitle() {
        step("Открыть страницу авторизации ", () -> {
            authorizationPage.openAuthPage();
        });

        step("Проверить отображение заголовка формы авторизации", () -> {
            authorizationPage.checkAuthFormTitle();
        });
    }

    @Feature("Registration")
    @Tag("Positive")
    @DisplayName("Open registration page")
    @Test
    @AllureId("12844")
    void openRegistrationPage() {
        step("Открыть страницу авторизации ", () -> {
            authorizationPage.openAuthPage();
        });

        step("Нажать на кнопку 'Регистрация'", () -> {
            authorizationPage.openRegistrationPage();
        });

        step("Проверить заголовок страницы 'Регистрация'", () -> {
            authorizationPage.checkRegistrationFormTitle();
        });
    }

    @Tag("Positive")
    @DisplayName("Open password recovery page")
    @Test
    void openPasswordRecoveryPage() {
        step("Открыть страницу авторизации ", () -> {
            authorizationPage.openAuthPage();
        });

        step("Перейти на авторизацию по email", () -> {
            authorizationPage.setEmailAuth();
        });

        step("Ввести email", () -> {
            authorizationPage.typeValidEmail(hidEmailLogin);
        });
        step("Нажать на кнопку 'Продолжить'", () -> {
            authorizationPage.clickSubmit();
        });

        step("Нажать на кнопку 'Забыли пароль?'", () -> {
            authorizationPage.openPasswordRecoveryPage();
        });

        step("Проверить заголовок страницы 'Восстановление пароля'", () -> {
            authorizationPage.checkPasswordRecoveryTitle();
        });
    }

    @Feature("Authorization")
    @Tag("Negative")
    @DisplayName("Authorization by email: not registered email")
    @Test
    @AllureId("12837")
    void loginNotRegisteredEmailTest() {
        step("Открыть страницу авторизации ", () -> {
            authorizationPage.openAuthPage();
        });

        step("Перейти на авторизацию по email", () -> {
            authorizationPage.setEmailAuth();
        });

        step("Ввести незарегистрированный email", () -> {
            authorizationPage.typeNotRegisteredEmail(notRegisteredEmail);
        });
        step("Нажать на кнопку 'Продолжить'", () -> {
            authorizationPage.clickSubmit();
        });
        step("Ввести пароль", () -> {
            authorizationPage.typePassword(hidPassword);
        });
        step("Нажать на кнопку 'Продолжить'", () -> {
            authorizationPage.clickSubmit();
        });

        step("Проверить отображение ошибки под полем", () -> {
            authorizationPage.checkNotRegisteredEmailError();
        });
    }

    @Feature("Authorization")
    @CsvSource({
         "test@mail, no domain name",
         "test@mailru, without symbol '.'",
         "testmail.ru, without symbol '@'",
         "testtesttesttesttesttesttesttest@mail.ru, more than 31 symbols before '@'",
         "@mail.ru, no symbols before '@'",
         "testmail@, no symbols after '@'",
    })
    @ParameterizedTest(name = "Check errors for authorization by Email : {1}")
    @AllureId("12836")
    @Tag("Negative")
    void checkErrorAuthByEmailTest(String invalidEmail, String name) {
        step("Открыть страницу авторизации ", () -> {
            authorizationPage.openAuthPage();
        });

        step("Перейти на авторизацию по email", () -> {
            authorizationPage.setEmailAuth();
        });

        step("Ввести невалидный email", () -> {
            authorizationPage.typeInvalidEmail(invalidEmail);
        });

        step("Проверить отображение ошибки под полем", () -> {
            authorizationPage.checkInvalidEmailError();
        });
    }

    @Feature("Personal data page")
    @Tag("Positive")
    @DisplayName("Personal data: change name")
    @Test
    @AllureId("12847")
    void changeNameTest() {
        step("Пройти авторизацию по email", () -> {
            mainPage.loginByEmail();
        });
        step("Перейти на вкладку 'Личные данные'", () -> {
            personalDataPage.openTabPersonalData();
        });
        step("Изменить имя", () -> {
            personalDataPage.changeName(firstName);
        });
        step("Нажать на кнопку 'Сохранить'", () -> {
            personalDataPage.clickSaveButton();
        });
        step("Проверить, что отображается измененное имя", () -> {
            personalDataPage.checkNameInTabPersonalData(firstName);
        });
    }

    @Feature("Personal data page")
    @Tag("Positive")
    @DisplayName("Personal data: change lastname")
    @Test
    @AllureId("12848")
    void changLastNameTest() {
        step("Пройти авторизацию по email", () -> {
            mainPage.loginByEmail();
        });
        step("Перейти на вкладку 'Личные данные'", () -> {
            personalDataPage.openTabPersonalData();
        });
        step("Изменить фамилию", () -> {
            personalDataPage.changeLastname(lastName);
        });
        step("Нажать на кнопку 'Сохранить'", () -> {
            personalDataPage.clickSaveButton();
        });
        step("Проверить, что отображается измененная фамилия", () -> {
            personalDataPage.checkSurnameInTabPersonalData(lastName);
        });
    }

    @Feature("Personal data page")
    @Tag("Positive")
    @DisplayName("Personal data: change birthdate")
    @Test
    @AllureId("12849")
    void changeDateOfBirth() {
        step("Пройти авторизацию по email", () -> {
            mainPage.loginByEmail();
        });
        step("Перейти на вкладку 'Личные данные'", () -> {
            personalDataPage.openTabPersonalData();
        });
        step("Изменить дату рождения", () -> {
            personalDataPage.changeBirthDate();
        });
        step("Нажать на кнопку 'Сохранить'", () -> {
            personalDataPage.clickSaveButton();
        });
        step("Проверить, что отображается измененная дата", () -> {
            personalDataPage.checkBirthDate();
        });
    }

    @Feature("Personal data page")
    @Tag("Positive")
    @DisplayName("Personal data: change city")
    @Test
    @AllureId("12851")
    void changeCityTest() {
        step("Пройти авторизацию по email", () -> {
            mainPage.loginByEmail();
        });
        step("Перейти на вкладку 'Личные данные'", () -> {
            personalDataPage.openTabPersonalData();
        });
        step("Изменить город", () -> {
            personalDataPage.changeCity(city);
        });
        step("Нажать на кнопку 'Сохранить'", () -> {
            personalDataPage.clickSaveButton();
        });
        step("Проверить, что отображается выбранный город", () -> {
            personalDataPage.checkCity(city);
        });
    }

    @Feature("Personal data page")
    @Tag("Positive")
    @DisplayName("Personal data: change address")
    @Test
    @AllureId("12854")
    void changeAddressTest() {
        step("Пройти авторизацию по email", () -> {
            mainPage.loginByEmail();
        });
        step("Перейти на вкладку 'Личные данные'", () -> {
            personalDataPage.openTabPersonalData();
        });
        step("Изменить город", () -> {
            personalDataPage.changeCity(city);
        });
        step("Изменить адрес", () -> {
            personalDataPage.changeAddress(address);
        });
        step("Нажать на кнопку 'Сохранить'", () -> {
            personalDataPage.clickSaveButton();
        });
        step("Проверить, что отображается выбранный адрес", () -> {
            personalDataPage.checkAddress(city, address);
        });
    }

}
