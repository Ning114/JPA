package Persistence;

import model.Problem;

import static org.junit.jupiter.api.Assertions.assertEquals;

//CITATION: based off of JsonTest class in JsonSerializationDemo.
public class JsonTest {


    protected void checkProblem(String japaneseProblem, String englishProblem, Problem problem) {
        assertEquals(japaneseProblem, problem.japaneseProblem);
        assertEquals(englishProblem, problem.englishProblem);
    }

}
