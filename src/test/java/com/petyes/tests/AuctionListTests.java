package com.petyes.tests;

import com.petyes.api.Auction;
import com.petyes.api.Login;
import com.petyes.domain.DataBuilder;
import com.petyes.pages.BasePage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Auction")
public class AuctionListTests extends TestBase {
    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
    }

    @Test
    @DisplayName("Просмотреть не пустую страницу аукциона")
    @Tag("regress")
    @Tag("auctionTests")
    void viewAuctionTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        Auction auction = new Auction();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/auctions")
                .checkHeader(1, "Аукционы");
        basePage.checkNotNullAuctions(1);
    }
}
