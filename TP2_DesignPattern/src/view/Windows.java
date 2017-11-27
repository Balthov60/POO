package view;

import controller.NextButtonController;
import controller.SortSelectionController;
import data.AbstractSort;
import data.Data;
import data.SimpleBubbleSort;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Windows extends JFrame implements Observer {

    private JComboBox<String> sortList;
    private JButton next;
    private Chart chart;

    private final Data data;
    private AbstractSort sort;

    public Windows(Data data) {
        this.setTitle("Sort Chart");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.data = data;
        this.sort = new SimpleBubbleSort(data);

        initComponent();
        displayComponent();

        this.pack();
        this.setVisible(true);
    }

    private void initComponent() {
        sortList = new JComboBox<>(AbstractSort.SORT_TYPES);
        next = new JButton("Next");
        chart = new Chart(data);

        next.addActionListener(new NextButtonController(sort));
        sortList.addActionListener(new SortSelectionController(sort));
    }
    private void displayComponent() {
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Global Constraints
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.gridheight = 1;

        // Specific Constraints
        constraints.gridx = 0;
        constraints.weighty = 1;
        this.getContentPane().add(next, constraints);

        constraints.gridy++;
        constraints.weighty = 5;
        this.getContentPane().add(chart, constraints);

        constraints.gridy++;
        constraints.weighty = 1;
        this.getContentPane().add(sortList, constraints);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.getContentPane().removeAll();

        chart = new Chart(data);
        this.displayComponent();

        this.pack();
    }
}
