package junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirstJUnitTest {

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
