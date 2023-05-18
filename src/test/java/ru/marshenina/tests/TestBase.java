package ru.marshenina.tests;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.marshenina.config.CredentialsConfig;
import ru.marshenina.helpers.Attach;

import java.util.Map;

import static java.lang.String.format;

public class TestBase {
    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    String login = credentials.login();
    String password = credentials.password();

    @BeforeEach
    void setup() {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://id.hyundai.ru");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "100.0");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");

        String selenoidUrl = System.getProperty("url", "selenoid.autotests.cloud/wd/hub/");
        String browserUrl = format("https://%s:%s%s", login, password, selenoidUrl);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
