package ru.marshenina.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"})

public interface WebConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://id.hyundai.ru")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("remoteUrl")
    String getRemoteUrl();

}
