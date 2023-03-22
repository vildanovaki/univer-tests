package com.petyes.pages;

import io.qameta.allure.Step;

import javax.xml.xpath.XPath;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SalePage {

    @Step("Открыть страницу питомца на странице продажи")
    public SalePage selectPetOnSalePage(int number) {
        $$(".sale-offer-preview").get(number).click();
        return this;
    }

    @Step("Проверить содержание кнопки на странице питомца с продажи")
    public SalePage checkButton(String text) {
        $$(".as-button__slot").findBy(text(text)).click();
        return this;
    }
}
