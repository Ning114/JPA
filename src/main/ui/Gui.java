package ui;

import model.ProblemSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ui.ProjectApp.JSON_STORE;

public class Gui extends JPanel implements ActionListener {

    private final JComponent titleHiraganaSet1;
    private final JComponent titleHiraganaSet2;
    private JComponent titleTextBox;
    private JTabbedPane tabbedPane;
    private JPanel controls;

    private JPanel mainMenuPanel;
    private JTable activeProblemSetTable;
    public ProblemSetTable problemSetTableField;
    public JPanel createNewProblemSetTab;

    private JButton playProblemSetButton;
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
    private final String newline = "\n";
    private JTextField textField;
    private JTextArea textArea;
    private int problemSize;
    private JComponent problemSetSizeTextPanel;


    @SuppressWarnings("checkstyle:MethodLength")
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


        initializeButtons();

        initializeMainMenuPanel();

        initializeCreateProblemSetPanel();

        tabbedPane.addTab("Main Menu", icon, mainMenuPanel,
                "Main Menu");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


        //This will be the tab with the table of problems in the active problem set
        initializeActiveProblemSetTable();

        tabbedPane.addTab("Active Problem Set", icon, scrollPane,
                "Displays each problem in the current active problem set");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Create New Problem Set", icon, createNewProblemSetTab,
                "Create a new problem set");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    @SuppressWarnings("checkstyle:MethodLength")
    private void initializeCreateProblemSetPanel() {
        createNewProblemSetTab = new JPanel();
        createNewProblemSetTab.setLayout(new GridLayout(12, 1));

        createNewProblemSetTab.add(titleTextBox2);
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


        createNewProblemSetTab.add(makeTextPanel("Input type"));
        createNewProblemSetTab.add(displayTypePanel);
        createNewProblemSetTab.add(titleHiraganaSet1);
        createNewProblemSetTab.add(hiraganaSet1Panel);
        createNewProblemSetTab.add(titleHiraganaSet2);
        createNewProblemSetTab.add(hiraganaSet2Panel);
        createNewProblemSetTab.add(makeTextPanel("Vocab Family Set"));
        createNewProblemSetTab.add(vocabSetFamilyPanel);

        initiateProblemSetSizeTextField();

        problemSetSizeTextPanel = makeTextPanel("Enter the size of your problem set (Must be size <= "
                + activeProblemSet.availableProblems.size() + "):");
        createNewProblemSetTab.add(problemSetSizeTextPanel);
        createNewProblemSetTab.add(problemSetSize);

        createProblemSetButton = makeButton("Create Problem Set");
        initializeCreateProblemSetButtonFunctionality();
        createNewProblemSetTab.add(createProblemSetButton);


    }

    public void updateMaxProblemSetSize() {
        activeProblemSet.availableProblems.clear();
        activeProblemSet.generateAvailableProblems();
        problemSetSizeTextPanel = makeTextPanel("Enter the size of your problem set (Must be size <= "
                + activeProblemSet.availableProblems.size() + "):");
        problemSetSizeTextPanel.update(problemSetSizeTextPanel.getGraphics());
        createNewProblemSetTab.update(createNewProblemSetTab.getGraphics());
    }

    //MODIFIES: problemSetUi, problemSetUi subjects, problemSetUi hiraganaRows, problemSetUi.displayType
    //EFFECTS: resets problem set to default settings:
    //problemSetUi.displayType = "japanese";
    //empties problemSetUi problemSet List
    //empties problemSetUi availableProblems
    //enables all subjects and hiragana rows
    private void fullReset() {
        activeProblemSet.displayType = "japanese";
        activeProblemSet.problemSet.clear();
        activeProblemSet.availableProblems.clear();

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

    //EFFECTS: creates the text field in which the user inputs the size of their problem set before hitting create
    private void initiateProblemSetSizeTextField() {

        problemSetSize = new JTextField(activeProblemSet.availableProblems.size());
        problemSetSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = problemSetSize.getText();
                problemSize = Integer.parseInt(text);

            }
        });
    }


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

        initializeHiraganaSet1ButtonFunctionality();
        initializeHiraganaSet2ButtonFunctionality();
        initializeVocabFamilySetButtonFunctionality();
        initializeDisplayTypeButtonFunctionality();
        initializeDisplayTypeButtonFunctionality();
    }

    public void initializeHiraganaSet1ButtonFunctionality() {
        hiraganaSet1True.setSelected(true);
        hiraganaSet1True.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleHiraganaOneToEight(true);

                updateMaxProblemSetSize();

            }
        });
        hiraganaSet1False.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleHiraganaOneToEight(false);

                updateMaxProblemSetSize();

            }
        });
    }

    public void initializeHiraganaSet2ButtonFunctionality() {
        hiraganaSet2True.setSelected(true);
        hiraganaSet2True.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleHiraganaNineToSixteen(true);

                updateMaxProblemSetSize();

            }
        });
        hiraganaSet2False.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleHiraganaNineToSixteen(false);

                updateMaxProblemSetSize();

            }
        });
    }

    public void initializeVocabFamilySetButtonFunctionality() {
        vocabFamilyTrue.setSelected(true);
        vocabFamilyTrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeProblemSet.vocabFamilySet = true;

                updateMaxProblemSetSize();

            }
        });
        vocabFamilyFalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeProblemSet.vocabFamilySet = false;

                updateMaxProblemSetSize();

            }
        });
    }

    public void initializeCreateProblemSetButtonFunctionality() {
        createProblemSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (problemSize <= activeProblemSet.availableProblems.size()) {
                    String text = problemSetSize.getText();
                    problemSize = Integer.parseInt(text);
                    activeProblemSet.generateProblemSet(problemSize);
                    problemSetTableField.fillTable();
                }
            }
        });


    }

    public void initializeDisplayTypeButtonFunctionality() {
        displayTypeJapanese.setSelected(true);
        displayTypeJapanese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeProblemSet.displayType = "japanese";
            }
        });
        displayTypeEnglish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeProblemSet.displayType = "english";
            }
        });
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


    //EFFECTS: Initializes the table that will display each problem in the active data set.
    //DON'T FORGET TO CALL problemSetTable.fillTable(); everytime you alter the active data set in some way (!!!)
    //e.g.) Call this method after loading and creating a new problemSet.
    private void initializeActiveProblemSetTable() {

        ProblemSetTable problemSetTable = new ProblemSetTable(this);


        this.problemSetTableField = problemSetTable;
        activeProblemSetTable = problemSetTable.getTable();
        activeProblemSetTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        activeProblemSetTable.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        scrollPane = new JScrollPane(activeProblemSetTable);

        //Add the scroll pane to this panel.
        add(scrollPane);

    }

    private void initializeMainMenuPanel() {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(5, 1));

        mainMenuPanel.add(titleTextBox);
        //Add buttons to experiment with Grid Layout
        mainMenuPanel.add(loadProblemSetButton);
        mainMenuPanel.add(saveProblemSetButton);
    }

    private void initializeButtons() {
        //creating menu buttons
        loadProblemSetButton = makeButton("Load saved problem set");
        initializeLoadButtonFunctionality();
        saveProblemSetButton = makeButton("Save active problem set");
        initializeSaveButtonFunctionality();
    }


    //CITATION: Based off of loadWorkRoom method in WorkRoomApp class of JsonSerializationDemo
    //MODIFIES: this
    //EFFECTS: loads saved problem set when action is triggered
    private void initializeLoadButtonFunctionality() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        loadProblemSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    activeProblemSet = jsonReader.read();

                    problemSetTableField.fillTable();
                    System.out.println("Loaded problem set from " + JSON_STORE + ".");
                } catch (IOException exception) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        });
    }

    //CITATION: Based off of saveWorkRoom method in WorkRoomApp class of JsonSerializationDemo
    //EFFECTS: loads saved problem set when action is triggered
    private void initializeSaveButtonFunctionality() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        saveProblemSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(activeProblemSet);
                    jsonWriter.close();
                    System.out.println("Saved problem set to " + JSON_STORE + ".");
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    protected JButton makeButton(String text) {

        //creating play active problem set button and adding it to tab1
        final JButton button = new JButton(text);

        return button;

    }

    protected JRadioButton makeRadioButton(String text) {

        //creating play active problem set button and adding it to tab1
        final JRadioButton button = new JRadioButton(text);

        return button;

    }


    //Removing the tab icons for now. May add later.

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Gui.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            //System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
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
    public ProblemSet getActiveProblemSet() {
        return this.activeProblemSet;
    }
}
