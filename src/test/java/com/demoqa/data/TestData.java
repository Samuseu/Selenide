package com.demoqa.data;
import com.demoqa.tests.TestBase;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestData {
    Faker faker = new Faker(new Locale("ru"));

    public String firstName = faker.name().firstName(); // Emory
    public String lastName = faker.name().lastName(); // Barton

    Faker englishFaker = new Faker(new Locale("en"));
    public String emailAdress = englishFaker.internet().emailAddress();

    public String userAdress = faker.address().fullAddress();

    Date birthDate = faker.date().birthday();
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);

    public String userBirthDay = dayFormat.format(birthDate);
    public String userBirthMonth = monthFormat.format(birthDate);
    public String userBirthYear = yearFormat.format(birthDate);

    public String userSubject = faker.university().name();
    public String number = "375" + englishFaker.number().randomNumber(7, true);


}
