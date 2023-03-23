package com.petyes.tests;

import com.petyes.api.Login;
import com.petyes.domain.DataBuilder;
import com.petyes.domain.ItemsForLogin;
import com.petyes.pages.BasePage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("Faqs")
public class FaqsTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Оформить подписку в разделе Вопросы: {0}")
    @Tag("faqs_tests")
    void viewBreedTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/faqs")
                .enterEmail("test@test.test")
                .clickOnButton("Подписаться")
                .checkGreenMessage();
    }
}
