package display;


import dataHandling.Gender;
import dataHandling.Passenger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import java.util.HashMap;
import java.util.Map;

public class PieChart extends AbstractPanel {

    private DefaultPieDataset dataset;
    private JFreeChart chart;

    public PieChart() {
        super();

        initDataset();
        initChart();

        this.add(new ChartPanel(chart));
    }

    private void initDataset() {
        dataset = new DefaultPieDataset();
        Map<Gender, Integer> quantityForEachGender = getQuantityForEachGender();

        for(Gender gender : quantityForEachGender.keySet()) {
            dataset.setValue(gender, quantityForEachGender.get(gender));
        }
    }
    private Map<Gender, Integer> getQuantityForEachGender() {
        Map<Gender, Integer> quantityForEachGender = new HashMap<>();

        for (Passenger passenger : data.getPassengers()) {
            Gender gender = passenger.getGender();
            int count = quantityForEachGender.getOrDefault(gender, 0);
            quantityForEachGender.put(gender, count + 1);
        }

        return quantityForEachGender;
    }

    private void initChart() {
        chart = ChartFactory.createPieChart3D(
                "Gender Proportion",
                dataset,
                true,
                true,
                false
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
    }
}
