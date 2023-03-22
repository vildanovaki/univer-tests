package com.petyes.api;

import com.petyes.helpers.AllureRestAssuredFilter;
import io.qameta.allure.Step;
import com.petyes.models.LoginData;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class Login {

    public static Cookie cookie;
    String token;

    @Step("Авторизация по API")
    public String loginByAPI(String phone, String password) {
        LoginData user = LoginData.builder()
                .phone(phone)
                .password(password)
                .token_recaptcha("03AFY_a8X_jYz0mulDFgKyApLhmEgCkCPzTgrmID4KzELFA1ufYa3vKG10qKbhl9JkKu84a0xRS0XXWWD4qa_ZoSq8Qa7ErtF-IvrCU-T6zQE8oGTDKL-kydIy-IWOrxHRZ-zV7PUD9Def8MEE4T_xDswwbrSExrKCFYkjQDpWF7SR1hBSGP-sVW9Zxfd5yTwXI-KPBkM1MgQsnTm_-E6Oagmu0eQ62ztllQNTSlnPquApmAGgtL7RMRwiOex-05X22QLiMrR00YfQKGQJ0cg9IGxJ2gfXESCVi-qhI8Ep8622nKjm2svXfOlDZgSPcQH2HbgBet9fma6N3t3vfRFr13eITqbdiJCWmu-3MUs21Mrzq-drIfla1LWrmKtkUYeV0sKPE5AU-6814Dpd7lzpF2aKw0GMXeB9jlhxWPWK9V9OBtHOanDCsHoIhRXn6UGavizmEvZ6B5lm7J1DCdxVvd2MQaXRsmsMPtiTzZW-Di-tjt0y1N5lxAI")
                .remember(true)
                .build();
        token = given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .contentType("application/json;")
                .body(user)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().as(LoginData.class).getToken();
        return token;
    }

    @Step("Открыть минимальный контент")
    public Login openMinimalContent(){
        open("/_nuxt/img/image.2c2c034.jpg");
        return this;
    }

    @Step("Вставить куки в браузер")
    public void setCookie(String token){
        cookie = new Cookie("auth._token.local", token);
        getWebDriver().manage().addCookie(cookie);
    }
//
//    @Step("Установить куку в браузере")
//    public void setCookie(String token) {
//        open("/_nuxt/img/image.2c2c034.jpg");
//        getWebDriver().manage().addCookie(new Cookie("auth._token.local", token));
//    }


    @Step("Получить id авторизованного юзера")
    public int getUserId(String token) {
        Map<String, Integer> user = given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/user")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getMap("user");
        int user_id = user.get("id");
        return user_id;
    }
}
