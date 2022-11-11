package ru.marshenina.pages.properties;

import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    public void someTest() {
        System.setProperty("browser", "chrome");
        System.setProperty("version", "91");
        System.setProperty("browserSize", "500x500");
        System.setProperty("url","automation-practice-form");
    }
}
