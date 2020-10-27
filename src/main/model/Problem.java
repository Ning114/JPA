package model;


//CLASS LEVEL COMMENT:
//Problem class represents a Japanese/English practice problem. It stores the English and Japanese translation of a
//word, sentence or vocabulary of the problem, as well as the score attained on the problem.
public class Problem {

    public String japaneseProblem;
    public String englishProblem;
    public int score;


    //Creates a problem with the Japanese and English translation, as well as the score (default 0)
    public Problem(String japaneseProblem, String englishProblem) {

        this.japaneseProblem = japaneseProblem;
        this.englishProblem = englishProblem;
        this.score = 0;

    }

    //EFFECTS: returns true if the given English translation corresponds to the Japanese translation.
    //Also sets score = 1 if the user gets the question correct. Returns false otherwise.
    public Boolean isCorrectEnglish(String answer) {

        if (answer == this.englishProblem) {
            this.score = 1;
            return true;
        } else {
            this.score = 0;
            return false;
        }

    }

    //EFFECTS: returns true if the given Japanese translation corresponds to the Japanese translation.
    //Also sets score = 1 if the user gets the question correct. Returns false otherwise.
    public Boolean isCorrectJapanese(String answer) {

        if (answer == this.japaneseProblem) {
            this.score = 1;
            return true;
        } else {
            this.score = 0;
            return false;
        }

    }

    //EFFECTS: returns this.score
    public int getScore() {

        return this.score;

    }


}
