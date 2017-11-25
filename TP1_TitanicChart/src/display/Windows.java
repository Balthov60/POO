package display;

import dataHandling.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenuItem pieChart;
    private JMenuItem barChart;
    private JMenuItem multibarChart;

    private Data data;

    public Windows(Data data) {
        this.setTitle("Titanic ChartHandler");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.data = data;

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
            displayChart(new Pie(data));
        }
        else if (e.getSource() == barChart) {
            displayChart(new Bar(data));
        }
        else if (e.getSource() == multibarChart) {
            displayChart(new Multibar(data));
        }
    }

    private void displayChart(AbstractChartPanel chart) {
        this.getContentPane().removeAll();
        this.getContentPane().add(chart);
        this.pack();
    }
}
