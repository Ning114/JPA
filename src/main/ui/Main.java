package ui;

import javax.swing.*;

import static ui.Gui.createAndShowGUI;

//CLASS LEVEL COMMENT:
//Main class is used for running ProjectApp class
public class Main {
    public static void main(String[] args) {
//        new ProjectApp();
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });

    }
}
