package Test;

import static org.junit.jupiter.api.Assertions.*;


import model.Problem;
import model.ProblemSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class ProblemSetTest {

    ProblemSet ps1;
    ProblemSet ps2;
    ProblemSet ps3;

    Problem p1;
    Problem p2;
    Problem p3;


    @BeforeEach
    public void setup() {

        ps1 = new ProblemSet("english");
        ps2 = new ProblemSet("japanese");
        ps3 = new ProblemSet("english");
        p1 = new Problem("ま","ma");
        p2 = new Problem("み","mi");
        p3 = new Problem("む","mu");

    }


    @Test
    public void getScoreThisSetTotalTest() {

        assertEquals(0, ps1.getScoreThisSetTotal());
        ps1.problemSet.add(p1);
        assertEquals(1, ps1.getScoreThisSetTotal());
        ps1.problemSet.add(p2);
        assertEquals(2, ps1.getScoreThisSetTotal());

    }

    @Test
    public void generateAvailableVocabTest() {

        ps1.vocabFamilySet = false;
        assertEquals(0, ps1.generateAvailableVocab().size());

        ps1.vocabFamilySet = true;
        assertEquals(2, ps1.generateAvailableVocab().size());

    }

    @Test
    public void initializeVocabFamilyTest() {
        ps1.initializeVocabFamily();
        assertEquals(2, ps1.vocabFamily.size());
    }

    @Test
    public void generateAvailableHiraganaTest() {
        //This methods tests generateAvailableHiragana1 , 2 and 3

        //test case where it generates empty list because all rows are disabled
        ps1.hiraganaSet15 = false;
        ps1.hiraganaSet16 = false;
        assertEquals(0, ps1.generateAvailableHiragana3().size());
        //test case where it generates some rows, but not all
        ps1.hiraganaSet14 = false;
        assertEquals(21, ps1.generateAvailableHiragana2().size());
        //test case where it genereates all rows
        assertEquals(35, ps1.generateAvailableHiragana1().size());

    }


    //THESE TESTS ARE GOING TO FAIL WHEN I IMPLEMENT MORE VOCAB AND KATAKANA. I NEED TO RE-WRITE THEM WHEN THAT HAPPENS.
    @Test
    public void generateAvailableProblemsTest() {
        //case where nothing is added
        ps1.hiraganaSet1 = false;
        ps1.hiraganaSet2 = false;
        ps1.hiraganaSet3 = false;
        ps1.hiraganaSet4 = false;
        ps1.hiraganaSet5 = false;
        ps1.hiraganaSet6 = false;
        ps1.hiraganaSet7 = false;
        ps1.hiraganaSet8 = false;
        ps1.hiraganaSet9 = false;
        ps1.hiraganaSet10 = false;
        ps1.hiraganaSet11 = false;
        ps1.hiraganaSet12 = false;
        ps1.hiraganaSet13 = false;
        ps1.hiraganaSet14 = false;
        ps1.hiraganaSet15 = false;
        ps1.hiraganaSet16 = false;
        ps1.vocabFamilySet = false;
        ps1.vocabGreetingsSet = false;
        ps1.vocabPlacesSet = false;
        ps1.vocabThingsSet = false;
        ps1.vocabMajorsSet = false;

        ps1.generateAvailableProblems();
        assertEquals(0, ps1.availableProblems.size());

        //case where some but not all sets are added to available problems
        ps2.vocabFamilySet = false;
        ps2.generateAvailableProblems();
        assertEquals(71, ps2.availableProblems.size());

        //case where all subjects are added to available problems
        ps3.generateAvailableProblems();
        assertEquals(73, ps3.availableProblems.size());

    }

    @Test
    public void pickRandomProblemTest() {

        ps1.availableProblems.add(p1);
        ps1.availableProblems.add(p2);
        ps1.availableProblems.add(p3);

        for (int i = 0; i < 100; i++) {

            Random randomNumber = new Random();
            Problem result = ps1.availableProblems.get(randomNumber.nextInt(ps1.availableProblems.size()));
            assertTrue(ps1.availableProblems.contains(result));

        }

    }

    @Test
    public void generateProblemSetTest() {
        //case where the minimum number of problems is added (this method requires at least ONE SUBJECT to be enabled,
        //so we cannot have a case where nothing is added!)
        //(vocabFamilySet is enabled)
        ps1.hiraganaSet1 = false;
        ps1.hiraganaSet2 = false;
        ps1.hiraganaSet3 = false;
        ps1.hiraganaSet4 = false;
        ps1.hiraganaSet5 = false;
        ps1.hiraganaSet6 = false;
        ps1.hiraganaSet7 = false;
        ps1.hiraganaSet8 = false;
        ps1.hiraganaSet9 = false;
        ps1.hiraganaSet10 = false;
        ps1.hiraganaSet11 = false;
        ps1.hiraganaSet12 = false;
        ps1.hiraganaSet13 = false;
        ps1.hiraganaSet14 = false;
        ps1.hiraganaSet15 = false;
        ps1.hiraganaSet16 = false;
        ps1.vocabGreetingsSet = false;
        ps1.vocabPlacesSet = false;
        ps1.vocabThingsSet = false;
        ps1.vocabMajorsSet = false;

        //case where problemSet < availableProblems
        ps1.generateProblemSet(1);
        assertEquals(1, ps1.problemSet.size());

        //reset ps1.problemSet
        ps1.problemSet.remove(0);

        //case where problemSet = availableProblems
        ps1.generateProblemSet(2);
        assertEquals(2, ps1.problemSet.size());



        //case where all subjects are enabled
        ps2.generateProblemSet(5);
        assertEquals(5, ps2.problemSet.size());

        ps2.problemSet.removeAll(ps2.problemSet);

        //case where problemSet = availableProblems
        ps2.generateProblemSet(73);
        assertEquals(73, ps2.problemSet.size());


    }

    @Test
    public void getDisplayTypeTest() {

        assertEquals("english", ps1.getDisplayType());
        assertEquals("japanese", ps2.getDisplayType());

    }


}
