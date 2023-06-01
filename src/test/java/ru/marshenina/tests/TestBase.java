package ru.marshenina.tests;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.marshenina.config.CredentialsConfig;
import ru.marshenina.config.WebConfig;
import ru.marshenina.helpers.Attach;

import java.util.Map;

public class TestBase {
    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    static WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

    String login = credentials.login();
    String password = credentials.password();

    @BeforeEach
    void setup() {

        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browser = webConfig.getBrowser();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();
        if (webConfig.getRemoteUrl() != null){
            Configuration.remote = webConfig.getRemoteUrl();
        }

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
