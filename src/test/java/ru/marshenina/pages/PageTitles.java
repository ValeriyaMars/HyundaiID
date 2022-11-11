package ru.marshenina.pages;

public enum PageTitles {


    AUTH_FORM_TITLE("Авторизация"),
    REGISTRATION_FORM_TITLE("Регистрация"),
    PASSWORD_RECOVERY_FORM_TITLE("Восстановление пароля"),
    MAIN_PAGE_TITLE("Добро пожаловать в Hyundai ID");

    private String desc;

    PageTitles(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }
}
