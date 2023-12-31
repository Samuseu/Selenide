package com.demoqa.tests;

import org.junit.jupiter.api.Test;

public class RegistrationTests extends TestBase {


    @Test
    void practiceForm() {
        String userName = "Vlad";
        String userLastName = "Samuseu";
        String userEmail = "qa@gmail.com";
        String userNumber = "3733333333";
        String userSubject = "Maths";
        String userCurrentAdress = "city Polatsk";
        String userState = "NCR";
        String userCity = "Delhi";
        registrationPage.openPage()
                .setFirstName(userName)
                .setLastName(userLastName)
                .setUserEmail(userEmail)
                .setGender("Male")
                .setUserNumber(userNumber)
                .setBirthDay("30", "June", "2008")
                .setSubject(userSubject)
                .setHobbies("Sports")
                .uploatPicture("img/1.png")
                .setCurrentAddres(userCurrentAdress)
                .setState(userState)
                .setCity(userCity);

        registrationPage.virifyResultsModalAppears()
                .virifyResult("Student Name", userName)
                .virifyResult("Student Email", userEmail)
                .virifyResult("Gender", "Male")
                .virifyResult("Mobile", userNumber)
                .virifyResult("Date of Birth", "30 June,2008")
                .virifyResult("Subjects", userSubject)
                .virifyResult("Hobbies", "Sports")
                .virifyResult("Address", userCurrentAdress)
                .virifyResult("State and City", userState + " " + userCity);

    }
}
