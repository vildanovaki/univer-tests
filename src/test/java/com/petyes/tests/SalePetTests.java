package com.petyes.tests;

import com.petyes.api.Login;
import com.petyes.api.Pet;
import com.petyes.domain.DataBuilder;
import com.petyes.pages.BasePage;
import com.petyes.pages.PetPage;
import com.petyes.pages.components.CalendarComponent;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Feature("Sale")
public class SalePetTests extends TestBase {

    @BeforeAll
    static void setup() {
        DataBuilder dataBuilder = new DataBuilder();

        dataBuilder.setBreederToken();
        dataBuilder.setCustomerToken();
        dataBuilder.setPetId();
    }

    @Test
    @Tag("salePetTests")
    @DisplayName("Проверить неактивный радиобатон - Продать сейчас")
    void saleExistingPetTest() {
        Pet pet = new Pet();
        BasePage basePage = new BasePage();
        Login login = new Login();
        CalendarComponent calendarComponent = new CalendarComponent();

        Date birth = calendarComponent.getOtherDate(-20);
        int pet_id = pet.createPetByAPI(false, "autoTestSaleCat", birth, 0, 1);

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/for-sale/create/" + pet_id)
                .checkDisableRadio("Продажа сейчас");

        pet.deletePetByAPI(pet_id);
    }

    @Test
    @Tag("salePetTests")
    @DisplayName("Отдать даром нового питомца без породы")
    void freeSaleExistingPetTest() {

        BasePage basePage = new BasePage();
        Login login = new Login();
        CalendarComponent calendarComponent = new CalendarComponent();
        PetPage petPage = new PetPage();

        Date dateBirth = calendarComponent.getOtherDate(-20);
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        String birth = formater.format(dateBirth);

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/sale-pets-list")
                .clickOnButton("Новое животное");
        petPage
                .selectPetTypeSaleNewPet("cat")
                .selectBreed("Без породы");
        basePage
                .enterValueInInput("nickname", "autoTestNewFreeSaleCat");
        calendarComponent
                .enterDate("birth", birth);
        basePage
                .clickOnButton("Далее")
                .clickOnButton("Далее")
                .clickOnButton("Далее")
                .clickOnButton("Отдам даром")
                .clickOnButton("Завершить")
                .clickOnAdvertisement()
                .checkBlockDisplay("Кошка");
    }

    @Test
    @Tag("salePetTests")
    @DisplayName("Редактирование объявление (комментарий)")
    void editPetSaleTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        CalendarComponent calendarComponent = new CalendarComponent();
        PetPage petPage = new PetPage();

        Date dateBirth = calendarComponent.getOtherDate(-20);
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        String birth = formater.format(dateBirth);

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/sale-pets-list")
                .clickOnButton("Новое животное");
        petPage
                .selectPetTypeSaleNewPet("cat")
                .selectBreed("Без породы");
        basePage
                .enterValueInInput("nickname", "autoTestNewFreeSaleCat");
        calendarComponent
                .enterDate("birth", birth);
        basePage
                .clickOnButton("Далее")
                .clickOnButton("Далее")
                .clickOnButton("Далее")
                .clickOnButton("Отдам даром")
                .clickOnButton("Завершить")
                .clickOnAdvertisement()
                .clickOnButton("Редактировать")
                .enterValueInSingleTextarea("comment")
                .clickOnButton("Сохранить")
                .checkGreenMessage()
                .checkBlockDisplay("comment");
    }

    @Test
    @Tag("salePetTests")
    @DisplayName("Снятие объявления с продажи")
    void cancelPetSaleTest() {
        BasePage basePage = new BasePage();
        Login login = new Login();
        CalendarComponent calendarComponent = new CalendarComponent();
        PetPage petPage = new PetPage();

        Date dateBirth = calendarComponent.getOtherDate(-20);
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        String birth = formater.format(dateBirth);

        login
                .openMinimalContent()
                .setCookie(DataBuilder.breederToken);
        basePage
                .openPage("/sale-pets-list")
                .clickOnButton("Новое животное");
        petPage
                .selectPetTypeSaleNewPet("cat")
                .selectBreed("Без породы");
        basePage
                .enterValueInInput("nickname", "autoTestNewFreeSaleCat");
        calendarComponent
                .enterDate("birth", birth);
        basePage
                .clickOnButton("Далее")
                .clickOnButton("Далее")
                .clickOnButton("Далее")
                .clickOnButton("Отдам даром")
                .clickOnButton("Завершить")
                .clickOnAdvertisement()
                .clickOnButton("Снять с продажи")
                .chooseRadio("Сделка состоялась в другом месте")
                .clickOnButton("Продолжить")
                .checkGreenMessage();
    }
}