package ru.marshenina.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.marshenina.config.CredentialsConfig;
import ru.marshenina.helpers.Attach;

import static java.lang.String.format;

public class TestBase {
    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    String login = credentials.login();
    String password = credentials.password();

    @BeforeEach
    void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        String selenoidUrl = System.getProperty("url", "selenoid.autotests.cloud/wd/hub/");
        String browserUrl = format("https://%s:%s%s", login, password, selenoidUrl);

        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        Configuration.remote = browserUrl;
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
