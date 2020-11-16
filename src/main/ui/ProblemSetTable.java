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

//CLASS LEVEL COMMENT: created using help from this post:
//https://stackoverflow.com/questions/13833688/adding-jbutton-to-jtable
//and the componentsTableDemoProject from:
//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class ProblemSetTable extends AbstractTableModel {


    private Object[] columnNames = {"Japanese Problem",
            "English Problem",
            " "};

    private Object[][] data = {

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

    public ProblemSetTable(Gui gui) {

        this.gui = gui;

        problemSetTable = new DefaultTableModel();
        problemSetTable.setDataVector(data, columnNames);

        table = new JTable(problemSetTable);
        table.getColumn(" ").setCellRenderer(new ButtonRenderer());
        table.getColumn(" ").setCellEditor(new ButtonEditor(new JCheckBox()));


        JScrollPane scroll = new JScrollPane(table);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        //thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        //so buttons will fit and not be shown butto..


    }

    //EFFECTS: Updates the active problem set to reflect what it is in GUI.
    public void updateActiveProblemSet() {
        this.activeProblemSet = this.gui.getActiveProblemSet();
    }

    //EFFECTS: fills the table with each problem in the active ProblemSet.
    public void fillTable() {
        resetTable();
        updateActiveProblemSet();

        for (Problem p: activeProblemSet.problemSet) {
            String[] problemData = {p.japaneseProblem, p.englishProblem, "Delete"};

            problemSetTable.addRow(problemData);

        }


    }

    //MODIFIES: this.table
    //EFFECTS: Clears every row in the table.
    private void resetTable() {
        for (int i = 0; i < table.getRowCount() - 1; i++) {
            System.out.println(table.getRowCount());
            table.remove(i);
        }
    }


    public JTable getTable() {
        return this.table;
    }



    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }





//

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            selectedRow = row;

            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                //DON'T FORGET TO ENABLE THIS LATER. COMMENTING OUT FOR DEBUGGING PURPOSES. (!!!)
                activeProblemSet.problemSet.remove(getIndexOfProblemAtPosition());
                problemSetTable.removeRow(selectedRow);

            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    //When deleting a problem from the table, this method finds the corresponding problem inside the active problem set
    //and removes it.
    private int getIndexOfProblemAtPosition() {

        int index = 0;

        for (Problem p: activeProblemSet.problemSet) {
            if (p.japaneseProblem.equals(problemSetTable.getValueAt(selectedRow, 1))) {
                index = activeProblemSet.problemSet.indexOf(p);
                break;
            }
        }

        return index;
    }
}

