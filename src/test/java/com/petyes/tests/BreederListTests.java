package com.petyes.tests;

import com.petyes.api.Login;
import com.petyes.domain.DataBuilder;
import com.petyes.domain.ItemsForLogin;
import com.petyes.pages.BasePage;
import com.petyes.pages.BreedersPage;
import com.petyes.pages.components.CityComponent;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


@Feature("Breeders")
public class BreederListTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
    }

    @Test
    @Tag("breeder_tests")
    @DisplayName("Переход в создание запроса из раздела Заводчики")
    void createRequestTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/breeders")
                .clickOnButton("Найти питомца")
                .checkHeader(1, "Создание запроса на подбор животного");
    }

    @Test
    @Tag("breeder_tests")
    @DisplayName("Открытие страницы заводчика")
    void openBreederTest() {
        Login login = new Login();
        BasePage basePage = new BasePage();
        BreedersPage breedersPage = new BreedersPage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/breeders")
                .enterSearchValue("Продавец Автотест");
        breedersPage
                .openBreederPage("Продавец Автотест");
        basePage
                .checkContainsOfButton("Начать чат");
    }

    @Test
    @Tag("breeder_tests")
    @DisplayName("Оставить отзыв о зоводчике")
    void breederReviewTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        BreedersPage breedersPage = new BreedersPage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/breeders")
                .enterSearchValue("Продавец Автотест");
        breedersPage
                .openRewiewPopup("Продавец Автотест")
                .clickRatingButton(3)
                .setComment("тест");
        basePage
                .clickOnButton("Отправить")
                .clickOnButton("Хорошо");
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Работа фильтров на странице Заводчики: {0}")
    @Tag("breeder_tests")
    void filterBreedersTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();
        CityComponent cityComponent = new CityComponent();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/breeders");
        cityComponent
                .chooseCity("Санкт-Петербург");
        basePage
                .chooseRadio("Кошки")
                .selectValueInDropdownInFilter("Выберите породу", "Абиссинская")
                .clickOnSubmitButton()
                .checkLinkById(DataBuilder.breeder_id);
    }
}