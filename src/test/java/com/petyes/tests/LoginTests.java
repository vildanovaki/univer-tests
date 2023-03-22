package com.petyes.tests;

import com.petyes.config.App;
import com.petyes.domain.DataBuilder;
import com.petyes.pages.BasePage;
import com.petyes.pages.LoginPage;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Feature("LogIn")
public class LoginTests extends TestBase {

    @BeforeEach
    void setup() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("https://user1:1234@selenoid.autotests.cloud/wd/hub/"), chromeOptions);
        driver.manage().deleteAllCookies();
    }

    @Test
    @Tag("authorization")
    @AllureId("5696")
    @DisplayName("Успешная авторизация продавца")
    void loginAsBreederTest() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();

        basePage
                .openPage("/login")
                .clearValueInInput("phone")
                .enterValueInInput("phone", App.config.breederPhoneNumber())
                .enterValueInInput("password", App.config.userPassword())
                .clickOnButton("Войти");
        loginPage
                .checkLogin("Находите хозяев для Ваших щенков и котят");
        basePage
                .clickOnBurger()
                .signOut();

    }

    @Test
    @AllureId("5697")
    @Tag("authorization")
    @DisplayName("Успешная авторизация покупателя")
    void loginAsCustomerTest() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();

        basePage
                .openPage("/login")
                .clearValueInInput("phone")
                .enterValueInInput("phone", App.config.customerPhoneNumber())
                .enterValueInInput("password", App.config.userPassword())
                .clickOnButton("Войти");
        loginPage
                .checkLogin("Ваш любимец в паре кликов от Вас");
        basePage
                .clickOnBurger()
                .signOut();
    }

    @Test
    @AllureId("5699")
    @Tag("authorization")
    @DisplayName("Авторизация с неправильным паролем")
    void loginWithWrongPasswordTest() {
        BasePage basePage = new BasePage();
        LoginPage loginPage = new LoginPage();

        basePage
                .openPage("/login")
                .clearValueInInput("phone")
                .enterValueInInput("phone", App.config.customerPhoneNumber())
                .enterValueInInput("password", App.config.wrongPassword())
                .clickOnButton("Войти");
        loginPage
                .checkErrorMessage();
    }
}