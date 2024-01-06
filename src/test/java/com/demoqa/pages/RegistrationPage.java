package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponents;
import com.demoqa.pages.components.RegistrationResultsModal;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    CalendarComponents calendarComponents = new CalendarComponents();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private final String TITLE_TEXT = "Student Registration Form";
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            dateOFBithDay = $("#dateOfBirthInput"),
            userSubjectInput = $("#subjectsInput"),
            userGender = $("#genterWrapper"),
            userHobbies = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        userGender.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setBirthDay(String day, String month, String year) {
        dateOFBithDay.click();
        calendarComponents.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubject(String values) {
        userSubjectInput.setValue(values);


        return this;
    }

    public RegistrationPage setHobbies(String value) {
        userHobbies.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploatPicture(String path) {
        uploadPicture.uploadFromClasspath(path);

        return this;
    }

    public RegistrationPage setCurrentAddres(String value) {
        $("#currentAddress").setValue(value);

        return this;
    }

    public RegistrationPage setState(String value) {
        $("#state").click();
        $("#stateCity-wrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity(String value) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(value)).click();
        $("#submit").click();

        return this;
    }

    public RegistrationPage virifyResultsModalAppears() {
        registrationResultsModal.virifyModalAppears();

        return this;
    }

    public RegistrationPage virifyResult(String key, String value) {
        registrationResultsModal.virifyResult(key,value);

        return this;
    }

}
