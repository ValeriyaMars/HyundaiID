package ru.marshenina.pages;

import com.codeborne.selenide.SelenideElement;
import org.aeonbits.owner.ConfigFactory;
import ru.marshenina.config.CredentialsConfig;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    AuthorizationPage authorizationPage = new AuthorizationPage();
    private SelenideElement mainPageTitle = $(".baner_baner__3I8IU");
    private final String MAIN_PAGE_TITLE = "Добро пожаловать в Hyundai ID";

    public void loginByEmail(){
        authorizationPage.openAuthPage();
        authorizationPage.setEmailAuth();
        authorizationPage.typeValidEmail(credentials.hidEmailLogin());
        authorizationPage.clickSubmit();
        authorizationPage.typePassword(credentials.hidPassword());
        authorizationPage.clickSubmit();
    }

    public void checkPageTitle(){
        mainPageTitle.shouldHave(text(MAIN_PAGE_TITLE));
    }
}
