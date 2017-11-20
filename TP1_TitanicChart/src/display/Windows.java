package display;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows extends JFrame implements ActionListener {

    private JMenuBar menuBar;

    private JMenuItem pieChart;
    private JMenuItem barChart;
    private JMenuItem multibarChart;

    private AbstractPanel panel;

    public Windows() {
        this.setTitle("Titanic Chart");
        this.setSize(400, 400);

        initComponent();
        setJMenuBar(menuBar);

        this.setVisible(true);
    }

    private void initComponent() {
        menuBar = new JMenuBar();
        JMenu display = new JMenu("Affichage");
        pieChart = new JMenuItem("Diagramme circulaire");
        barChart = new JMenuItem("Diagramme en barres");
        multibarChart = new JMenuItem("Diagramme multibarres");

        menuBar.add(display);
        display.add(pieChart);
        display.add(barChart);
        display.add(multibarChart);

        pieChart.addActionListener(this);
        barChart.addActionListener(this);
        multibarChart.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pieChart) {
            System.out.println("pieChart");
        }
        else if (e.getSource() == barChart) {
            System.out.println("barChart");
        }
        else if (e.getSource() == multibarChart) {
            System.out.println("multibarChart");
        }
    }
}
