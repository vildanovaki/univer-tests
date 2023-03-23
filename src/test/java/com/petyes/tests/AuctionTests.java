package com.petyes.tests;

import com.petyes.api.Auction;
import com.petyes.api.Login;
import com.petyes.api.Pet;
import com.petyes.domain.DataBuilder;
import com.petyes.domain.ItemsForLogin;
import com.petyes.pages.BasePage;
import com.petyes.pages.components.CalendarComponent;
import com.petyes.pages.components.CityComponent;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.text.SimpleDateFormat;
import java.util.Date;

@Feature("Auction")
public class AuctionTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
        dataBuilder.setPetId();
    }


    //Дефект на создание аукциона питомца - на сервере приходит 500 ошибка на метод создания аукциона
    // на проме при создании аукциона применяется не энд поинт для создания аукциона
    @EnumSource(ItemsForLogin.class)
    @ParameterizedTest(name = "Работа фильтров: {0}")
    @Tag("auction_tests")
    void filterAuctionsTest(ItemsForLogin items) {
        BasePage basePage = new BasePage();
        Login login = new Login();
        CityComponent cityComponent = new CityComponent();
        CalendarComponent calendarComponent = new CalendarComponent();
        Auction auction = new Auction();

        SimpleDateFormat formaterAuction = new SimpleDateFormat("dd.MM.yyyy HH:mm z");

        Date dateTomorrow = calendarComponent.getOtherDate(1);
        String tomorrow = formaterAuction.format(dateTomorrow);

        Date dateDayAfterTomorrow = calendarComponent.getOtherDate(2);
        String dayAfterTomorrow = formaterAuction.format(dateDayAfterTomorrow);

//        int auction_id = auction.createAuctionByAPI(dateTomorrow, dateDayAfterTomorrow, 10000, 1,
//                20000, false, false);

        login
                .openMinimalContent()
                .setCookie(items.getToken());
        basePage
                .openPage("/auctions");
        cityComponent
                .chooseCity("Санкт-Петербург");
        basePage
                .chooseCheckbox("Приватные")
                .openFilter("Блиц-цена")
                .chooseRadio("Есть")
                .openFilter("Дата начала")
                .enterValueInInputInFilter(0, tomorrow)
                .openFilter("Дата окончания")
                .enterValueInInputInFilter(1, dayAfterTomorrow)
                .openFilter("Животные")
                .chooseRadio("Кошки")
                .openFilter("Порода")
                .selectValueInDropdownInFilter("Выберите породу", "Абиссинская")
                .openFilter("Ценовой диапазон, ₽")
                .enterValueByKeys(2, "9999")
                .enterValueByKeys(3, "10001")
                .openFilter("Пол")
                .chooseRadio("Самец")
                .openFilter("Возраст")
                .chooseCheckbox("До 6 месяцев")
                .openFilter("Особенности")
                .chooseCheckbox("Кастрация/стерилизация")
                .clickOnButton("Показать");
//                .checkLinkById(auction_id);
//
//        auction.deleteAuctionByAPI(auction_id);
    }

    @Test
    @Tag("auction_tests")
    @DisplayName("Проверить неактивный радиабатон Аукцион")
    void auctionPetTest() {
        Pet pet = new Pet();
        BasePage basePage = new BasePage();
        Login login = new Login();
        CalendarComponent calendarComponent = new CalendarComponent();
        Auction auction = new Auction();

        Date birth = calendarComponent.getOtherDate(-20);
        int pet_id = pet.createPetByAPI(false, "autoTestAuctionCat", birth, 0, 1);

        SimpleDateFormat formaterAuction = new SimpleDateFormat("dd.MM.yyyy HH:mm z");

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/for-sale/create/" + pet_id)
                .checkDisableRadio("Аукцион");
    }
}
