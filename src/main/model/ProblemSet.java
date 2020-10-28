package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.Random;


//CLASS LEVEL COMMENT:
//ProblemSet class represents an ArrayList of Problems. Also contains the database for all problems i.e the
//pre-determined problems are initialized here.
//ProblemSets are generated based on user input and parts of the database.
//ProblemSet also keeps track of:
//The total score attained on a problem set
//The maximum score attainable on a problem set
public class ProblemSet implements Writeable {

    public String displayType;
    private int scoreThisSet;
    private int scoreThisSetTotal;
    public ArrayList<Problem> problemSet;
    public ArrayList<Problem> availableProblems;

    public Boolean hiraganaSet1;
    public Boolean hiraganaSet2;
    public Boolean hiraganaSet3;
    public Boolean hiraganaSet4;
    public Boolean hiraganaSet5;
    public Boolean hiraganaSet6;
    public Boolean hiraganaSet7;
    public Boolean hiraganaSet8;
    public Boolean hiraganaSet9;
    public Boolean hiraganaSet10;
    public Boolean hiraganaSet11;
    public Boolean hiraganaSet12;
    public Boolean hiraganaSet13;
    public Boolean hiraganaSet14;
    public Boolean hiraganaSet15;
    public Boolean hiraganaSet16;


    private Boolean katakanaSet1;
    private Boolean katakanaSet2;
    private Boolean katakanaSet3;
    private Boolean katakanaSet4;
    private Boolean katakanaSet5;
    private Boolean katakanaSet6;
    private Boolean katakanaSet7;
    private Boolean katakanaSet8;
    private Boolean katakanaSet9;
    private Boolean katakanaSet10;
    private Boolean katakanaSet11;
    private Boolean katakanaSet12;
    private Boolean katakanaSet13;
    private Boolean katakanaSet14;
    private Boolean katakanaSet15;
    private Boolean katakanaSet16;

    public Boolean timeSet;

    public Boolean vocabFamilySet;
    public Boolean vocabMajorsSet;
    public Boolean vocabGreetingsSet;
    public Boolean vocabPlacesSet;
    public Boolean vocabThingsSet;


    private ArrayList<Problem> hiraganaRow1;
    private ArrayList<Problem> hiraganaRow2;
    private ArrayList<Problem> hiraganaRow3;
    private ArrayList<Problem> hiraganaRow4;
    private ArrayList<Problem> hiraganaRow5;
    private ArrayList<Problem> hiraganaRow6;
    private ArrayList<Problem> hiraganaRow7;
    private ArrayList<Problem> hiraganaRow8;
    private ArrayList<Problem> hiraganaRow9;
    private ArrayList<Problem> hiraganaRow10;
    private ArrayList<Problem> hiraganaRow11;
    private ArrayList<Problem> hiraganaRow12;
    private ArrayList<Problem> hiraganaRow13;
    private ArrayList<Problem> hiraganaRow14;
    private ArrayList<Problem> hiraganaRow15;
    private ArrayList<Problem> hiraganaRow16;

    private ArrayList<Problem> katakanaRow1;
    private ArrayList<Problem> katakanaRow2;
    private ArrayList<Problem> katakanaRow3;
    private ArrayList<Problem> katakanaRow4;
    private ArrayList<Problem> katakanaRow5;
    private ArrayList<Problem> katakanaRow6;
    private ArrayList<Problem> katakanaRow7;
    private ArrayList<Problem> katakanaRow8;
    private ArrayList<Problem> katakanaRow9;
    private ArrayList<Problem> katakanaRow10;
    private ArrayList<Problem> katakanaRow11;
    private ArrayList<Problem> katakanaRow12;
    private ArrayList<Problem> katakanaRow13;
    private ArrayList<Problem> katakanaRow14;
    private ArrayList<Problem> katakanaRow15;
    private ArrayList<Problem> katakanaRow16;

    public ArrayList<Problem> vocabFamily;
    public ArrayList<Problem> vocabMajors;
    public ArrayList<Problem> vocabGreetings;
    public ArrayList<Problem> vocabPlaces;
    public ArrayList<Problem> vocabThings;


    //Initializes an empty problemSet with the specified displayType.
    //By default, all subjects are enabled and can be changed by the User later.
    //scoreThisSetTotal is determined after a problem set is generated and scoreThisSet is calculated after the user
    //has run through the problem set (still initialized as 0).
    public ProblemSet(String displayType) {

        this.displayType = displayType;
        this.scoreThisSet = 0;

        initializeHiraganaBool();

        initializeKatakanaBool();

        initializeVocabBool();

        this.timeSet = true;

        this.availableProblems = new ArrayList<Problem>();

        initializeHiraganaRowLists();

        initializeVocabSetLists();

    }

    //EFFECTS: initializes all hiraganaRow bool to true
    public void initializeHiraganaBool() {

        this.hiraganaSet1 = true;
        this.hiraganaSet2 = true;
        this.hiraganaSet3 = true;
        this.hiraganaSet4 = true;
        this.hiraganaSet5 = true;
        this.hiraganaSet6 = true;
        this.hiraganaSet7 = true;
        this.hiraganaSet8 = true;
        this.hiraganaSet9 = true;
        this.hiraganaSet10 = true;
        this.hiraganaSet11 = true;
        this.hiraganaSet12 = true;
        this.hiraganaSet13 = true;
        this.hiraganaSet14 = true;
        this.hiraganaSet15 = true;
        this.hiraganaSet16 = true;

    }

    //EFFECTS: initializes all katakanaRow bool to true
    public void initializeKatakanaBool() {

        this.katakanaSet1 = true;
        this.katakanaSet2 = true;
        this.katakanaSet3 = true;
        this.katakanaSet4 = true;
        this.katakanaSet5 = true;
        this.katakanaSet6 = true;
        this.katakanaSet7 = true;
        this.katakanaSet8 = true;
        this.katakanaSet9 = true;
        this.katakanaSet10 = true;
        this.katakanaSet11 = true;
        this.katakanaSet12 = true;
        this.katakanaSet13 = true;
        this.katakanaSet14 = true;
        this.katakanaSet15 = true;
        this.katakanaSet16 = true;

    }

    //EFFECTS: initializes all vocabSet bool to true
    public void initializeVocabBool() {
        this.vocabFamilySet = true;
        this.vocabMajorsSet = true;
        this.vocabGreetingsSet = true;
        this.vocabPlacesSet = true;
        this.vocabThingsSet = true;
    }

    //EFFECTS: initializes all hiraganaRow ArrayLists as empty lists
    public void initializeHiraganaRowLists() {

        this.hiraganaRow1 = new ArrayList<Problem>();
        this.hiraganaRow2 = new ArrayList<Problem>();
        this.hiraganaRow3 = new ArrayList<Problem>();
        this.hiraganaRow4 = new ArrayList<Problem>();
        this.hiraganaRow5 = new ArrayList<Problem>();
        this.hiraganaRow6 = new ArrayList<Problem>();
        this.hiraganaRow7 = new ArrayList<Problem>();
        this.hiraganaRow8 = new ArrayList<Problem>();
        this.hiraganaRow9 = new ArrayList<Problem>();
        this.hiraganaRow10 = new ArrayList<Problem>();
        this.hiraganaRow11 = new ArrayList<Problem>();
        this.hiraganaRow12 = new ArrayList<Problem>();
        this.hiraganaRow13 = new ArrayList<Problem>();
        this.hiraganaRow14 = new ArrayList<Problem>();
        this.hiraganaRow15 = new ArrayList<Problem>();
        this.hiraganaRow16 = new ArrayList<Problem>();

    }

    //EFFECTS: initializes all vocabSet ArrayLists as empty lists
    public void initializeVocabSetLists() {
        this.problemSet = new ArrayList<Problem>();
        this.vocabFamily = new ArrayList<Problem>();
        this.vocabMajors = new ArrayList<Problem>();
        this.vocabGreetings = new ArrayList<Problem>();
        this.vocabPlaces = new ArrayList<Problem>();
        this.vocabThings = new ArrayList<Problem>();
    }


    //EFFECTS: returns this.displayType
    public String getDisplayType() {

        return this.displayType;

    }


    //REQUIRES: at least one Subject set is enabled and size of problem set is <= size of availableProblems
    //MODIFIES: this.problemSet, this.availableProblems
    //EFFECTS: Generates a list of problems based off which subject sets are enabled.
    public ArrayList<Problem> generateProblemSet(int size) {

        generateAvailableProblems();

        for (int i = 0; i < size; i++) {

            Problem randomProblem = pickRandomProblem();
            this.problemSet.add(randomProblem);
            //ensures that duplicate problems will not be added to the list.
            this.availableProblems.remove(randomProblem);

        }

        this.scoreThisSetTotal = getScoreThisSetTotal();
        return this.problemSet;

    }

    //REQUIRES: size of list of available problems is >= 1
    //EFFECTS: Randomly picks a problem out of pool of given problems
    public Problem pickRandomProblem() {

        Random randomNumber = new Random();

        return availableProblems.get(randomNumber.nextInt(availableProblems.size()));
    }


    //EFFECTS: gets the maximum score one can achieve in the current problem set.
    public int getScoreThisSetTotal() {

        return problemSet.size();

    }


    //MODIFIES: this.availableProblems
    //EFFECTS: Generates a list of available problems based on which subject sets are enabled
    public void generateAvailableProblems() {

        //This will be the list of all available problems that can be added to the problem set.

        this.availableProblems.addAll(generateAvailableHiragana1());
        this.availableProblems.addAll(generateAvailableHiragana2());
        this.availableProblems.addAll(generateAvailableHiragana3());
        this.availableProblems.addAll(generateAvailableHiragana4());
        this.availableProblems.addAll(generateAvailableVocab());

    }

    //EFFECTS: adds all vocabulary sets to result if they are enabled by user. Returns result after checking
    //all vocab sets
    public ArrayList<Problem> generateAvailableVocab() {

        ArrayList<Problem> result = new ArrayList<Problem>();

        if (this.vocabFamilySet) {
            initializeVocabFamily();
            result.addAll(vocabFamily);
        }

        return result;

    }


    //EFFECTS: adds all hiragana rows 1-5 to result if they are enabled by user. Returns result after checking
    //all hiragana rows 1-5
    public ArrayList<Problem> generateAvailableHiragana1() {

        ArrayList<Problem> result = new ArrayList<Problem>();

        if (this.hiraganaSet1) {
            initializeHiraganaRow1();
            result.addAll(hiraganaRow1);
        }
        if (this.hiraganaSet2) {
            initializeHiraganaRow2();
            result.addAll(hiraganaRow2);
        }
        if (this.hiraganaSet3) {
            initializeHiraganaRow3();
            result.addAll(hiraganaRow3);
        }
        if (this.hiraganaSet4) {
            initializeHiraganaRow4();
            result.addAll(hiraganaRow4);
        }
        if (this.hiraganaSet5) {
            initializeHiraganaRow5();
            result.addAll(hiraganaRow5);
        }


        return result;

    }

    //EFFECTS: adds all hiragana rows 6-10 to result if they are enabled by user. Returns result after checking
    //all hiragana rows 6-10
    public ArrayList<Problem> generateAvailableHiragana2() {

        ArrayList<Problem> result = new ArrayList<Problem>();

        if (this.hiraganaSet6) {
            initializeHiraganaRow6();
            result.addAll(hiraganaRow6);
        }
        if (this.hiraganaSet7) {
            initializeHiraganaRow7();
            result.addAll(hiraganaRow7);
        }
        if (this.hiraganaSet8) {
            initializeHiraganaRow8();
            result.addAll(hiraganaRow8);
        }
        if (this.hiraganaSet9) {
            initializeHiraganaRow9();
            result.addAll(hiraganaRow9);
        }
        if (this.hiraganaSet10) {
            initializeHiraganaRow10();
            result.addAll(hiraganaRow10);
        }


        return result;

    }

    //EFFECTS: adds all hiragana rows 11-15 to result if they are enabled by user. Returns result after checking
    //all hiragana rows 11-15
    public ArrayList<Problem> generateAvailableHiragana3() {

        ArrayList<Problem> result = new ArrayList<Problem>();

        if (this.hiraganaSet11) {
            initializeHiraganaRow11();
            result.addAll(hiraganaRow11);
        }
        if (this.hiraganaSet12) {
            initializeHiraganaRow12();
            result.addAll(hiraganaRow12);
        }
        if (this.hiraganaSet13) {
            initializeHiraganaRow13();
            result.addAll(hiraganaRow13);
        }
        if (this.hiraganaSet14) {
            initializeHiraganaRow14();
            result.addAll(hiraganaRow14);
        }
        if (this.hiraganaSet15) {
            initializeHiraganaRow15();
            result.addAll(hiraganaRow15);
        }

        return result;

    }

    //EFFECTS: adds hiragana row 16 to result if they are enabled by user. Returns result after checking
    //hiragana row 16
    public ArrayList<Problem> generateAvailableHiragana4() {

        ArrayList<Problem> result = new ArrayList<Problem>();

        if (this.hiraganaSet16) {
            initializeHiraganaRow16();
            result.addAll(hiraganaRow16);
        }

        return result;

    }



    //MODIFIES: this.hiraganaRow1
    //EFFECTS: initializes all hiragana in row 1
    public void initializeHiraganaRow1() {

        Problem p1 = new Problem("あ", "a");
        hiraganaRow1.add(p1);
        Problem p2 = new Problem("い", "i");
        hiraganaRow1.add(p2);
        Problem p3 = new Problem("う", "u");
        hiraganaRow1.add(p3);
        Problem p4 = new Problem("え", "e");
        hiraganaRow1.add(p4);
        Problem p5 = new Problem("お", "o");
        hiraganaRow1.add(p5);

    }

    //MODIFIES: this.hiraganaRow2
    //EFFECTS: initializes all hiragana in row 2
    public void initializeHiraganaRow2() {

        Problem p1 = new Problem("か", "ka");
        hiraganaRow2.add(p1);
        Problem p2 = new Problem("き", "ki");
        hiraganaRow2.add(p2);
        Problem p3 = new Problem("く", "ku");
        hiraganaRow2.add(p3);
        Problem p4 = new Problem("け", "ke");
        hiraganaRow2.add(p4);
        Problem p5 = new Problem("こ", "ko");
        hiraganaRow2.add(p5);

    }

    //MODIFIES: this.hiraganaRow3
    //EFFECTS: initializes all hiragana in row 3
    public void initializeHiraganaRow3() {

        Problem p1 = new Problem("さ", "sa");
        hiraganaRow3.add(p1);
        Problem p2 = new Problem("し", "shi");
        hiraganaRow3.add(p2);
        Problem p3 = new Problem("す", "su");
        hiraganaRow3.add(p3);
        Problem p4 = new Problem("せ", "se");
        hiraganaRow3.add(p4);
        Problem p5 = new Problem("そ", "so");
        hiraganaRow3.add(p5);

    }

    //MODIFIES: this.hiraganaRow4
    //EFFECTS: initializes all hiragana in row 4
    public void initializeHiraganaRow4() {

        Problem p1 = new Problem("た", "ta");
        hiraganaRow4.add(p1);
        Problem p2 = new Problem("ち", "chi");
        hiraganaRow4.add(p2);
        Problem p3 = new Problem("つ", "tsu");
        hiraganaRow4.add(p3);
        Problem p4 = new Problem("て", "te");
        hiraganaRow4.add(p4);
        Problem p5 = new Problem("と", "to");
        hiraganaRow4.add(p5);

    }

    //MODIFIES: this.hiraganaRow5
    //EFFECTS: initializes all hiragana in row 5
    public void initializeHiraganaRow5() {

        Problem p1 = new Problem("な", "na");
        hiraganaRow5.add(p1);
        Problem p2 = new Problem("に", "ni");
        hiraganaRow5.add(p2);
        Problem p3 = new Problem("ぬ", "nu");
        hiraganaRow5.add(p3);
        Problem p4 = new Problem("ね", "ne");
        hiraganaRow5.add(p4);
        Problem p5 = new Problem("の", "no");
        hiraganaRow5.add(p5);

    }

    //MODIFIES: this.hiraganaRow 6
    //EFFECTS: initializes all hiragana in row 6
    public void initializeHiraganaRow6() {

        Problem p1 = new Problem("は", "ha");
        hiraganaRow6.add(p1);
        Problem p2 = new Problem("ひ", "hi");
        hiraganaRow6.add(p2);
        Problem p3 = new Problem("ふ", "hu");
        hiraganaRow6.add(p3);
        Problem p4 = new Problem("へ", "he");
        hiraganaRow6.add(p4);
        Problem p5 = new Problem("ほ", "ho");
        hiraganaRow6.add(p5);

    }

    //MODIFIES: this.hiraganaRow 7
    //EFFECTS: initializes all hiragana in row 7
    public void initializeHiraganaRow7() {

        Problem p1 = new Problem("ま", "ma");
        hiraganaRow7.add(p1);
        Problem p2 = new Problem("み", "mi");
        hiraganaRow7.add(p2);
        Problem p3 = new Problem("む", "mu");
        hiraganaRow7.add(p3);
        Problem p4 = new Problem("め", "me");
        hiraganaRow7.add(p4);
        Problem p5 = new Problem("も", "mo");
        hiraganaRow7.add(p5);

    }

    //MODIFIES: this.hiraganaRow8
    //EFFECTS: initializes all hiragana in row 8
    public void initializeHiraganaRow8() {

        Problem p1 = new Problem("や", "ya");
        hiraganaRow8.add(p1);
        Problem p2 = new Problem("ゆ", "yu");
        hiraganaRow8.add(p2);
        Problem p3 = new Problem("よ", "yo");
        hiraganaRow8.add(p3);

    }

    //MODIFIES: this.hiraganaRow9
    //EFFECTS: initializes all hiragana in row 9
    public void initializeHiraganaRow9() {

        Problem p1 = new Problem("ら", "ra");
        hiraganaRow9.add(p1);
        Problem p2 = new Problem("り", "ri");
        hiraganaRow9.add(p2);
        Problem p3 = new Problem("る", "ru");
        hiraganaRow9.add(p3);
        Problem p4 = new Problem("れ", "re");
        hiraganaRow9.add(p4);
        Problem p5 = new Problem("ろ", "ro");
        hiraganaRow9.add(p5);

    }

    //MODIFIES: this.hiraganaRow10
    //EFFECTS: initializes all hiragana in row 10
    public void initializeHiraganaRow10() {

        Problem p1 = new Problem("わ", "wa");
        hiraganaRow10.add(p1);
        Problem p2 = new Problem("を", "wo");
        hiraganaRow10.add(p2);

    }

    //MODIFIES: this.hiraganaRow11
    //EFFECTS: initializes all hiragana in row 11
    public void initializeHiraganaRow11() {

        Problem p1 = new Problem("ん", "n");
        hiraganaRow11.add(p1);

    }

    //MODIFIES: this.hiraganaRow12
    //EFFECTS: initializes all hiragana in row 12
    public void initializeHiraganaRow12() {

        Problem p1 = new Problem("が", "ga");
        hiraganaRow12.add(p1);
        Problem p2 = new Problem("ぎ", "gi");
        hiraganaRow12.add(p2);
        Problem p3 = new Problem("ぐ", "gu");
        hiraganaRow12.add(p3);
        Problem p4 = new Problem("げ", "ge");
        hiraganaRow12.add(p4);
        Problem p5 = new Problem("ご", "go");
        hiraganaRow12.add(p5);

    }

    //MODIFIES: this.hiraganaRow13
    //EFFECTS: initializes all hiragana in row 13
    public void initializeHiraganaRow13() {

        Problem p1 = new Problem("ざ", "za");
        hiraganaRow13.add(p1);
        Problem p2 = new Problem("じ", "ji");
        hiraganaRow13.add(p2);
        Problem p3 = new Problem("ず", "zu");
        hiraganaRow13.add(p3);
        Problem p4 = new Problem("ぜ", "ze");
        hiraganaRow13.add(p4);
        Problem p5 = new Problem("ぞ", "zo");
        hiraganaRow13.add(p5);

    }

    //MODIFIES: this.hiraganaRow14
    //EFFECTS: initializes all hiragana in row 14
    public void initializeHiraganaRow14() {

        Problem p1 = new Problem("だ", "da");
        hiraganaRow14.add(p1);
        Problem p2 = new Problem("ぢ", "ji");
        hiraganaRow14.add(p2);
        Problem p3 = new Problem("づ", "zu");
        hiraganaRow14.add(p3);
        Problem p4 = new Problem("で", "de");
        hiraganaRow14.add(p4);
        Problem p5 = new Problem("ど", "do");
        hiraganaRow14.add(p5);

    }

    //MODIFIES: this.hiraganaRow15
    //EFFECTS: initializes all hiragana in row 15
    public void initializeHiraganaRow15() {

        Problem p1 = new Problem("ば", "ba");
        hiraganaRow15.add(p1);
        Problem p2 = new Problem("び", "bi");
        hiraganaRow15.add(p2);
        Problem p3 = new Problem("ぶ", "bu");
        hiraganaRow15.add(p3);
        Problem p4 = new Problem("べ", "be");
        hiraganaRow15.add(p4);
        Problem p5 = new Problem("ぼ", "bo");
        hiraganaRow15.add(p5);

    }

    //MODIFIES: this.hiraganaRow16
    //EFFECTS: initializes all hiragana in row 16
    public void initializeHiraganaRow16() {

        Problem p1 = new Problem("ぱ", "pa");
        hiraganaRow16.add(p1);
        Problem p2 = new Problem("ぴ", "pi");
        hiraganaRow16.add(p2);
        Problem p3 = new Problem("ぷ", "pu");
        hiraganaRow16.add(p3);
        Problem p4 = new Problem("ぺ", "pe");
        hiraganaRow16.add(p4);
        Problem p5 = new Problem("ぽ", "po");
        hiraganaRow16.add(p5);

    }

    //MODIFIES: this.vocabFamily
    //EFFECTS: initializes all family vocab problems
    public void initializeVocabFamily() {
        Problem p1 = new Problem("おとうさん", "father");
        vocabFamily.add(p1);
        Problem p2 = new Problem("おかあさん", "mother");
        vocabFamily.add(p2);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("displayType", displayType);
        json.put("problemSet", problemSetToJson());
        return json;
    }

    // EFFECTS: returns things in this ProblemSet as a JSON array
    private JSONArray problemSetToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Problem p : problemSet) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
