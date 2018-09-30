package unitTests.inClass;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class SomeClassTest {

    private static Logger logger = Logger.getLogger(SomeClassTest.class);
    private SomeClass someClass;

    @BeforeEach
    void setUp() {
        logger.info("HELLOOOO");
        someClass = new SomeClass();


    }

    @AfterEach
    void tearDown() {
        logger.info("afteach");
    }

    @Test
    void getSumm() {
        int res = someClass.getSumm(1, 2);
        assertEquals(3, res);

    }

    @Test
    void doSome() {

        assertThrows(IOException.class, () -> someClass.doSome(5));
    }
}