package com.petyes.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import javax.xml.xpath.XPath;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.sql.DriverManager.getDriver;

public class RequestPage {

    @Step("Выбрать вид животного")
    public RequestPage selectPetType(String placeholder, String value) {
        $$(".as-select__container").findBy(text(placeholder)).click();
        $$(".as-select__dropdown-menu .as-select__option-item").findBy(text(value)).click();
        return this;
    }

    @Step("Выбрать породу животного")
    public RequestPage selectPetBreed(String placeholder, String value) {
        $$(".as-select__container").findBy(text(placeholder)).click();
        $$(".as-select__dropdown-menu .as-select__option-item").findBy(text(value)).click();
        return this;
    }
    @Step("Выбрать город")
    public RequestPage selectCity(String placeholder, String value) {
        $$(".as-button__slot").findBy(text("Продолжить")).scrollTo();
        $$(".as-select__placeholder").findBy(text(placeholder)).click();
        $x("//input[@data-vv-name=\"city\"]").setValue(value).pressEnter();
        $x("//input[@data-vv-name=\"city\"]").pressEnter().pressEnter().pressEnter();
        return this;
    }

    @Step("Нажать на кнопку Отправить")
    public RequestPage clickSendButton(String text) {
        $$(".as-button__slot").findBy(text(text)).click();
        return this;
    }

    @Step("Проверить успешное создание запроса")
    public RequestPage checkSuccessRequest() {
        $(".as-modal__content").shouldHave(text("Запрос успешно создан"));
        return this;
    }

    @Step("Выбрать дополнительный параметр")
    public RequestPage chooseOption(String optionName, String radio) {
        $$(".as-form-item").findBy(text(optionName)).$$(".as-radio__text").findBy(text(radio)).click();
        return this;
    }

    @Step("Проверить отображение тэга")
    public RequestPage checkTag(String tag) {
        $(".buy-request__tags").shouldHave(text(tag));
        return this;
    }

    @Step("Проверить отображение значения")
    public RequestPage checkResults(int index, String fieldName, String value) {
        $(".buy-request__params-item", index).shouldHave(text(fieldName));
        $(".buy-request__params-item", index).shouldHave(text(value));
        return this;
    }

    @Step("Удалить запрос")
    public void deleteRequest() {
        $(".as-modal__content").$$(".as-button__slot").findBy(text("Удалить")).click();
    }

    @Step("Ввести причину завершения")
    public void enterReason(String value) {
        $(".as-textarea__field").setValue(value);
    }

    @Step("Проверить отображение объявления")
    public void checkSaleOffer(int index, int id) {
        $(".as-card__body", index).$("a[href$=\""+id+"\"]").should(exist);
    }

    @Step("Выбрать питомца для отклика")
    public void choosePetToResponse(int pet_id) {
        $("input[type=\"checkbox\"][value =\"" + pet_id + "\"]+span").click();
    }
}