package com.petyes.tests;

import com.petyes.api.Login;
import com.petyes.config.App;
import com.petyes.domain.DataBuilder;
import com.petyes.pages.BasePage;
import com.petyes.pages.LoginPage;
import com.petyes.pages.RequestPage;
import com.petyes.pages.components.CityComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class RequestListTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
    }

    @Test
    @Tag("request_tests")
    @DisplayName("Работа фильтров")
    void filterRequestsTest() {

        BasePage basePage = new BasePage();
        Login login = new Login();
        CityComponent cityComponent = new CityComponent();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/search");
        cityComponent
                .chooseCity("Санкт-Петербург");
        basePage
                .chooseRadio("Кошки")
                .selectValueInDropdownInFilter("Выберите породу", "Абиссинская")
                .chooseRadio("Куплю")
                .enterValueByKeys(1, "9999")
                .enterValueByKeys(2, "10002")
                .chooseRadio("Самец")
                .chooseCheckbox("До 6 месяцев")
                .clickOnSubmitButton();
    }

    @Test
    @Tag("request_tests")
    @DisplayName("Просмотр запроса")
    void viewRequestTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/buy/" + App.config.requestId())
                .checkBlockDisplay("Требуемые характеристики");
    }

    @Test
    @Tag("request_tests")
    @DisplayName("Отклик на запрос")
    void requestApplicationTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/buy/" + App.config.requestId())
                .clickOnButton("Откликнуться");
    }
}
