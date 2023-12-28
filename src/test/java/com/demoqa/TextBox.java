package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class TextBox {
    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = false;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }
    @Test
    void testBox(){
        open("/text-box");

        $("#userName").setValue("Samuseu Vlad");
        $("#userEmail").setValue("qa@gmail.com");
        $("#currentAddress").setValue("currentAddress");
        $("#permanentAddress").setValue("permanentAddress");
        $("#submit").click();


        $("#output").shouldBe(visible);
        $("#output #name").shouldHave(text("Name:"));
        $("#output #email").shouldHave(text("Email"));
        $("#output #currentAddress").shouldHave(text("Current Address"));
        $("#output #permanentAddress").shouldHave(text("Permananet Address"));
//        sleep(2000);
    }
}
