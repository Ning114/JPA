package ui;

import model.Problem;
import model.ProblemSet;

import java.util.Scanner;

public class ProjectApp {

    private Boolean timer;
    private Long startTime;
    private Long endTime;
    private Scanner input;
    private ProblemSet problemSetUi;
    private int phase;


    public ProjectApp() {
        runProjectApp();
    }

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

    public void initiate() {
        input = new Scanner(System.in);
        problemSetUi = new ProblemSet("japanese");
    }

    public void displayMenu() {
        System.out.println(" ");
        System.out.println("Japanese Hiragana and Katakana Practice Application");
        System.out.println("Options: (Please enter a number)");
        System.out.println("1.) play current problem set");
        System.out.println("2.) create new problem set");
        System.out.println("3.) quit");

    }

    public void playProblemSet() {

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

    @SuppressWarnings("checkstyle:MethodLength")
    public void playProblemSetPhase2() {
        if (timer) {
            startTimer();
        }

        for (Problem p : problemSetUi.problemSet) {
            if (problemSetUi.displayType == "Japanese") {
                System.out.println("Translate the following into Japanese: ");
                System.out.println(p.englishProblem);
                String input1 = input.next();
                if (p.isCorrectJapanese(input1)) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Incorrect");
                }
            } else {
                System.out.println("Translate the following into English: ");
                System.out.println(p.japaneseProblem);
                String input1 = input.next();
                if (p.isCorrectEnglish(input1)) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Incorrect");
                }

            }
        }

        if (timer) {
            stopTimer();
        }

        System.out.println("Your score is: ");
        System.out.println(getPerformance() + " / " + problemSetUi.getScoreThisSetTotal());
        System.out.println("がんばってください！");


    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public void stopTimer() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;

        System.out.println("Elapsed time: " + elapsedSeconds + " seconds.");
    }

    public int getPerformance() {

        int sum = 0;

        for (Problem p: problemSetUi.problemSet) {

            sum += p.getScore();

        }

        return sum;
    }

    public void createProblemSetPart1() {
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

    @SuppressWarnings("checkstyle:MethodLength")
    public void createProblemSetPart2Continued1() {

        String input1 = input.next();

        while (phase == 2) {
            if (input1.equals("1")) {
                problemSetUi.hiraganaSet1 = !problemSetUi.hiraganaSet1;
                problemSetUi.hiraganaSet2 = !problemSetUi.hiraganaSet2;
                problemSetUi.hiraganaSet3 = !problemSetUi.hiraganaSet3;
                problemSetUi.hiraganaSet4 = !problemSetUi.hiraganaSet4;
                problemSetUi.hiraganaSet5 = !problemSetUi.hiraganaSet5;
                problemSetUi.hiraganaSet6 = !problemSetUi.hiraganaSet6;
                problemSetUi.hiraganaSet7 = !problemSetUi.hiraganaSet7;
                problemSetUi.hiraganaSet8 = !problemSetUi.hiraganaSet8;
                System.out.println("Hiragana rows 1-8 set to: " + problemSetUi.hiraganaSet1);
                input1 = input.next();
            } else if (input1.equals("2")) {
                problemSetUi.hiraganaSet9 = !problemSetUi.hiraganaSet9;
                problemSetUi.hiraganaSet10 = !problemSetUi.hiraganaSet10;
                problemSetUi.hiraganaSet11 = !problemSetUi.hiraganaSet11;
                problemSetUi.hiraganaSet12 = !problemSetUi.hiraganaSet12;
                problemSetUi.hiraganaSet13 = !problemSetUi.hiraganaSet13;
                problemSetUi.hiraganaSet14 = !problemSetUi.hiraganaSet14;
                problemSetUi.hiraganaSet15 = !problemSetUi.hiraganaSet15;
                problemSetUi.hiraganaSet16 = !problemSetUi.hiraganaSet16;
                System.out.println("Hiragana rows 9-16 set to: " + problemSetUi.hiraganaSet9);
                input1 = input.next();
            } else if (input1.equals("3")) {
                problemSetUi.vocabFamilySet = !problemSetUi.vocabFamilySet;
                System.out.println("Vocabulary family set to: " + problemSetUi.vocabFamilySet);
                input1 = input.next();
            } else if (input1.equals("4")) {
                System.out.println("lets go!");
                phase = 3;
                createProblemSetPart2Continued2();
                break;
            } else {
                System.out.println("invalid input");
                input1 = input.next();
            }


        }

    }

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
