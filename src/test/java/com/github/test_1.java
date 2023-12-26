package com.github;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class test_1 {
    @Test
    void GitHubSolution() {
        open("https://github.com");
        $x("//li[contains(@class,'HeaderMenu')][2]")
                .$(byText("Solutions")).hover();
        $x("//ul[@aria-labelledby]//a[@href='/enterprise'][1]").click();
        $x("//p[contains(text(),'To build, scale, and deliver secure software.')]")
                .shouldHave(text("To build, scale, and deliver secure software."));
//        sleep(3000);

    }
}
