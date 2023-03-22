package com.petyes.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.petyes.config.App;
import com.petyes.config.Project;
//import com.petyes.domain.SetupExtension;
import com.petyes.domain.SetupExtension;
import com.petyes.helpers.DriverSettings;
import com.petyes.helpers.DriverUtils;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import com.petyes.helpers.Attach;



@ExtendWith({AllureJunit5.class})
@ExtendWith(SetupExtension.class)
public class TestBase {


    @AfterEach
    public void addAttachments() {
//        String sessionId = DriverUtils.getSessionId();
//
//        AllureAttachments.addScreenshotAs("Last screenshot");
//        AllureAttachments.addPageSource();
//        AllureAttachments.addBrowserConsoleLogs();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

//        Selenide.closeWebDriver();

//        if (Project.isVideoOn()) {
//            AllureAttachments.addVideo(sessionId);
//        }
    }
}