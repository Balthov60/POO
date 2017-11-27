import Strategy.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Windows extends JFrame implements ActionListener {

    private JComboBox<String> sortList;
    private String[] sortTypes;
    private JButton next;
    private Chart chart;

    private final Data data;
    private AbstractSort sort;

    private Windows(Data data) {
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
        sortTypes = new String[] {"Simple Bubble Sort", "Advanced Bubble Sort", "Selection Sort"};
        sortList = new JComboBox<>(sortTypes);
        next = new JButton("Next");
        chart = new Chart(data);

        next.addActionListener(this);
        sortList.addActionListener(this);
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
    private void refresh() {
        this.getContentPane().removeAll();

        chart = new Chart(data);
        this.displayComponent();

        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            sort.next();
            refresh();
        }
        else if (e.getSource() == sortList) {
            String selected = sortList.getSelectedItem().toString();

            if (Objects.equals(selected, sortTypes[0])) {
                sort = new SimpleBubbleSort(data);
            }
            else if (Objects.equals(selected, sortTypes[1])) {
                sort = new AdvancedBubbleSort(data);
            }
            else if (Objects.equals(selected, sortTypes[2])) {
                sort = new SelectionSort(data);
            }

            data.reset();
            this.refresh();
        }
    }



    public static void main(String[] args) {
        Data data = new Data(10);
        new Windows(data);
    }
}
