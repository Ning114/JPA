package Persistence;

import model.Problem;
import model.ProblemSet;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//CITATION: class is based off JsonWriterTest in JsonSerializationDemo
public class TestJsonWriter extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ProblemSet problemSet = new ProblemSet("japanese");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:goodnightgirlI'llseeyoutomorrow.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //test passed, nice!
            return;
        }
        fail("Exception should have been caught");
    }

    @Test
    void testWriterEmptyProblemSet() {
        try {
            ProblemSet problemSet = new ProblemSet("japanese");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyProblemSet.json");
            writer.open();
            writer.write(problemSet);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyProblemSet.json");
            problemSet = reader.read();
            assertEquals("japanese", problemSet.getDisplayType());
            assertEquals(0, problemSet.problemSet.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriter() {
        try {
            ProblemSet problemSet = new ProblemSet("english");
            problemSet.problemSet.add(new Problem("の", "no"));
            problemSet.problemSet.add(new Problem("や", "ya"));
            problemSet.problemSet.add(new Problem("おとうさん", "father"));
            JsonWriter writer = new JsonWriter("./data/testWriter.json");
            writer.open();
            writer.write(problemSet);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriter.json");
            problemSet = reader.read();

            assertEquals("english", problemSet.getDisplayType());
            assertEquals("の", problemSet.problemSet.get(0).japaneseProblem);
            assertEquals("no", problemSet.problemSet.get(0).englishProblem);
            assertEquals("や", problemSet.problemSet.get(1).japaneseProblem);
            assertEquals("ya", problemSet.problemSet.get(1).englishProblem);
            assertEquals("おとうさん", problemSet.problemSet.get(2).japaneseProblem);
            assertEquals("father", problemSet.problemSet.get(2).englishProblem);
            assertEquals(3, problemSet.problemSet.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
