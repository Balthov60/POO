package view;

import data.Data;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

class Chart extends JPanel {

    private DefaultCategoryDataset dataset;
    private JFreeChart chart;

    Chart(Data data) {

        initDataset(data);
        initChart();

        this.add(new ChartPanel(chart));
    }

    private void initDataset(Data data) {
        dataset = new DefaultCategoryDataset();

        for (int i = 0; i < data.getSize(); i++) {
            dataset.addValue(data.get(i), "", "" + i);
        }
    }
    private void initChart() {
        chart = ChartFactory.createBarChart3D(
                "Sort",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
    }
}
