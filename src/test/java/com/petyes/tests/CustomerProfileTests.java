package com.petyes.tests;

import com.github.javafaker.Faker;
import com.petyes.api.Login;
import com.petyes.domain.DataBuilder;
import com.petyes.pages.BasePage;
import com.petyes.pages.RequestPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Customer")
public class CustomerProfileTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setCustomerToken();
        dataBuilder.setCustomerId();
    }

    @Test
    @Tag("customer_tests")
    @DisplayName("Просмотр покупателем своего профиля")
    void viewCustomerProfileTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/user/" + DataBuilder.customer_id)
                .checkHeader(1, "Венера");
    }

    @Test
    @Tag("customer_tests")
    @DisplayName("Редактирование покупателем своего профиля (о себе)")
    void editProfileTest() {
        Faker faker = new Faker();
        BasePage basePage = new BasePage();
        Login login = new Login();

        String aboutMe = faker.chuckNorris().fact();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/settings/info")
                .clearTextarea()
                .enterValueInTextarea("description", aboutMe)
                .clickOnButton("Сохранить изменения")
                .checkGreenMessage()
                .openPage("/user/" + DataBuilder.customer_id)
                .checkBlockDisplay(aboutMe);
    }

    @Test
    @Tag("customer_tests")
    @DisplayName("Создание покупателем запроса на покупку")
    void viewRequestTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        RequestPage requestPage = new RequestPage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/user/" + DataBuilder.customer_id + "#requests")
                .clickOnButton("Оставить заявку");
        //                .checkLinkById(DataBuilder.request_id); // Проверка нахождения id запроса питомца
        requestPage
                .selectPetType("Выберите вид животного", "Кошки")
                .selectPetBreed("Выберите породу животного", "Абиссинская")
                .selectCity("Выберите города для поиска", "Санкт-Петербург");
        basePage
                .clickOnButton("Продолжить");
        requestPage
                .clickSendButton("Отправить");
    }
}
