package com.petyes.pages;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import javax.xml.xpath.XPath;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class BasePage {

    @Step("Открыть страницу")
    public BasePage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Получить id из урла")
    public int getIdFromUrl() {
        int id = Integer.parseInt(url().substring(url().lastIndexOf("/") + 1));
        return id;
    }

    @Step("Нажать на кнопку")
    public BasePage clickOnButton(String text) {
        $$(".as-button__slot").findBy(text(text)).click();
        return this;
    }

    @Step("Перейти к объявлению")
    public BasePage clickOnAdvertisement() {
        $x("//div[@class=\"as-modal__footer\"]//span[text()=\"Перейти к объявлению\"]").click();
        return this;
    }

    @Step("Нажать на кнопку Submit")
    public BasePage clickOnSubmitButton() {
        $("button[type=\"submit\"]").shouldBe(visible).click();
        return this;
    }

    @Step("Проверить заголовок")
    public BasePage checkHeader(int headerNumber, String headerName) {
        $x("//h" + headerNumber).shouldBe(visible).shouldHave(text(headerName));
//        $(".h" + headerNumber).shouldBe(visible).shouldHave(text(headerName));
        return this;
    }

    @Step("Проверить не нулевое количество аукционов")
    public boolean checkNotNullAuctions(int headerNumber) {
        int numberOfAuctions = Integer.parseInt($x("//h1//span").getText());
        if (numberOfAuctions>0) {
            return true;
        }
        else
            return false;
    }

    @Step("Проверить наличие кнопки 'Начать чат' на странице заводчика")
    public BasePage checkContainsOfButton(String nameOfText) {
//        $(".as-button__slot").getAttribute("style").equals("display: none;");
        $$(".user-profile__controls").first().shouldHave(text(nameOfText));
        return this;
    }

    @Step("Выбрать вариант")
    public BasePage chooseRadio(String radio) {
        $$(".as-radio__text").findBy(text(radio)).click();
        return this;
    }

    @Step("Проверка на неактивный радиабатон")
    public boolean checkDisableRadio(String radio) {
        if ($$(".as-radio__text").findBy(text(radio)).isDisplayed())
            return false;
        else
            return true;
    }

    @Step("Выбрать вариант")
    public BasePage chooseCheckbox(String checkbox) {
        $$(".as-checkbox__text").findBy(text(checkbox)).click();
        return this;
    }

    @Step("Ввести значение в поле")
    public BasePage enterValueInInput(String fieldName, String value) {
        $("input[data-vv-name=\"" + fieldName +"\"]").setValue(value);
        return this;
    }

    @Step("Ввести email в поле")
    public BasePage enterEmail(String value) {
        $x("//a[@class=\"app-legacy-footer__tel\"]").scrollTo();
        $(".as-input__field").click();
        $(".as-input__field").setValue(value);
//        $(".as-input__field").setValue(value);
        return this;
    }

    @Step("Очистить значение в поле")
    public BasePage clearValueInInput(String fieldName) {
        $("input[data-vv-name=\"" + fieldName +"\"]").clear();
        return this;
    }

    @Step("Ввести значение в поле")
    public BasePage enterValueByKeys(int index, String value) {
        $(".as-input__field", index).sendKeys(Keys.CONTROL + "A");
        $(".as-input__field", index).sendKeys(Keys.BACK_SPACE);
        $(".as-input__field", index).sendKeys(value);
        return this;
    }

    @Step("Ввести значение в строку поиска")
    public BasePage enterSearchValue(String value) {
        $("input[type=\"search\"]").setValue(value).pressEnter();
        return this;
    }

    @Step("Ввести значение в поле в фильтрах")
    public BasePage enterValueInInputInFilter(int id, String value) {
        $(".as-input__field", id).setValue(value);
        return this;
    }

    @Step("Ввести значение в числовое поле")
    public BasePage enterNumberInInput(String inputName, String value) {
        $("input[data-vv-name=\"" + inputName + "\"]").sendKeys(Keys.CONTROL + "A");
        $("input[data-vv-name=\"" + inputName + "\"]").sendKeys(Keys.BACK_SPACE);
        $("input[data-vv-name=\"" + inputName + "\"]").sendKeys(value);
        return this;
    }

    @Step("Выбрать значение в выпадающем списке")
    public BasePage enterValueInDropdown(String selectName, String value) {
        $("input[data-vv-name=\"" + selectName +"\"]").setValue(value);
        $$(".as-select__option-item").findBy(text(value)).click();
        return this;
    }

    @Step("Выбрать значение в выпадающем списке")
    public BasePage selectValueInDropdown(String selectName, String value) {
        $("div[data-vv-name=\"" + selectName +"\"]").click();
        $$(".as-select__option-item").findBy(text(value)).shouldBe(visible).click();
        return this;
    }

    @Step("Выбрать значение в выпадающем списке в фильтрах")
    public BasePage selectValueInDropdownInFilter(String placeholder, String value) {
        $$(".as-select__container").findBy(text(placeholder)).click();
        $$(".as-select__option-item").findBy(text(value)).shouldBe(visible).click();
        return this;
    }

    @Step("Ввести текст в textarea")
    public BasePage enterValueInTextarea(String textareaName, String value) {
        $("textarea[data-vv-name=\"" + textareaName +"\"]").setValue(value);
        return this;
    }

    @Step("Ввести текст в textarea")
    public BasePage enterValueInSingleTextarea(String value) {
        $(".as-textarea__field").setValue(value);
        return this;
    }

    @Step("Очистить поле textarea")
    public BasePage clearTextarea() {
        $(".as-textarea__field").clear();
        return this;
    }

    @Step("Раскрыть фильтр")
    public BasePage openFilter(String filter) {
        $$(".as-collapse__heading").findBy(text(filter)).click();
        return this;
    }

    @Step("Проверить отображение текста в блоке")
    public BasePage checkBlockDisplay(String text) {
        $$(".as-card__body").findBy(text(text)).shouldBe(visible);
        return this;
    }

    @Step("Проверить отображение текста в модальном окне")
    public void checkModalBlockDisplay(String text) {
        $(".alert-modal__title").shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(text));
    }

    @Step("Проверить результат поиска")
    public void checkResult(String value) {
        $(".search-page__results").shouldHave(text(value));
    }

    @Step("Проверить отображение зеленой всплывашки")
    public BasePage checkGreenMessage() {
        Configuration.timeout = 3000;
        $(".iziToast-color-green").shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие ссылки по id")
    public void checkLinkById(int id) {
        $("a[href$=\""+id+"\"]").shouldBe(exist);
    }

    @Step("Открыть ссылку")
    public BasePage openLink(String link) {
        $("a[href=\"" + link + "\"]").click();
        return this;
    }

    @Step("Кликнуть на бургер сайта")
    public BasePage clickOnBurger() {
        $(".profile-menu").click();
        return this;
    }
    @Step("Нажать на кнопку Выход")
    public BasePage signOut() {
        $$(".as-dropdown-menu__item").findBy(text("Выход")).click();
//        $x("//button[@class=\"as-dropdown-menu__item\"]").findfindBy(text(text)).click();
        return this;
    }
}