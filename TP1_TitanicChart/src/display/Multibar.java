package display;

import dataHandling.Ages;
import dataHandling.Data;
import dataHandling.TravelingClass;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.Map;

public class Multibar extends AbstractChartPanel {

    private static final int CHART_COUNT = 4;

    private DefaultCategoryDataset[] datasets;
    private final JFreeChart[] charts = new JFreeChart[CHART_COUNT];

    public Multibar(Data data) {
        super(data);

        initDataset();
        initChart();

        this.setLayout(new GridLayout(2, 2));

        for (int i = 0; i < CHART_COUNT; i++) {
            add(new ChartPanel(charts[i]));
        }
    }

    protected void initDataset() {
        datasets = new DefaultCategoryDataset[CHART_COUNT];
        for (int i = 0; i < CHART_COUNT; i++) {
            datasets[i] = new DefaultCategoryDataset();
        }

        initTravelersDataset();
        initAgesDataset();
    }
    private void initTravelersDataset() {
        Map<TravelingClass, Integer> quantityForEachTravelingClasses = data.getQuantityForEachTravelingClasses();
        Map<TravelingClass, Integer> survivorsForEachTravelingClasses = data.getSurvivorQuantityForEachTravelingClasses();

        for(TravelingClass travelingClass : quantityForEachTravelingClasses.keySet()) {
            datasets[0].setValue(quantityForEachTravelingClasses.get(travelingClass), travelingClass, travelingClass);
            datasets[1].setValue(survivorsForEachTravelingClasses.get(travelingClass), travelingClass, travelingClass);
        }
    }
    private void initAgesDataset() {
        Map<Ages, Integer> quantityForEachAges = data.getQuantityForEachAges();
        Map<Ages, Integer> survivorQuantityForEachAges = data.getSurvivorQuantityForEachAges();

        for(Ages age : quantityForEachAges.keySet()) {
            datasets[2].setValue(quantityForEachAges.get(age), age, age);
            datasets[3].setValue(survivorQuantityForEachAges.get(age), age, age);
        }
    }

    protected void initChart() {
        // Traveler By Class ChartHandler
        charts[0] = ChartFactory.createStackedBarChart3D(
                "Traveler By Classes",
                "Category",
                "Value",
                datasets[0],
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        // Survivor By Class ChartHandler
        charts[1] = ChartFactory.createStackedBarChart3D(
                "Survivor By Classes",
                "Category",
                "Value",
                datasets[1],
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );


        // Traveler By Age ChartHandler
        charts[2] = ChartFactory.createStackedBarChart3D(
                "Traveler By Classes",
                "Category",
                "Value",
                datasets[2],
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );


        // survivor by age ChartHandler
        charts[3] = ChartFactory.createStackedBarChart3D(
                "Survivor By Ages",
                "Category",
                "Value",
                datasets[3],
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );
    }
}
