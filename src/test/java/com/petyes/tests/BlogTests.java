package com.petyes.tests;

import com.petyes.api.Login;
import com.petyes.api.Pet;
import com.petyes.domain.DataBuilder;
import com.petyes.domain.ItemsForLogin;
import com.petyes.pages.BasePage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("Blog")
public class BlogTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
        dataBuilder.setPetId();
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Проверка доступности публикации статьи: {0}")
    @Tag("blog_tests")
    void createArticleTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/knowledge/articles")
                .clickOnButton("Опубликовать статью")
                .checkHeader(1, "Создание статьи");
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Работа фильтров: {0}")
    @Tag("regress")
    @Tag("blog_tests")
    void filterArticlesTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/knowledge/articles")
                .chooseRadio("Кошки")
                .openFilter("Категория")
                .chooseCheckbox(DataBuilder.categoryName)
                .clickOnButton("Показать");
    }
}
