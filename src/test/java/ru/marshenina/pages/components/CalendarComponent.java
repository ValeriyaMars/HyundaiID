package ru.marshenina.pages.components;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Long.valueOf;

public class CalendarComponent {
    public void setDate(String month, String year, String day) {
        $(By.name("birthdate")).click();
        $(".react-datepicker__month-select").selectOptionByValue(month);
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__day--0" + day).click();
    }
}
