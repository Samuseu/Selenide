package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void practiceForm() {
        String userName = "Vlad";
        String userLastName = "Samuseu";
        String userEmail = "qa@gmail.com";

        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        executeJavaScript("$('#fixedban').remove()"); //удаление рекламы с помощью вызова джава скрипта.(в девтулах)
        executeJavaScript("$('footer').remove()"); // удаление рекламы

        $("#firstName").setValue(userName);
        $("#lastName").setValue(userLastName);
        $("#userEmail").setValue(userEmail);

        //Radio button
//        $("label[for=gender-radio-1]").click();// хороший вариант можно просто [for=gender-radio-1]
//        $("#gender-radio-1").parent().click(); //good
        $("#genterWrapper").$(byText("Male")).click();
//        $(byText("Male")).click(); // not very good ищет первый элемент

        //Number
        $("#userNumber").setValue("3733333333");

        //Data calendar
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
//        $(".react-datepicker__month-select").selectOptionByValue("5");
        $(".react-datepicker__year-select").selectOption("2009");
        $(".react-datepicker__day--030:not(react-datepicker__day--outside-month)").click();

        //checkbox
        $("#subjectsInput").setValue("Maths").pressEnter();

        //Radio button
        $("#hobbiesWrapper").$(byText("Sports")).click();

        //Picture
//        $("").uploadFile(new File("src/test/resources/img/1.png"));
        $("#uploadPicture").uploadFromClasspath("img/1.png");// или так сокращение говорится что файл уже лежит в папке ресурс

        $("#currentAddress").setValue("Current Address");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        $("#submit").click();

        $(".modal-dialog").shouldHave(appear);
        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(userName), text(userLastName), text(userEmail), text("3733333333"));
//        sleep(2000);
    }
}
