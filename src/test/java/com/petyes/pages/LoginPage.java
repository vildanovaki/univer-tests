package com.petyes.pages;

import com.petyes.api.Login;
import com.petyes.config.App;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static String breederToken;

    @Step("Авторизоваться с ролью продавца")
    public void setBreederToken() {
        Login login = new Login();
        breederToken = login.loginByAPI("7"+ App.config.breederPhoneNumber(), App.config.userPassword());
        login
                .openMinimalContent()
                .setCookie(breederToken);
    }

    @Step("Проверить успешную авторизацию")
    public void checkLogin(String text) {
        $(".app-landing__cover").shouldHave(text(text));
    }

    @Step("Проверить сообщение об ошибке")
    public void checkErrorMessage() {
        $(".as-alert__content").shouldBe(visible);
    }
}