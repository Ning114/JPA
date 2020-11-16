package ui;

import model.ProblemSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static ui.ProjectApp.JSON_STORE;

public class Gui extends JPanel implements ActionListener {

    private JComponent titleTextBox;
    private JTabbedPane tabbedPane;
    private JPanel controls;

    private JPanel mainMenuPanel;
    private JTable activeProblemSetTable;
    public ProblemSetTable problemSetTableField;

    private JButton playProblemSetButton;
    private JButton createProblemSetButton;
    private JButton loadProblemSetButton;
    private JButton saveProblemSetButton;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private ProblemSet activeProblemSet;

    //The activeProblemSetTable gets put into the scrollPane so the table can scroll Poggers
    private JScrollPane scrollPane;

    @SuppressWarnings("checkstyle:MethodLength")
    public Gui() {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");

        //title of application
        titleTextBox = makeTextPanel("Welcome to the Japanese Hiragana + Katakana Practice application!");

        controls = new JPanel();
        controls.setLayout(new GridLayout(4, 1));


        initializeButtons();

        initializeMainMenuPanel();

        tabbedPane.addTab("Main Menu", icon, mainMenuPanel,
                "Main Menu");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


        //This will be the tab with the table of problems in the active problem set
        initializeActiveProblemSetTable();

        tabbedPane.addTab("Active Problem Set", icon, scrollPane,
                "Displays each problem in the current active problem set");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
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
        mainMenuPanel.add(playProblemSetButton);
        mainMenuPanel.add(createProblemSetButton);
        mainMenuPanel.add(loadProblemSetButton);
        mainMenuPanel.add(saveProblemSetButton);
    }

    private void initializeButtons() {
        //creating menu buttons
        playProblemSetButton = makeButton("Play active problem set");
        createProblemSetButton = makeButton("Create new problem set");
        loadProblemSetButton = makeButton("Load saved problem set");
        initializeLoadButtonFunctionality();
        saveProblemSetButton = makeButton("Save active problem set");
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
