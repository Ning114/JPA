package ui;

import model.Problem;
import model.ProblemSet;
import model.SizeTooLarge;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import static ui.ProjectApp.JSON_STORE;


//CLASS LEVEL COMMENT: GUI class is the main class that calls all the helper methods needed to create the GUI and
//it's components.
public class Gui extends JPanel implements ActionListener {


    private final JComponent titleHiraganaSet1;
    private final JComponent titleHiraganaSet2;
    private JComponent titleTextBox;
    private JTabbedPane tabbedPane;
    private JPanel controls;

    private JPanel mainMenuPanel;
    private JTable activeProblemSetTable;
    public ProblemSetTable table;
    public JPanel createSetTab;
    public JPanel playSetTab;

    JLabel currQuestion;
    JLabel currScore;
    int currScoreVal;
    int currQuestionVal;
    public Boolean isStart = true;

    public JTextField ansBox;
    public String textAns;
    public ActionListener submitAns;
    public ActionListener createProblemSet;



    private JButton createProblemSetButton;
    private JButton loadProblemSetButton;
    private JButton saveProblemSetButton;

    private JRadioButton hiraganaSet1False;
    private JRadioButton hiraganaSet1True;
    ButtonGroup hiraganaSet1Buttons = new ButtonGroup();
    private JRadioButton hiraganaSet2False;
    private JRadioButton hiraganaSet2True;
    ButtonGroup hiraganaSet2Buttons = new ButtonGroup();
    private JRadioButton vocabFamilyFalse;
    private JRadioButton vocabFamilyTrue;
    ButtonGroup vocabFamilyButtons = new ButtonGroup();
    private JRadioButton displayTypeJapanese;
    private JRadioButton displayTypeEnglish;
    ButtonGroup displayTypeButtons = new ButtonGroup();

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    private ProblemSet activeProblemSet;

    //The activeProblemSetTable gets put into the scrollPane so the table can scroll Poggers
    private JScrollPane scrollPane;


    private JComponent titleTextBox2;

    private JTextField problemSetSize;


    private int problemSize;
    private JComponent problemSetSizeTextPanel;
    private JTextArea textField;


    //EFFECTS: main constructor for GUI. Calls all the helper methods needed to create GUI.
    public Gui() {
        super(new GridLayout(1, 1));

        activeProblemSet = new ProblemSet("japanese");

        tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");

        //title of application
        titleTextBox = makeTextPanel("Welcome to the Japanese Hiragana + Katakana Practice application!");
        titleTextBox2 = makeTextPanel("Create a new problem set (All sets are enabled be default):");

        titleHiraganaSet1 = makeTextPanel("Hiragana set 1");
        titleHiraganaSet2 = makeTextPanel("Hiragana set 2");

        controls = new JPanel();
        controls.setLayout(new GridLayout(4, 1));


        initPersistence();

        initMainMenu();

        initCreateSetPanel();

        initPlayPanel();

        tabbedPane.addTab("Main Menu", icon, mainMenuPanel,
                "Main Menu");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


        //This will be the tab with the table of problems in the active problem set
        initActiveProblemSetTable();

        initializeTabPanes(icon);
    }


    //created with help from:
    //https://stackoverflow.com/questions/4419667/detect-enter-press-in-jtextfield
    //https://stackoverflow.com/questions/5328945/how-to-clear-the-jtextfield-by-clicking-jbutton
    private void initPlayPanel() {
        playSetTab = new JPanel();
        playSetTab.setLayout(new GridLayout(3, 1));

        JLabel miscCounter = new JLabel();
        miscCounter.setLayout(new GridLayout(1, 2));

        currQuestion = new JLabel();
        currQuestion.setText("Problem Set Progress: " + currQuestionVal + "/" + activeProblemSet.problemSet.size());
        currQuestion.setFont(new Font("SansSerif", Font.PLAIN, 25));
        currQuestion.setHorizontalAlignment(SwingConstants.CENTER);

        currScore = new JLabel();
        currScore.setText("Current Score: " + currScoreVal + "/" + activeProblemSet.problemSet.size());
        currScore.setFont(new Font("SansSerif", Font.PLAIN, 25));
        currScore.setHorizontalAlignment(SwingConstants.CENTER);

        miscCounter.add(currQuestion);
        miscCounter.add(currScore);

        textField = new JTextArea();
        textField.setEditable(false);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 25));

        textField.setText("Press enter to start once you have a problem set ready!");



//        String str = "";
//        for (int i = 0; i < 3; ++i) {
//            str += "Some text\n";
//            System.out.println("doing stuff");
//            textField.setText(str);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //USE THIS TO ADD TEXT TO THE BOX!
//        textField.setText(str);

        JScrollPane scroll = new JScrollPane(textField);
        scroll.setBounds(10, 11, 455, 249);                     // <-- THIS


        //EFFECTS: creates the text field in which the user enters their answers to problems
        ansBox = new JTextField(50);
        ansBox.setSize(100, 20);
        ansBox.setFont(new Font("SansSerif", Font.PLAIN, 20));



        //pressing enter in the textField will run this function.
        submitAns = e -> {

            textAns = ansBox.getText();

            if (isStart) {
                if (activeProblemSet.problemSet.size() > 0) {
                    isStart = false;
                    textField.setText("");
                    if (activeProblemSet.displayType.equals("english")) {
                        //!!!
                        //I do not understand why this always prints the opposite. For now they're set to both .englishProblem
                        textField.setText(textField.getText() + "\n" + activeProblemSet.problemSet.get(currQuestionVal).japaneseProblem);
                    } else {
                        textField.setText(textField.getText() + "\n" + activeProblemSet.problemSet.get(currQuestionVal).englishProblem);
                    }
                    textField.revalidate();
                    textField.repaint();
                }
                return;
            }

            updateQuizSession();
        };

        ansBox.addActionListener(submitAns);

        playSetTab.add(scroll);
        playSetTab.add(miscCounter);
        playSetTab.add(ansBox);


    }

    private void performanceSummary() {
        double score = currScoreVal / (double) activeProblemSet.problemSet.size();
        System.out.println(score);

        textField.setText("Final Score: " + currScoreVal + "/" + activeProblemSet.problemSet.size());


        if (score == 1) {
            textField.setText(textField.getText() + "\n" + "Well done! A perfect score");
        } else if (0.75 <= score && score < 1) {
            textField.setText(textField.getText() + "\n" + "Just a little more to go! Everyone is cheering you on!");
        } else if (0.5 <= score && score < 0.75) {
            textField.setText(textField.getText() + "\n" + "もっとがんばってください!");
        } else {
            textField.setText(textField.getText() + "\n" + "Your grade is chotto Yabe...");
        }
        isStart = true;
        currScoreVal = 0;
        currQuestionVal = 0;

    }

    private String generateCorrectPrompt() {
        playSound("./data/success.wav");
        String answer = "naisu!";
        int randomNum = ThreadLocalRandom.current().nextInt(1, 7 + 1);
        switch (randomNum) {
            case 1: answer = "naisu！";
            break;
            case 2: answer = "おつかれさまでした";
                break;
            case 3: answer = "ナイス！";
                break;
            case 4: answer = "ごうかく！";
                break;
            case 5: answer = "naisu!";
                break;
            case 6: answer = "Poggers";
                break;
            case 7: answer = "Correct!";
                break;
        }
        return answer;
    }

    //this should be the method that actually runs the quiz, does the scoring, etc.
    public void updateQuizSession() {

        //responsible for displaying user's answer
        textField.setText(textField.getText() + "\n" + textAns);
        textField.revalidate();
        textField.repaint();


        if (activeProblemSet.displayType.equals("english")) {
            textField.setText(textField.getText() + "\n" + activeProblemSet.problemSet.get(currQuestionVal).japaneseProblem);
        } else {
            textField.setText(textField.getText() + "\n" + activeProblemSet.problemSet.get(currQuestionVal).englishProblem);
        }


        //responsible for changing the score
        if (activeProblemSet.displayType.equals("japanese")) {
            if (activeProblemSet.problemSet.size() > 0) {
                //english problem
                if (activeProblemSet.problemSet.get(currQuestionVal).japaneseProblem.equals(textAns)) {
                    currScoreVal++;
                    textField.setText(textField.getText() + "\n" + generateCorrectPrompt());
                    textField.setText(textField.getText() + "\n");

                } else {
                    playSound("./data/failure.wav");
                    textField.setText(textField.getText() + "\n" + "Incorrect!");
                    textField.setText(textField.getText() + "\n");

                }
            }
        } else {
            if (activeProblemSet.problemSet.size() > 0) {
                //japanese problem
                if (activeProblemSet.problemSet.get(currQuestionVal).englishProblem.equals(textAns)) {
                    currScoreVal++;
                    textField.setText(textField.getText() + "\n" + generateCorrectPrompt());
                    textField.setText(textField.getText() + "\n");
                } else {
                    playSound("./data/failure.wav");
                    textField.setText(textField.getText() + "\n" + "Incorrect!");
                    textField.setText(textField.getText() + "\n");

                }
            }
        }


        //display next question
        currQuestionVal++;
        if (currQuestionVal >= activeProblemSet.problemSet.size()) {
            //problem set is over, we've finished the last question.
            //so we do nothing here because there is no next question to display!
//                    fullReset();
            performanceSummary();
        } else if (activeProblemSet.displayType.equals("english")) {
            textField.setText(textField.getText() + "\n" + activeProblemSet.problemSet.get(currQuestionVal).japaneseProblem);
        } else {
            System.out.println(activeProblemSet.problemSet.get(currQuestionVal).englishProblem);
            textField.setText(textField.getText() + "\n" + activeProblemSet.problemSet.get(currQuestionVal).englishProblem);
        }
        textField.revalidate();
        textField.repaint();


        ansBox.setText("");



        //update currQuestion and currScore displays
        currQuestion.setText("Problem Set Progress: " + currQuestionVal + "/" + activeProblemSet.problemSet.size());
        currScore.setText("Current Score: " + currScoreVal + "/" + activeProblemSet.problemSet.size());


    }



    //CITATION: made with help from:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    //MODIFIES: this
    //EFFECTS: creates the tab panes to put on our main JFrame
    private void initializeTabPanes(ImageIcon icon) {
        tabbedPane.addTab("Active Problem Set", icon, scrollPane,
                "Displays each problem in the current active problem set");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Create New Problem Set", icon, createSetTab,
                "Create a new problem set");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.addTab("Play Problem Set", icon, playSetTab,
                "Create a new problem set");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }


    //MODIFIES: this
    //EFFECTS: starts the construction process for creating the createProblemSetTab.
    private void initCreateSetPanel() {
        createSetTab = new JPanel();
        createSetTab.setLayout(new GridLayout(12, 1));

        updateMaxProblemSetSize();

        createSetTab.add(titleTextBox2);
        initializeRadioButtons();
        JPanel hiraganaSet1Panel = new JPanel();
        hiraganaSet1Panel.setLayout(new FlowLayout());

        hiraganaSet1Panel.add(hiraganaSet1False);
        hiraganaSet1Panel.add(hiraganaSet1True);

        JPanel hiraganaSet2Panel = new JPanel();
        hiraganaSet1Panel.setLayout(new FlowLayout());

        hiraganaSet2Panel.add(hiraganaSet2False);
        hiraganaSet2Panel.add(hiraganaSet2True);

        JPanel vocabSetFamilyPanel = new JPanel();
        vocabSetFamilyPanel.setLayout(new FlowLayout());

        vocabSetFamilyPanel.add(vocabFamilyFalse);
        vocabSetFamilyPanel.add(vocabFamilyTrue);

        JPanel displayTypePanel = new JPanel();
        displayTypePanel.setLayout(new FlowLayout());

        displayTypePanel.add(displayTypeJapanese);
        displayTypePanel.add(displayTypeEnglish);


        constructCreateProblemSetTab(hiraganaSet1Panel, hiraganaSet2Panel, vocabSetFamilyPanel, displayTypePanel);

    }


    //MODIFIES: this
    //EFFECTS: constructs the createProblemSetTab and slaps the needed panels onto it for functionality
    private void constructCreateProblemSetTab(JPanel hiraganaSet1Panel, JPanel hiraganaSet2Panel,
                                              JPanel vocabSetFamilyPanel, JPanel displayTypePanel) {
        createSetTab.add(makeTextPanel("Input type"));
        createSetTab.add(displayTypePanel);
        createSetTab.add(titleHiraganaSet1);
        createSetTab.add(hiraganaSet1Panel);
        createSetTab.add(titleHiraganaSet2);
        createSetTab.add(hiraganaSet2Panel);
        createSetTab.add(makeTextPanel("Vocab Family Set"));
        createSetTab.add(vocabSetFamilyPanel);

        //EFFECTS: creates the text field in which the user inputs the size of their problem set before hitting create
        problemSetSize = new JTextField(activeProblemSet.availableProblems.size());
        problemSetSize.addActionListener(e -> {
            String text = problemSetSize.getText();
            problemSize = Integer.parseInt(text);

        });

        //EFFECTS: part 2 for constructing createProblemSetTab. Called after constructCreateProblemSetTab
        problemSetSizeTextPanel = makeTextPanel("Enter the size of your problem set (Must be size <= "
                + activeProblemSet.availableProblems.size() + "):");
        createSetTab.add(problemSetSizeTextPanel);
        createSetTab.add(problemSetSize);

        createProblemSetButton = makeButton("Create Problem Set");
        initCreateSetBut();
        createSetTab.add(createProblemSetButton);
    }


    //MODIFIES: this
    //EFFECTS: regenerates the availableproblems problemset by generating a new one everytime a change is made
    public void updateMaxProblemSetSize() {

        activeProblemSet.availableProblems.clear();
        activeProblemSet.generateAvailableProblems();

//        problemSetSizeTextPanel = makeTextPanel("Enter the size of your problem set (Must be size <= "
//                + activeProblemSet.availableProblems.size() + "):");
        problemSetSizeTextPanel = makeTextPanel("Enter the size of your problem set (Must be size <= "
                + activeProblemSet.availableProblems.size() + "):");
        problemSetSizeTextPanel.revalidate();
        problemSetSizeTextPanel.repaint();


        System.out.println(activeProblemSet.availableProblems.size());

        problemSetSizeTextPanel.update(problemSetSizeTextPanel.getGraphics());
        problemSetSizeTextPanel.revalidate();
        problemSetSizeTextPanel.repaint();
        createSetTab.revalidate();
        createSetTab.repaint();
//        createSetTab.update(createSetTab.getGraphics());
    }

    //MODIFIES: problemSetUi, problemSetUi subjects, problemSetUi hiraganaRows, problemSetUi.displayType
    //EFFECTS: resets problem set to default settings:
    //problemSetUi.displayType = "japanese";
    //empties problemSetUi problemSet List
    //empties problemSetUi availableProblems
    //enables all subjects and hiragana rows
    public void fullReset() {
        activeProblemSet.displayType = "japanese";
        activeProblemSet.problemSet.clear();
        activeProblemSet.availableProblems.clear();
        table.resetTable();
        isStart = true;

        if (!activeProblemSet.hiraganaSet1) {
            toggleHiraganaOneToEight(true);
        }
        if (!activeProblemSet.hiraganaSet9) {
            toggleHiraganaNineToSixteen(true);
        }
        if (!activeProblemSet.vocabFamilySet) {
            activeProblemSet.vocabFamilySet = true;
        }

    }


    //MODIFIES: this
    //EFFECTS: initializes the button sets
    private void initializeRadioButtons() {
        hiraganaSet1False = makeRadioButton("Disable set");
        hiraganaSet1True = makeRadioButton("Enable set");

        hiraganaSet1Buttons.add(hiraganaSet1False);
        hiraganaSet1Buttons.add(hiraganaSet1True);

        hiraganaSet2False = makeRadioButton("Disable set");
        hiraganaSet2True = makeRadioButton("Enable set");

        hiraganaSet2Buttons.add(hiraganaSet2False);
        hiraganaSet2Buttons.add(hiraganaSet2True);

        vocabFamilyFalse = makeRadioButton("Disable set");
        vocabFamilyTrue = makeRadioButton("Enable set");

        vocabFamilyButtons.add(vocabFamilyFalse);
        vocabFamilyButtons.add(vocabFamilyTrue);

        displayTypeJapanese = makeRadioButton("Japanese");
        displayTypeEnglish = makeRadioButton("English");

        displayTypeButtons.add(displayTypeJapanese);
        displayTypeButtons.add(displayTypeEnglish);

        initHirSet1But();
        initHirSet2But();
        initVocabFamBut();
        initDisplayButton();
        initDisplayButton();
    }

    //MODIFIES: this
    //EFFECTS: initializes the button functionality for hiraganaset1
    public void initHirSet1But() {
        hiraganaSet1True.setSelected(true);
        hiraganaSet1True.addActionListener(e -> {
            toggleHiraganaOneToEight(true);

            updateMaxProblemSetSize();

        });
        hiraganaSet1False.addActionListener(e -> {
            toggleHiraganaOneToEight(false);

            updateMaxProblemSetSize();

        });
    }

    //MODIFIES: this
    //EFFECTS: Initializes button functionality for hiraganaset2
    public void initHirSet2But() {
        hiraganaSet2True.setSelected(true);
        hiraganaSet2True.addActionListener(e -> {
            toggleHiraganaNineToSixteen(true);

            updateMaxProblemSetSize();

        });
        hiraganaSet2False.addActionListener(e -> {
            toggleHiraganaNineToSixteen(false);

            updateMaxProblemSetSize();

        });
    }

    //MODIFIES: this
    //EFFECTS: Initializes button functionality for vocabfamilyset
    public void initVocabFamBut() {
        vocabFamilyTrue.setSelected(true);
        vocabFamilyTrue.addActionListener(e -> {
            activeProblemSet.vocabFamilySet = true;

            updateMaxProblemSetSize();

        });
        vocabFamilyFalse.addActionListener(e -> {
            activeProblemSet.vocabFamilySet = false;

            updateMaxProblemSetSize();

        });
    }

    //MODIFIES: this
    //EFFECTS: Initializes button functionality for create problem set button. If button is pressed,
    //it should do nothing if problem size
    //entered is larger than activeProblemSet.availableProblems size.
    //Plays a sound when problem set created successfully
    public void initCreateSetBut() {
        createProblemSet = e -> {
            if (problemSize <= activeProblemSet.availableProblems.size()) {
                String text = problemSetSize.getText();
                problemSize = Integer.parseInt(text);
                try {
                    table.resetTable();
                    activeProblemSet.generateProblemSet(problemSize);

                } catch (SizeTooLarge sizeTooLarge) {
                    playSound("./data/failure.wav");
                    System.out.println("Problem set size is not equal to or less than available problem set size!"
                            + " Try creating a problem set with size <= "
                            + activeProblemSet.availableProblems.size() + ".");
                    return;
                }
                table.fillTable();
                playSound("./data/success.wav");

                //update score and question displays
                currQuestionVal = 0;
                currQuestion.setText("Problem Set Progress: " + currQuestionVal + "/" + activeProblemSet.problemSet.size());
                currScore.setText("Current Score: " + currScoreVal + "/" + activeProblemSet.problemSet.size());
            }

        };
        createProblemSetButton.addActionListener(createProblemSet);


    }

    //MODIFIES: this
    //EFFECTS: Initializes displaytypebutton functionality
    public void initDisplayButton() {
        displayTypeJapanese.setSelected(true);
        displayTypeJapanese.addActionListener(e -> activeProblemSet.displayType = "japanese");
        displayTypeEnglish.addActionListener(e -> activeProblemSet.displayType = "english");
    }

    //MODIFIES: problemSetUi.hiraganaSet1-8
    //EFFECTS: changes all hiraganaSets 1-8 to the given boolean value (false/true)
    private void toggleHiraganaOneToEight(Boolean bool) {
        activeProblemSet.hiraganaSet1 = bool;
        activeProblemSet.hiraganaSet2 = bool;
        activeProblemSet.hiraganaSet3 = bool;
        activeProblemSet.hiraganaSet4 = bool;
        activeProblemSet.hiraganaSet5 = bool;
        activeProblemSet.hiraganaSet6 = bool;
        activeProblemSet.hiraganaSet7 = bool;
        activeProblemSet.hiraganaSet8 = bool;
    }

    //MODIFIES: problemSetUi.hiraganaSet9-16
    //EFFECTS: changes all hiraganaSets 9-16 to the given boolean value (false/true)
    private void toggleHiraganaNineToSixteen(Boolean bool) {
        activeProblemSet.hiraganaSet9 = bool;
        activeProblemSet.hiraganaSet10 = bool;
        activeProblemSet.hiraganaSet11 = bool;
        activeProblemSet.hiraganaSet12 = bool;
        activeProblemSet.hiraganaSet13 = bool;
        activeProblemSet.hiraganaSet14 = bool;
        activeProblemSet.hiraganaSet15 = bool;
        activeProblemSet.hiraganaSet16 = bool;
    }


    //MODIFIES: this
    //EFFECTS: Initializes/creates new table that will display each problem in the active data set.
    //DON'T FORGET TO CALL problemSetTable.fillTable(); everytime you alter the active data set in some way (!!!)
    //e.g.) Call this method after loading and creating a new problemSet.
    private void initActiveProblemSetTable() {

        ProblemSetTable problemSetTable = new ProblemSetTable(this);


        this.table = problemSetTable;
        activeProblemSetTable = problemSetTable.getTable();
        activeProblemSetTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        activeProblemSetTable.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        scrollPane = new JScrollPane(activeProblemSetTable);

        //Add the scroll pane to this panel.
        add(scrollPane);

    }

    //MODIFIES: this
    //EFFECTS: creates the main menu panel. Also slaps the title panel and load/save problem set buttons onto it.
    private void initMainMenu() {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(5, 1));

        mainMenuPanel.add(titleTextBox);
        //Add buttons to experiment with Grid Layout
        mainMenuPanel.add(loadProblemSetButton);
        mainMenuPanel.add(saveProblemSetButton);
    }

    //MODIFIES: this
    //EFFECTS: initializes the buttons required for persistence, as well as their functionality
    private void initPersistence() {
        //creating menu buttons
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadProblemSetButton = makeButton("Load saved problem set");
        initLoadButFunc();
        saveProblemSetButton = makeButton("Save active problem set");
        initSaveButFunc();
    }


    //CITATION: Based off of loadWorkRoom method in WorkRoomApp class of JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: loads saved problem set when action is triggered
    private void initLoadButFunc() {

        loadProblemSetButton.addActionListener(e -> {
            try {
                fullReset();

                activeProblemSet = jsonReader.read();
//                isStart = false;
                System.out.println("Loaded problem set from " + JSON_STORE + ".");

                //update score and question displays
                table.fillTable();
                currQuestionVal = 0;
                currQuestion.setText("Problem Set Progress: " + currQuestionVal + "/" + activeProblemSet.problemSet.size());
                currScore.setText("Current Score: " + currScoreVal + "/" + activeProblemSet.problemSet.size());


                playSound("./data/success.wav");

            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        });
    }

    //CITATION: Based off of saveWorkRoom method in WorkRoomApp class of JsonSerializationDemo
    //MODIFIES: this

    //EFFECTS: loads saved problem set when action is triggered. Does nothing if current active set is empty.
    private void initSaveButFunc() {

        saveProblemSetButton.addActionListener(e -> {

            if (activeProblemSet.problemSet.size() > 0) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(activeProblemSet);
                    jsonWriter.close();
                    System.out.println("Saved problem set to " + JSON_STORE + ".");
                    playSound("./data/success.wav");
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }

    //CITATION: taken from demo project TabbedPaneDemoProject from:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    //EFFECTS: helper function for creating text panels
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    //CITATION: taken from demo project TabbedPaneDemoProject from:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    //EFFECTS: helper function for creating a button with the given label
    protected JButton makeButton(String text) {
        //creating play active problem set button and adding it to tab1
        final JButton button = new JButton(text);
        return button;

    }

    //CITATION: based off of the makeButton helper method taken from demo project TabbedPaneDemoProject from:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    //EFFECTS: helper function for creating a radio button with the given label
    protected JRadioButton makeRadioButton(String text) {
        //creating play active problem set button and adding it to tab1
        final JRadioButton button = new JRadioButton(text);
        return button;
    }


    //I can't create tab panels without this so I just have to leave it in lol
    //CITATION: taken from demo project TabbedPaneDemoProject from:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    //EFFECTS: creates an image icon object taken from the given path.
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Gui.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            //System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    //CITATION: I don't remember exactly where this was taken from, but most likely the TabbedPaneDemoProject from:
    //https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    //EFFECTS: creates the main panel our tabbed panes go on.
    static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Japanese Hiragana & Katakana Practice Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Gui(), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //EFFECTS: returns the current active data set
    //CITATION: created using help from:
    //http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    public ProblemSet getActiveProblemSet() {
        return this.activeProblemSet;
    }

    //EFFECTS: plays the specified sound when called
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}

