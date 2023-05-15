package ru.marshenina.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.String.valueOf;

public class RandomData {

    private static Faker faker = new Faker();
    static Date fakerDateOfBirthday = faker.date().birthday();

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomBirthMonth() {
        int randomBirthMonth = faker.number().numberBetween(0, 11);
        String randomBirthMonthToString = valueOf(randomBirthMonth);
        return randomBirthMonthToString;
    }

    public static String getRandomBirthYear() {
        return new SimpleDateFormat("y", Locale.ENGLISH).format(fakerDateOfBirthday);
    }

    public static String getRandomBirthDay() {
        return new SimpleDateFormat("dd", Locale.ENGLISH).format(fakerDateOfBirthday);
    }

}
