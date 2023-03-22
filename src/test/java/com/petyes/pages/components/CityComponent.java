package com.petyes.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CityComponent {

    @Step("Выбрать город")
    public void chooseCity(String city) {
        $("input[data-vv-name=\"city\"]").sendKeys(Keys.CONTROL + "A");
        $("input[data-vv-name=\"city\"]").sendKeys(Keys.BACK_SPACE);
        $("input[data-vv-name=\"city\"]").sendKeys(city);
        $$(".as-select__option-item").findBy(text(city)).shouldBe(visible);
        $("input[data-vv-name=\"city\"]").sendKeys(Keys.ENTER);
    }
}
