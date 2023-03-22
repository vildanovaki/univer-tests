package com.petyes.tests;

import com.petyes.api.Login;
import com.petyes.domain.DataBuilder;
import com.petyes.domain.ItemsForLogin;
import com.petyes.pages.BasePage;
import com.petyes.pages.SalePage;
import com.petyes.pages.components.CityComponent;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("Sale")
public class SaleListTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Работа фильтров: {0}")
    @Tag("saleListTests")
    void filterSalesTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();
        CityComponent cityComponent = new CityComponent();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/for-sale");
        cityComponent
                .chooseCity("Москва");
        basePage
                .chooseRadio("Кошки")
                .selectValueInDropdownInFilter("Выберите породу", "Абиссинская")
                .chooseRadio("Куплю")
                .enterValueByKeys(0, "0")
                .enterValueByKeys(1, "50000")
                .chooseRadio("Самец")
                .chooseCheckbox("До 6 месяцев")
                .chooseCheckbox("Кастрация/стерилизация")
                .clickOnSubmitButton();
    }

    @Test
    @Tag("saleListTests")
    @DisplayName("Просмотр объявления")
    void viewSaleTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        SalePage salePage = new SalePage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/for-sale");
        salePage
                .selectPetOnSalePage(1);
    }

    @Test
    @Tag("saleListTests")
    @DisplayName("Переход в создание запроса из раздела Объявления")
    void createRequestTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/for-sale")
                .clickOnButton("Оставить заявку")
                .checkHeader(1, "Создание запроса на подбор животного");
    }
}
