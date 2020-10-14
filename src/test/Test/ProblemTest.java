package Test;

import static org.junit.jupiter.api.Assertions.*;


import model.Problem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProblemTest {

    Problem p1;
    Problem p2;

    @BeforeEach
    public void initialize() {

        p1 = new Problem("よ", "yo");
        p2 = new Problem("ありがとう", "thank you");

    }

    //my getScore method is already extensively tested in my other two test methods.

    @Test
    public void isCorrectEnglishTest() {

        assertEquals(0, p1.getScore());
        assertTrue(p1.isCorrectEnglish("yo"));
        assertEquals(1, p1.getScore());
        assertFalse(p1.isCorrectEnglish("wrong"));
        assertEquals(0, p1.getScore());

        assertEquals(0, p2.getScore());
        assertTrue(p2.isCorrectEnglish("thank you"));
        assertEquals(1, p2.getScore());
        assertFalse(p2.isCorrectEnglish("wrong answer dummy"));
        assertEquals(0, p2.getScore());

    }

    @Test
    public void isCorrectJapaneseTest() {

        assertEquals(0, p1.getScore());
        assertTrue(p1.isCorrectJapanese("よ"));
        assertEquals(1, p1.getScore());
        assertFalse(p1.isCorrectJapanese("じ"));
        assertEquals(0, p1.getScore());

        assertEquals(0, p2.getScore());
        assertTrue(p2.isCorrectJapanese("ありがとう"));
        assertEquals(1, p2.getScore());
        assertFalse(p2.isCorrectJapanese("コンピューター"));
        assertEquals(0, p2.getScore());

    }
}
