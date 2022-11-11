package ru.marshenina.pages;

public enum InputErrors {


    INVALID_EMAIL_ERROR_TEXT("Недопустимый формат email"),
    NOT_REGISTERED_EMAIL_ERROR_TEXT("Введенный email не найден. Проверьте корректность или пройдите регистрацию");

    private String desc;

    InputErrors(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }
}
