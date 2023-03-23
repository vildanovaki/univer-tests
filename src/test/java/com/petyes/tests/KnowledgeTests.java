package com.petyes.tests;

import com.petyes.api.Login;
import com.petyes.domain.DataBuilder;
import com.petyes.domain.ItemsForLogin;
import com.petyes.pages.BasePage;
import com.petyes.pages.KnowledgePage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("BreedCatalog")
public class KnowledgeTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Подобрать породу: {0}")
    @Tag("knowledge_tests")
    void chooseABreedTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();
        KnowledgePage knowledgePage = new KnowledgePage();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/breeds/choose");
        knowledgePage
                .choosePetType();
        basePage
                .chooseRadio("Дома")
                .clickOnButton("Далее")
                .clickOnButton("Далее")
                .chooseRadio("Нет")
                .clickOnButton("Далее")
                .chooseCheckbox("Высокий")
                .clickOnButton("Далее")
                .chooseRadio("Да")
                .clickOnButton("Далее")
                .chooseRadio("Нет")
                .clickOnButton("Далее")
                .chooseRadio("Нет")
                .clickOnButton("Далее");
        knowledgePage
                .chooseBubble("Ласковый");
        basePage
                .clickOnButton("Далее");
        knowledgePage
                .chooseBubble("Ласковый питомец");
        basePage
                .clickOnButton("Завершить")
                .checkHeader(1, "Вам подходят следующие породы");
        knowledgePage
                .checkBreedsDisplay("Бенгальская");
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Сравнить породы: {0}")
    @Tag("knowledge_tests")
    void compareBreedsTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();
        KnowledgePage knowledgePage = new KnowledgePage();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/breeds/cat");
        knowledgePage
                .addToComparison("Абиссинская")
                .addToComparison("Австралийская дымчатая")
                .addToComparison("Азиатская короткошерстная / малайская")
                .openComparison();
        basePage
                .clickOnButton("Перейти к сравнению");
        knowledgePage
                .checkComparison("Абиссинская")
                .checkComparison("Австралийская дымчатая")
                .checkComparison("Азиатская короткошерстная / малайская");
        basePage
                .clickOnButton("Очистить список");
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Работа фильтров: {0}")
    @Tag("knowledge_tests")
    void filterBreedsTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();
        KnowledgePage knowledgePage = new KnowledgePage();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/breeds")
                .chooseRadio("Кошки")
                .openFilter("Длина шерсти")
                .chooseCheckbox("Короткая")
                .openFilter("Тип шерсти")
                .chooseCheckbox("Волнистая");
        knowledgePage
                .checkCounter("Показать 7");
        basePage
                .clickOnButton("Показать 7 пород");
        knowledgePage
                .checkResult("Девон-рекс")
                .checkResult("Корниш-рекс")
                .checkResult("Орегон-рекс");
    }

    @Test
    @Tag("knowledge_tests")
    @DisplayName("Переход в создание запроса из раздела Каталог пород")
    void createRequestTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        KnowledgePage knowledgePage = new KnowledgePage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/breeds");
        knowledgePage
                .clickOnRequestButton("Абиссинская");
        basePage
                .checkHeader(1, "Создание запроса на подбор животного");
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Просмотр породы: {0}")
    @Tag("knowledge_tests")
    void viewBreedTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();
        KnowledgePage knowledgePage = new KnowledgePage();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/breeds/abissinskaya")
                .checkHeader(1, "Абиссинская");
        knowledgePage
                .checkCharacteristics("Характерные черты");
    }
}