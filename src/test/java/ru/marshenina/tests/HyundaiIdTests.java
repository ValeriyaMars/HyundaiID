package ru.marshenina.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import ru.marshenina.pages.AuthorizationPage;
import ru.marshenina.pages.MainPage;
import ru.marshenina.pages.PersonalDataPage;

import static io.qameta.allure.Allure.step;
import static ru.marshenina.tests.TestData.*;

public class HyundaiIdTests extends TestBase {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    PersonalDataPage personalDataPage = new PersonalDataPage();
    Faker faker = new Faker();

    public String notRegisteredEmail = faker.internet().emailAddress();
    public String firstName = faker.address().firstName();
    public String lastName = faker.address().lastName();

    @DisplayName("Authorization By email")
    @Test
    void loginByValidEmailTest() {
        step("Открыть страницу авторизации ", () -> {
            authorizationPage.openAuthPage();
        });

        step("Перейти на авторизацию по email", () -> {
            authorizationPage.setEmailAuth();
        });

        step("Ввести email", () -> {
            authorizationPage.typeValidEmail(authEmailHid);
        });
        step("Нажать на кнопку 'Продолжить'", () -> {
            authorizationPage.clickSubmit();
        });
        step("Ввести пароль", () -> {
            authorizationPage.typePassword(authPasswordHid);
        });
        step("Нажать на кнопку 'Продолжить'", () -> {
            authorizationPage.clickSubmit();
        });

        step("Проверить отображение заголовка на главной странице", () -> {
            mainPage.checkPageTitle();
        });
    }

    @DisplayName("Check authorization form title")
    @Test
    void checkAuthTitle() {
        step("Открыть страницу авторизации ", () -> {
            authorizationPage.openAuthPage();
        });

        step("Проверить отображение заголовка формы авторизации", () -> {
            authorizationPage.checkAuthFormTitle();
        });
    }

    @DisplayName("Open registration page")
    @Test
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
            authorizationPage.typeValidEmail(authEmailHid);
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

    @DisplayName("Authorization by email: not registered email")
    @Test
    @Tag("Негативный кейс")
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
            authorizationPage.typePassword(authPasswordHid);
        });
        step("Нажать на кнопку 'Продолжить'", () -> {
            authorizationPage.clickSubmit();
        });

        step("Проверить отображение ошибки под полем", () -> {
            authorizationPage.checkNotRegisteredEmailError();
        });
    }

    @CsvSource({
         "no domain name, test@mail",
         "without symbol '.', test@mailru",
         "without symbol '@', testmail.ru",
         "more than 31 symbols before '@', testtesttesttesttesttesttesttest@mail.ru",
         "no symbols before '@', @mail.ru",
         "no symbols after '@', @mail.ru",
    })
    @ParameterizedTest(name = "Check errors for authorization by Email : {0}")
    @Tag("Негативный кейс")
    void checkErrorAuthByEmailTest(String invalidEmail) {
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


    @DisplayName("Personal data: change name")
    @Test
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

    @DisplayName("Personal data: change lastname")
    @Test
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

    @DisplayName("Personal data: change birthdate")
    @Test
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

    @DisplayName("Personal data: change city")
    @Test
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

    @DisplayName("Personal data: change address")
    @Test
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
