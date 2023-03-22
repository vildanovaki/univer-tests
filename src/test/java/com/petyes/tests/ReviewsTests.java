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

@Feature("Reviews")
public class ReviewsTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
    }

    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Оставить отзыв: {0}")
    @Tag("reviewTests")
    void compareBreedsTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/reviews")
                .clickOnButton("Написать отзыв")
                .enterValueInTextarea("review", "Автотестовый отзыв");
    }
}
