package model;

import java.util.ArrayList;

public class ProblemSet {

    private String displayType;
    private int scoreThisSet;
    private int scoreThisSetTotal;
    private ArrayList<Problem> problemSet;
    private ArrayList<Problem> availableProblems;

    private Boolean hiraganaSet;
    private Boolean katakanaSet;
    private Boolean timeSet;
    private Boolean vocabularySet;

    //Initializes an empty problemSet with the specified displayType.
    //By default, all subjects are enabled and can be changed by the User later.
    //scoreThisSetTotal is determined after a problem set is generated and scoreThisSet is calculated after the user
    //has run through the problem set (still initialized as 0).
    public ProblemSet(String displayType) {

        this.displayType = displayType;
        this.scoreThisSet = 0;

        this.hiraganaSet = true;
        this.katakanaSet = true;
        this.timeSet = true;
        this.vocabularySet = true;

        this.problemSet = new ArrayList<Problem>();

    }




    //REQUIRES: at least one Subject set is enabled
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

        //(int)(Math.random() * (max - min + 1) + min);
        int random = (int)(Math.random() * (availableProblems.size() - 0 + 1) + 0);

        return availableProblems.get(random);
    }


    //EFFECTS: gets the maximum score one can achieve in the current problem set.
    public int getScoreThisSetTotal() {

        return problemSet.size();

    }

    //REQUIRES: at least one Subject set is enabled
    //MODIFIES: this.availableProblems
    //EFFECTS: Generates a list of available problems based on which subject sets are enabled
    public void generateAvailableProblems() {

        //This will be the list of all available problems that can be added to the problem set.
        this.availableProblems = new ArrayList<Problem>();

        if (this.hiraganaSet) {
            //availableProblems.addAll(hiraganaSetList);
        }
        if (this.katakanaSet) {
            //availableProblems.addAll(katakanaSetList);
        }
        if (this.timeSet) {
            //availableProblems.addAll(timeSetList);
        }
        if (this.vocabularySet) {
            //availableProblems.addAll(vocabularySetList);
        }

    }




}
