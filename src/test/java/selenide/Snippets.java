package selenide;


import  com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
// selenide это библиотека java
public class Snippets {

    @Test
    void browser_command_examples() {
        open("https://www.google.com/");
        open("/customer/orders");
        open("/", AuthenticationType.BASIC, new BasicAuthCredentials("", "user", "password"));// когда есть пуш окно для авторезации

        Selenide.back();//стрелка назад при тестирование визардов, когда есть процеес при оформление подписок в несколько шагов и тд проверка кнопок.
        Selenide.refresh();//обновление страницы одним тестом мы лагинмися а вторым сново начать тестирование с нового пользователся это обновиться тоесть сделать рефреш.это обновление страницы

        Selenide.clearBrowserCookies(); //
        Selenide.clearBrowserLocalStorage(); //
        executeJavaScript("sessionStorage.clear();"); //session storage

        //это попапы которые нельзя открыть через дом.
        Selenide.confirm(); // это поп ап окно где есть только 1 кнопка ок
        Selenide.dismiss(); //это поп ап окно где есть только 2 кнопки ок и отмена

        Selenide.closeWindow(); //закрыть текущие окно
        Selenide.closeWebDriver(); //закрыть браузер

        // фреймы это страничка в страничке
        Selenide.switchTo().frame("new"); //переход в фрейм
        Selenide.switchTo().defaultContent(); //выйти из фрейма return

        Selenide.switchTo().window("The Interner"); // переходить между окнами в браузере

        var cookie = new Cookie("foo", "bar"); //создание куков позже нужно обновить страницу.
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }

    void selectors_examples() {
        $("div").click();
        element("div").click(); //клик вместо $ например для котлина если у нас сборка.

        $("div", 2).click(); //мы берем 3 див; the third div

        // Xpath ищет во все стороны
        $x("//h1/div").click(); //поиск по пути селектор
        $(byXpath("//h1/div")).click(); //

        $(byText("full text")).click(); //искать по тексту что внутри тега
        $(withText("ull tex")); // частично текст искать

        $(byTagAndText("div", "full text")); // пишем тег и текс какой нужен
        $(withTagAndText("div", "ull text")); //

        $("").parent(); //это вернуться к  родителю
        $("").sibling(1); //ищет братьев и сестер верх
        $("").preceding(1); //ищет братьев и сестер вниз
        $("").closest("div"); //выше див
        $("").ancestor("div"); //ниже див
        $("div:last-child"); // последний ребенок

        $("div").$("h1").find(byText("abc")).click();// мы ищем див,ищем внутри h1,ищем внутри текс "abc",потом кликаем
        //тоже самое внизу просто find заменили на доллар ибо поиск может начинатьсяс $ а с find нельзя вот и всегда в начала стоит $, а так find == $
        $("div").$("h1").$(byText("abc")).click();// мы ищем див,ищем внутри h1,ищем внутри текс "abc",потом кликаем
        //very optional
        $(byAttribute("abc", "x")).click();// искать по атрибуту
        $("[abc=x]").click();

        $(byId("myText")).click();// искать по айди
        $("#myText").click();

        $(byClassName(".red")).click();// искать по имени класса
        $(".red").click();
    } // селекторы не ищут пока мы не сделаем действие ну или actions например клик или проверку и  тд

    void actions_examples() {
        $("").click();
        $("").doubleClick();
        $("").contextClick(); //правый клик

        $("").hover(); //подвести мышку и не кликать

        $("").setValue("text"); //оно сначала удаляет все что есть в поле а после вводит текст
        $("").append("text"); //а аппенд добавляет в конец
        $("").clear(); //удаляет значение
        $("").setValue(""); // clear

        $("div").sendKeys("c"); // hotkey c on element эмуляция кнопкой, например на сайте джира если нажать просто кнопку "с" то появится меню с созданием новой заметки.вот и эмулируем тут.
        actions().sendKeys("c").perform(); //hotkey c on whole application // просто нажать на кнопку С
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));// еще вариант как выше.

        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();


        // complex actions with keybord and mouse, example
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();
// подвинуть мышку к элементу, нажать клик и держать и потом потянуть мышку в 300 право 200 вверх и релиз это отпустить.

        // old html actions don't work with many modern frameworks
        $("").selectOption("dropdown_option");// выбрать дропдавн меню
        $("").selectRadio("radio_options");

    }

    void assertions_examples() {
        // стандартный таймаут 4с
        // они все одинаковый отличаются только название по англ, чтобы лучше читался тест
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);


        //longer timeouts можно увеличить время или уменьшить именно этого параметра именно параметр Duration.ofSeconds(30) можно и в других проверках проверять
        $("").shouldBe(visible, Duration.ofSeconds(30));

    } // проверки

    void conditions_examples() {
        $("").shouldBe(visible); //видимый
        $("").shouldBe(hidden); //не видимый

        $("").shouldHave(text("abc")); // игнорирует все case и тд проверяет по вхождению
        $("").shouldHave(exactText("abc"));  // ищет точно такой текст как в значении
        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$")); // сложные проверки редко пользуются

        $("").shouldHave(cssClass("red")); // проверка класса
        $("").shouldHave(cssClass("red"),cssClass("black")); // проверка несколько классов

        $("").shouldHave(cssValue("font-size", "12")); //проверка по css выбираем параметр и значение например цвет кнопки проверить чтобы был красный.

        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);

        $("").shouldHave(attribute("disabled")); // проверка атрибута
        $("").shouldHave(attribute("name", "example")); //проверка атрибута и значения
        $("").shouldHave(attributeMatching("name", "[0-9]abc$")); // проверка более точно

        $("").shouldBe(checked); // for checkboxes проверка чекбокса

        // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
        $("").should(exist);

        // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
        // проверки на кликабильность
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);
    }

    void collections_examples() {

        $$("div"); // does nothing!

        $$x("//div"); // by XPath

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1)); //фильтрация, после проверка,
        $$("div").excludeWith(text("123")).shouldHave(size(1)); //

        $$("div").first().click(); // первый элемент
        elements("div").first().click();
        // $("div").click();
        $$("div").last().click(); //последний эелемент
        $$("div").get(1).click(); // the second! (start with 0)
        $("div", 1).click(); // same as previous
        $$("div").findBy(text("123")).click(); //  finds first ищет первый элемент

        // assertions
        $$("").shouldHave(size(0)); //пустая коллекция поиск
        $$("").shouldBe(CollectionCondition.empty); // the same

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma")); //точная проверка текста

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

        $$("").shouldHave(itemWithText("Gamma")); // only one text поиск в коллекции элемент

        $$("").shouldHave(sizeGreaterThan(0));  //>0
        $$("").shouldHave(sizeGreaterThanOrEqual(1)); //>=1
        $$("").shouldHave(sizeLessThan(3)); //>3
        $$("").shouldHave(sizeLessThanOrEqual(2)); //<=2


    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file); // загрузить файл
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit!
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')");// запуск js
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);// считываешь значение 12 и передаешь в js
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);// вычисление js

    }
}
