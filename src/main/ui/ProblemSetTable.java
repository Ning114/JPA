package ui;

import model.Problem;
import model.ProblemSet;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//CLASS LEVEL COMMENT: ProblemSetTable handles creating the functionality in our activeProblemSetTable, which displays
//every problem in the active problem set, and allows the user to delete any problem they want.

//CITATION: created using help from this post:
//https://stackoverflow.com/questions/13833688/adding-jbutton-to-jtable
//and the componentsTableDemoProject from:
//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class ProblemSetTable extends AbstractTableModel {


    private Object[] columnNames = {"Japanese Problem",
            "English Problem"};

    private String[][] data = {

            //DATA FOR TESTING PURPOSES (!!!)
//            {"おとうさん", "father", "Delete"},
//            {"おかあさん", "mother", "Delete"},
//            {"たのしい", "fun", "Delete"},
//            {"おもしろい", "interesting", "Delete"},
//            {"おもしろい", "interesting", "Delete"},
//            {"おもしろい", "interesting", "Delete"},

    };

    public ProblemSet activeProblemSet;

    private final JTable table;
    private DefaultTableModel problemSetTable;



    public int selectedRow;

    private Gui gui;


    //EFFECTS: Creates an empty problemSetTable and it's functionality. Takes a GUI field because
    // it needs to access the activeProblemSet field.
    public ProblemSetTable(Gui gui) {

        this.gui = gui;



        problemSetTable = new DefaultTableModel(data, columnNames);

//        problemSetTable.setDataVector(data, columnNames);

        table = new JTable(problemSetTable);



        JScrollPane scroll = new JScrollPane(table);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        //thanks mKorbel +1
        //http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        //so buttons will fit and not be shown butto..

    }





    //MODIFIES: this
    //EFFECTS: Updates the active problem set to reflect what it is in GUI.
    public void updateActiveProblemSet() {
        this.activeProblemSet = this.gui.getActiveProblemSet();
    }

    //MODIFIES: this
    //EFFECTS: fills the table with each problem in the active ProblemSet.
    public void fillTable() {
        resetTable();



        updateActiveProblemSet();

        for (Problem p: activeProblemSet.problemSet) {
            String[] problemData = {p.japaneseProblem, p.englishProblem};

            problemSetTable.addRow(problemData);

        }


    }

    //MODIFIES: this.table
    //EFFECTS: Clears every row in the table.
    public void resetTable() {
        //I should be using a vector here since the row count changes as i ticks downwards/upwards... oh well
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            System.out.println("number of rows: " + table.getRowCount());
            System.out.println("row removed: " + i);
            problemSetTable.removeRow(i);

        }
    }


    //EFFECTS: returns this.table
    public JTable getTable() {
        return this.table;
    }



    @Override
    //EFFECTS: returns this.data.length
    public int getRowCount() {
        return data.length;
    }

    @Override
    //EFFECTS: returns this.columnNames.length
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    //EFFECTS: returns the value in the specified row and column
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }





}

