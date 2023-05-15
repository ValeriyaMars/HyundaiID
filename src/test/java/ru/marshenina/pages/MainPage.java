package ru.marshenina.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    private SelenideElement mainPageTitle = $(".baner_baner__3I8IU");
    private final String MAIN_PAGE_TITLE = "Добро пожаловать в Hyundai ID";

    public void loginByEmail() {
        authorizationPage.openAuthPage();
        authorizationPage.setEmailAuth();
        authorizationPage.typeValidEmail(authorizationPage.hidEmailLogin);
        authorizationPage.clickSubmit();
        authorizationPage.typePassword(authorizationPage.hidPassword);
        authorizationPage.clickSubmit();
    }

    public void checkPageTitle() {
        mainPageTitle.shouldHave(text(MAIN_PAGE_TITLE));
    }
}
