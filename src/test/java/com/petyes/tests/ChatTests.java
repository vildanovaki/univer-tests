package com.petyes.tests;

import com.petyes.api.Login;
import com.petyes.domain.DataBuilder;
import com.petyes.pages.BasePage;
import com.petyes.pages.ChatPage;
import com.petyes.pages.components.CalendarComponent;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

@Feature("Chat")
public class ChatTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
        dataBuilder.setBreederId();
    }

    @Test
    @Tag("chat_tests")
    @DisplayName("Отправка и получение сообщения")
    void messageExchangeTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        ChatPage chatPage = new ChatPage();
        CalendarComponent calendarComponent = new CalendarComponent();

        Date dateToday = calendarComponent.getTodayDate();
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
        String message = "Автотестовое сообщение " + formater.format(dateToday);

        login
                .openMinimalContent()
                .setCookie(DataBuilder.customerToken);
        basePage
                .openPage("/user/" + DataBuilder.breeder_id)
                .clickOnButton("Начать чат")
                .enterValueInSingleTextarea(message);
        chatPage
                .sendMessage()
                .checkMessage(message);
        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/chat");
        chatPage
                .openChat("Венера")
                .checkMessage(message);
    }
}