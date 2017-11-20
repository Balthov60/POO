package display;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

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
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
            panel = new PieChart();
            this.getContentPane().removeAll();
            this.getContentPane().add(panel);
            this.pack();
        }
        else if (e.getSource() == barChart) {
            panel = new BarChart();
            this.getContentPane().removeAll();
            this.getContentPane().add(panel);
            this.pack();
        }
        else if (e.getSource() == multibarChart) {
            panel = new MultibarChart();
            this.getContentPane().removeAll();
            this.getContentPane().add(panel);
            this.pack();
        }
    }
}
