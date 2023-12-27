package junit;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class FirstJUnitTest {

    @BeforeAll // запускается 1 раз перед всеми тестами нужен пример задать браузер разрешение и тд
    static void beforeAll() {
        Configuration.browser = "opera";
    }

    @AfterAll //запускается 1 раз после всех тестов, нужен, чтобы почистить и тд
    static void afterAll() {
    }

    @BeforeEach
        // перед каждым тестам
    void beforeEach() {
    }

    @AfterEach
        //после каждого теста
    void afterEach() {
    }

    @Test
    void firstTest() {
        Assertions.assertTrue(3 > 2);
    }
}
