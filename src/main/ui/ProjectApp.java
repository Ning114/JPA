package ui;

import model.Problem;
import model.ProblemSet;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

//CLASS LEVEL COMMENT:
//ProjectApp class is the ui of the Project. ProjectApp contains methods that operate the application, from creating
//new problem sets, playing them, etc.
//The user interacts with this class. This class is run from main.
public class ProjectApp {

    private Boolean timer;
    private Long startTime;
    private Scanner input;
    private ProblemSet problemSetUi;
    private int phase;


    //EFFECTS: runs application
    public ProjectApp() {
        runProjectApp();
    }

    //MODIFIES: this.phase
    //EFFECTS: prints main menu while the application is still running, and initializes phase to 1.
    public void runProjectApp() {
        Boolean keepGoing = true;
        String command = null;
        phase = 1;


        initiate();

        while (keepGoing) {

            displayMenu();
            command = input.next();
            command = command.toLowerCase();


            if (command.equals("3")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Thank you for using this application!");
        System.out.println("ありがとうございます！");


    }

    //EFFECTS: processes user command based on user input, taking user through process of creating new problem set or
    //playing an existing problem set.
    public void processCommand(String command) {

        if (command.equals("1")) {
            if (this.problemSetUi.problemSet.size() == 0) {
                System.out.println("Your problem set is empty or you haven't created one yet!");
            } else {
                playProblemSet();
            }
        } else if (command.equals("2")) {
            createProblemSetPart1();
        } else {
            System.out.println("Invalid selection");
        }


    }

    //MODIFIES: this.input, this.problemSetUi
    //EFFECTS: initiates input and problemSetUi fields. problemSetUi is initiated as "japanese" by default,
    //but can be changed by user in create problem set.
    public void initiate() {
        input = new Scanner(System.in);
        problemSetUi = new ProblemSet("japanese");
    }

    //EFFECTS: prints out main menu and options
    public void displayMenu() {
        System.out.println(" ");
        System.out.println("Japanese Hiragana and Katakana Practice Application");
        System.out.println("Options: (Please enter a number)");
        System.out.println("1.) play current problem set");
        System.out.println("2.) create new problem set");
        System.out.println("3.) quit");

    }

    //MODIFIES: this.phase, this.timer
    //EFFECTS: starts the playProblemSet process.  asks user if they want to be timed or not, and starts timing
    //them if yes. Calls playProblemSetPhase2 to continue the process
    public void playProblemSet() {

        phase = 1;

        System.out.println("Would you like to be timed?");
        System.out.println(" ");
        System.out.println("1.) yes");
        System.out.println("2.) no");

        String input1 = input.next();

        while (phase == 1) {

            if (input1.equals("1")) {
                timer = true;
                System.out.println("Start!");
                phase = 2;
                break;
            } else if (input1.equals("2")) {
                timer = false;
                System.out.println("Start!");
                phase = 2;
                break;
            } else {
                System.out.println("invalid input");
                input1 = input.next();
            }

        }

        playProblemSetPhase2();

    }


    //MODIFIES: problemSetUi.problemSet.p.score
    //EFFECTS: for each problem p in problemSetUi, print out the english/japanese question and ask for the answer.
    //After going through problemSet, returns score and time elapsed (if user wanted to be timed).
    public void playProblemSetPhase2() {

        if (timer) {
            startTimer();
        }

        for (Problem p : problemSetUi.problemSet) {

            if (problemSetUi.displayType == "japanese") {
                problemSetJapanese(p);
            } else if (problemSetUi.displayType == "english") {
                problemSetEnglish(p);

            }
        }

        if (timer) {
            stopTimer();
        }

        System.out.println("Your score is: ");
        System.out.println(getPerformance() + " / " + problemSetUi.getScoreThisSetTotal());
        System.out.println("がんばってください！");


    }

    //MODIFIES: problemSetUi.problemSet.p.score
    //EFFECTS: displays the Japanese problem and asks user to translate. Set Problem p's score to 1 if correct,
    //set score to 0 if incorrect.
    private void problemSetEnglish(Problem p) {
        System.out.println("Translate the following into English: ");
        System.out.println(p.japaneseProblem);
        String input1 = input.next();

        if (p.englishProblem.equals(input1)) {
            p.score = 1;
            System.out.println("Correct");
        } else {
            p.score = 0;
            System.out.println("Incorrect");
        }
    }

    //MODIFIES: problemSetUi.problemSet.p.score
    //EFFECTS: displays the English problem and asks user to translate. Set Problem p's score to 1 if correct,
    //set score to 0 if incorrect.
    private void problemSetJapanese(Problem p) {
        System.out.println("Translate the following into Japanese: ");
        System.out.println(p.englishProblem);
        String input1 = input.next();
        System.out.println(input1);

        if (p.japaneseProblem.equals(input1)) {
            p.score = 1;
            System.out.println("Correct");
        } else {
            p.score = 0;
            System.out.println("Incorrect");
        }
    }

    //MODIFIES: this.startTime
    //EFFECTS: records the time when the timer first started
    //CITATION: This method was created with the help of this post:
    //https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java/14323134
    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    //EFFECTS: calculates the time elapsed in seconds and prints it out.
    //CITATION: This method was created with the help of this post:
    //https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java/14323134
    public void stopTimer() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;

        System.out.println("Elapsed time: " + elapsedSeconds + " seconds.");
    }

    //EFFECTS: gets the score value for every Problem p in problemSet and returns that sum.
    public int getPerformance() {

        int sum = 0;

        for (Problem p : problemSetUi.problemSet) {

            sum += p.getScore();

        }

        return sum;
    }

    //MODIFIES: this.phase, this.problemSetUi.displayType
    //EFFECTS: asks the user for the desired input type, then changes the displayType of this.problemSetUi appropriately
    public void createProblemSetPart1() {
        fullReset();
        System.out.println("Choose your input type: ");
        System.out.println("1.) japanese");
        System.out.println("2.) english");


        String input1 = input.next();

        while (phase == 1) {
            if (input1.equals("1")) {
                problemSetUi.displayType = "japanese";
                phase = 2;
            } else if (input1.equals("2")) {
                problemSetUi.displayType = "english";
                phase = 2;
            } else {
                System.out.println("invalid input");
                input1 = input.next();
            }
        }
        createProblemSetPart2();

    }

    //MODIFIES: problemSetUi, problemSetUi subjects, problemSetUi hiraganaRows, problemSetUi.displayType
    //EFFECTS: resets problem set to default settings:
    //problemSetUi.displayType = "japanese";
    //empties problemSetUi list
    //enables all subjects and hiragana rows
    private void fullReset() {
        problemSetUi.displayType = "japanese";
        problemSetUi.problemSet.clear();
        problemSetUi.availableProblems.clear();

        if (!problemSetUi.hiraganaSet1) {
            toggleHiraganaOneToEight();
        }
        if (!problemSetUi.hiraganaSet9) {
            toggleHiraganaNineToSixteen();
        }
        if (!problemSetUi.vocabFamilySet) {
            problemSetUi.vocabFamilySet = !problemSetUi.vocabFamilySet;
        }

    }

    //EFFECTS: prints out study set options for user to toggle
    public void createProblemSetPart2() {
        System.out.println("Choose which subjects you want enabled/disabled in this problem set:");
        System.out.println("(all subjects enabled by default)");
        System.out.println(" ");
        System.out.println("1.) Hiragana row 1-8");
        System.out.println("2.) Hiragana row 9-16");
        System.out.println("3.) Vocabulary family");
        System.out.println(" ");
        System.out.println("4.) Ready to go");

        createProblemSetPart2Continued1();

    }

    //MODIFIES: problemSetUi.hiraganaSet1-16, problemSetUi.vocabFamilySet
    //EFFECTS: lets the user toggle on and off which subjects they want to appear in the problem set.
    public void createProblemSetPart2Continued1() {

        String input1 = input.next();

        while (phase == 2) {
            if (input1.equals("1")) {
                toggleHiraganaOneToEight();
                input1 = input.next();
            } else if (input1.equals("2")) {
                toggleHiraganaNineToSixteen();
                input1 = input.next();
            } else if (input1.equals("3")) {
                problemSetUi.vocabFamilySet = !problemSetUi.vocabFamilySet;
                System.out.println("Vocabulary family set to: " + problemSetUi.vocabFamilySet);
                input1 = input.next();
            } else if (input1.equals("4")) {
                phase = 3;
                createProblemSetPart2Continued2();
                break;
            } else {
                System.out.println("invalid input");
                input1 = input.next();
            }


        }

    }

    //MODIFIES: problemSetUi.hiraganaSet9-16
    //EFFECTS: changes all hiraganaSets 9-16 to their inverse Boolean value, then prints out what it was changed to
    private void toggleHiraganaNineToSixteen() {
        problemSetUi.hiraganaSet9 = !problemSetUi.hiraganaSet9;
        problemSetUi.hiraganaSet10 = !problemSetUi.hiraganaSet10;
        problemSetUi.hiraganaSet11 = !problemSetUi.hiraganaSet11;
        problemSetUi.hiraganaSet12 = !problemSetUi.hiraganaSet12;
        problemSetUi.hiraganaSet13 = !problemSetUi.hiraganaSet13;
        problemSetUi.hiraganaSet14 = !problemSetUi.hiraganaSet14;
        problemSetUi.hiraganaSet15 = !problemSetUi.hiraganaSet15;
        problemSetUi.hiraganaSet16 = !problemSetUi.hiraganaSet16;
        System.out.println("Hiragana rows 9-16 set to: " + problemSetUi.hiraganaSet9);
    }

    //MODIFIES: problemSetUi.hiraganaSet1-8
    //EFFECTS: changes all hiraganaSets 1-8 to their inverse Boolean value, then prints out what it was changed to
    private void toggleHiraganaOneToEight() {
        problemSetUi.hiraganaSet1 = !problemSetUi.hiraganaSet1;
        problemSetUi.hiraganaSet2 = !problemSetUi.hiraganaSet2;
        problemSetUi.hiraganaSet3 = !problemSetUi.hiraganaSet3;
        problemSetUi.hiraganaSet4 = !problemSetUi.hiraganaSet4;
        problemSetUi.hiraganaSet5 = !problemSetUi.hiraganaSet5;
        problemSetUi.hiraganaSet6 = !problemSetUi.hiraganaSet6;
        problemSetUi.hiraganaSet7 = !problemSetUi.hiraganaSet7;
        problemSetUi.hiraganaSet8 = !problemSetUi.hiraganaSet8;
        System.out.println("Hiragana rows 1-8 set to: " + problemSetUi.hiraganaSet1);
    }


    //MODIFIES: this.phase, problemSetUi.problemSet.size()
    //EFFECTS: lets user determine size of problemSet to generate. Size must be <= availableProblems list size
    //If user inputs a size <= availableProblems, tell the user to try again and input a value in that range
    public void createProblemSetPart2Continued2() {

        problemSetUi.generateAvailableProblems();
        System.out.println("Enter the size of your problem set: ");

        int input1 = input.nextInt();

        while (phase == 3) {

            if (input1 <= problemSetUi.availableProblems.size()) {

                problemSetUi.generateProblemSet(input1);
                System.out.println("Problem set successfully created");
                phase = 1;
                break;
            } else {
                System.out.println("Invalid input or your problem set is too large!");
                System.out.println("Your problem set must be <= " + problemSetUi.availableProblems.size());
                input1 = input.nextInt();
            }
        }

    }


}
