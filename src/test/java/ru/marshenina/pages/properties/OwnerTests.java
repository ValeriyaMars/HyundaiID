package ru.marshenina.pages.properties;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import ru.marshenina.config.CredentialsConfig;

import static java.lang.String.format;

public class OwnerTests {

    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    @Test
    void readCredentialsTest(){
        String login = credentials.login();
        String password = credentials.password();

        String browserUrl = format("https:// %s: %s selenoid.autotests.cloud/wd/hub/",login, password);

        System.out.println(login);
        System.out.println(password);
    }
}
