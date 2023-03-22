package com.petyes.tests;

import com.petyes.api.Login;
import com.petyes.api.Pet;
import com.petyes.domain.DataBuilder;
import com.petyes.pages.BasePage;
import com.petyes.pages.MyPetsPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

@Feature("My pets")
public class MyPetsTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
        dataBuilder.setPetId();
    }

    @Test
    @Tag("myPetsTests")
    @DisplayName("Открыть страницу питомца")
    void openPetTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        MyPetsPage myPetsPage = new MyPetsPage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/mypets");
        myPetsPage
                .clickOnPet(DataBuilder.petName);
        basePage
                .checkBlockDisplay(DataBuilder.petName);
    }

    @Test
    @Tag("myPetsTests")
    @DisplayName("Открыть родословную питомца")
    void openGenTreeTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        MyPetsPage myPetsPage = new MyPetsPage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/mypets");
        myPetsPage
                .clickOnGenTree(DataBuilder.pet_id)
                .checkGenTree(DataBuilder.petName);
    }

    @Test
    @Tag("myPetsTests")
    @DisplayName("Открыть редактирование питомца")
    void openEditPetTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        MyPetsPage myPetsPage = new MyPetsPage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/mypets");
        myPetsPage
                .clickOnEdit(DataBuilder.pet_id);
        basePage
                .checkHeader(1, "Редактирование карточки питомца");
    }

    @Test
    @Tag("myPetsTests")
    @DisplayName("Открыть продажу питомца (отдать даром)")
    void openFreeSalePetTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        MyPetsPage myPetsPage = new MyPetsPage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/mypets");
        myPetsPage
                .clickOnFreeSale(DataBuilder.pet_id)
                .checkSale("Отдам бесплатно в хорошие руки");
    }

    // Дефект - не удается открыть продажу питомца, так как сломан запрос на создание питомца для продажи
    // (ошибка не добавления спецификации)
    @Test
    @Tag("myPetsTests")
    @DisplayName("Открыть продажу питомца")
    void openSalePetTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        MyPetsPage myPetsPage = new MyPetsPage();

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/mypets");
        myPetsPage
                .clickOnSale(DataBuilder.pet_id)
                .checkSale("Продажа сейчас");
    }
}