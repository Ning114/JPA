package Persistence;

import model.ProblemSet;
import model.Problem;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonReader extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/bruhMoment.json");
        try {
            ProblemSet problemSet = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //test passed
            return;
        }
        fail("Exception should have been caught");
    }

    @Test
    void testReaderEmptyProblemSet() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyProblemSet.json");
        try {
            ProblemSet problemSet = reader.read();
            assertEquals("english", problemSet.getDisplayType());
            assertEquals(0 , problemSet.problemSet.size());
        } catch (IOException e) {
            fail("File didn't read properly");
        }
    }

    @Test
    void testReader() {
        JsonReader reader = new JsonReader("./data/testReader.json");
        try {
            ProblemSet problemSet = reader.read();
            assertEquals("japanese", problemSet.getDisplayType());
            assertEquals("あ", problemSet.problemSet.get(0).japaneseProblem);
            assertEquals("a", problemSet.problemSet.get(0).englishProblem);
            assertEquals("おかあさん", problemSet.problemSet.get(1).japaneseProblem);
            assertEquals("mother", problemSet.problemSet.get(1).englishProblem);
            assertEquals("ぽ", problemSet.problemSet.get(2).japaneseProblem);
            assertEquals("po", problemSet.problemSet.get(2).englishProblem);
            assertEquals(3, problemSet.problemSet.size());

        } catch (IOException e) {
            fail("File didn't read properly");
        }
    }
}
