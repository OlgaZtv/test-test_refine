package io.olgaztv;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RefineApp {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1900x1000";

    }

    @Test
    @Owner("OlgaZtv")
    @DisplayName("Загрузка фото на главной странице")
    public void uploadPhotoToStart() {

        open("https://refineapp.me/");
        File image = new File("src/test/resources/lama.jpg");
        $("input[type='file']").uploadFile(image);
        $(".video-wrrapper ", 1).click();
        $(".button ").click();
        $(".email-form__tooltip").shouldBe(Condition.visible, Duration.ofMillis(25_000));
        $(".email-form__input").shouldBe(Condition.visible);
    }

    @Test
    @Owner("OlgaZtv")
    @DisplayName("Логин с невалидным email")
    public void loginToMyAccountWithNotValidEmail() {
        open("https://refineapp.me/");
        $(byText("My Account")).click();
        $("#email").sendKeys("test@test.ru");
        $(".button ").click();
        $(".account__toast__description").shouldHave(text("We can't find an account with this email address"));
    }

}
