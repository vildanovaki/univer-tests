package com.petyes.tests;

import com.github.javafaker.Faker;
import com.petyes.api.Auction;
import com.petyes.api.Login;
import com.petyes.domain.DataBuilder;
import com.petyes.pages.BasePage;
import com.petyes.pages.components.CalendarComponent;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Date;

@Feature("Breeders")
public class BreederProfileTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
        dataBuilder.setBreederId();
    }

    @Test
    @Tag("breeder_tests")
    @DisplayName("Просмотр продавцом своего профиля")
    void viewBreederProfileTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/user/" + DataBuilder.breeder_id)
                .checkHeader(1, "Продавец  Автотест");
    }

    @Test
    @Tag("breeder_tests")
    @DisplayName("Редактирование продавцом своего профиля (о себе)")
    void editProfileTest() {
        Faker faker = new Faker();
        BasePage basePage = new BasePage();
        Login login = new Login();

        String aboutMe = faker.chuckNorris().fact();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/settings/info")
                .clearTextarea()
                .enterValueInTextarea("description", aboutMe)
                .clickOnButton("Сохранить изменения")
                .checkGreenMessage()
                .openPage("/user/" + DataBuilder.breeder_id)
                .checkBlockDisplay(aboutMe);
    }

    // Тест падает, так как дефект на добавление питомца на продажу - дефект на сайте
    @Test
    @Tag("breeder_tests")
    @DisplayName("Просмотр продавцом объявлений в своем профиле")
    void viewSaleTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setSaleId();
        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/user/" + DataBuilder.breeder_id + "#sales")
                .checkLinkById(DataBuilder.sale_id);
    }

    // Тест падает, так как не работает создание аукциона - дефект на сайте
    @Test
    @Tag("breeder_tests")
    @DisplayName("Просмотр продавцом аукционов в своем профиле")
    void viewAuctionTest() {
        BasePage basePage = new BasePage();
        CalendarComponent calendarComponent = new CalendarComponent();
        Login login = new Login();
        Auction auction = new Auction();

        Date tomorrow = calendarComponent.getOtherDate(1);
        Date dayAfterTomorrow = calendarComponent.getOtherDate(2);

        int auction_id = auction.createAuctionByAPI(tomorrow, dayAfterTomorrow, 10000, 1, 20000, false, false);

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/user/" + DataBuilder.breeder_id + "#auctions")
                .checkLinkById(auction_id);

        auction.deleteAuctionByAPI(auction_id);
    }
}
