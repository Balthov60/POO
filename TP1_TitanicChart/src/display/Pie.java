package display;


import dataHandling.Data;
import dataHandling.Gender;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.Map;

public class Pie extends AbstractChartPanel {

    private DefaultPieDataset dataset;
    private JFreeChart chart;

    public Pie(Data data) {
        super(data);

        initDataset();
        initChart();

        this.add(new ChartPanel(chart));
    }

    protected void initDataset() {
        dataset = new DefaultPieDataset();
        Map<Gender, Integer> quantityForEachGender = data.getQuantityForEachGender();

        for(Gender gender : quantityForEachGender.keySet()) {
            dataset.setValue(gender, quantityForEachGender.get(gender));
        }
    }
    protected void initChart() {
        chart = ChartFactory.createPieChart3D(
                "Gender Proportion",
                dataset,
                true,
                true,
                false
        );
    }
}
