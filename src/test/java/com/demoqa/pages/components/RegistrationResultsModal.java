package com.demoqa.pages.components;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {
    public void virifyModalAppears() {
        $(".modal-dialog").shouldHave(appear);
        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
    }

    public void virifyResult(String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
    }
}
