package com.petyes.domain;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
//import com.petyes.api.Blog;
//import com.petyes.api.Pet;
//import com.petyes.api.Request;
//import com.petyes.api.Sale;
import com.petyes.config.App;
import com.petyes.helpers.DriverSettings;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;

public class SetupExtension extends BaseSetupExtension {
    @Override
    void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
        Configuration.startMaximized = true;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = App.config.webUrl();
        RestAssured.baseURI = App.config.apiUrl();
//        DataBuilder dataBuilder = new DataBuilder();
//        dataBuilder.setBreederToken();
//        dataBuilder.setBreederId();
//        dataBuilder.setCustomerToken();
//        dataBuilder.setCustomerId();
//        dataBuilder.setPetId();
    }
//
    @Override
    public void close() throws Throwable {
    }
}
