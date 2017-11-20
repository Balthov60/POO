package display;

import dataHandling.Ages;
import dataHandling.Passenger;
import dataHandling.TravelingClass;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MultibarChart extends AbstractPanel {

    private static int CHART_COUNT = 4;

    private DefaultCategoryDataset[] datasets;
    private JFreeChart[] charts = new JFreeChart[CHART_COUNT];
    private ChartPanel[] panels = new ChartPanel[CHART_COUNT];

    public MultibarChart() {
        super();

        initDatasets();
        initChart();

        for (int i = 0; i < CHART_COUNT; i++) {
            panels[i] = new ChartPanel(charts[i]);
        }

        this.setLayout(new GridLayout(2, 2));

        CategoryPlot plot1 = (CategoryPlot) this.charts[1].getPlot();
        CategoryPlot plot2 = (CategoryPlot) this.charts[2].getPlot();
        CategoryPlot plot3 = (CategoryPlot) this.charts[3].getPlot();
        plot1.getRangeAxis().setInverted(true);
        plot3.getRangeAxis().setInverted(true);
        plot2.setOrientation(PlotOrientation.HORIZONTAL);
        plot3.setOrientation(PlotOrientation.HORIZONTAL);

        add(this.panels[0]);
        add(this.panels[1]);
        add(this.panels[2]);
        add(this.panels[3]);
        setPreferredSize(new Dimension(800, 600));
    }

    private void initDatasets() {
        datasets = new DefaultCategoryDataset[CHART_COUNT];
        for (int i = 0; i < CHART_COUNT; i++) {
            datasets[i] = new DefaultCategoryDataset();
        }

        // Traveler by class dataset

        Map<TravelingClass, Integer> quantityForEachTravelingClasses = getQuantityForEachTravelingClasses();
        for(TravelingClass travelingClass : quantityForEachTravelingClasses.keySet()) {
            datasets[0].setValue(quantityForEachTravelingClasses.get(travelingClass), travelingClass, travelingClass);
        }

        // survivor by class dataset

        Map<TravelingClass, Integer> survivorQuantityForEachTravelingClasses = getSurvivorQuantityForEachTravelingClasses();
        for(TravelingClass travelingClass : survivorQuantityForEachTravelingClasses.keySet()) {
            datasets[1].setValue(survivorQuantityForEachTravelingClasses.get(travelingClass), travelingClass, travelingClass);
        }

        // Traveler by age dataset

        Map<Ages, Integer> quantityForEachAges = getQuantityForEachAges();
        for(Ages age : quantityForEachAges.keySet()) {
            datasets[2].setValue(quantityForEachAges.get(age), age, age);
        }

        // survivor by age dataset

        Map<Ages, Integer> survivorQuantityForEachAges = getSurvivorQuantityForEachAges();
        for(Ages age : survivorQuantityForEachAges.keySet()) {
            datasets[3].setValue(survivorQuantityForEachAges.get(age), age, age);
        }
    }
    private Map<TravelingClass, Integer> getQuantityForEachTravelingClasses() {
        Map<TravelingClass, Integer> quantityForEachTravelingClasses = new HashMap<>();

        for (Passenger passenger : data.getPassengers()) {
            TravelingClass travelingClass = passenger.getTravelingClass();
            int count = quantityForEachTravelingClasses.getOrDefault(travelingClass, 0);
            quantityForEachTravelingClasses.put(travelingClass, count + 1);
        }

        return quantityForEachTravelingClasses;
    }
    private Map<TravelingClass, Integer> getSurvivorQuantityForEachTravelingClasses() {
        Map<TravelingClass, Integer> quantityForEachTravelingClasses = new HashMap<>();

        for (Passenger passenger : data.getPassengers()) {
            if (passenger.isSurvivor()) {
                System.out.println("survivor");
                TravelingClass travelingClass = passenger.getTravelingClass();
                int count = quantityForEachTravelingClasses.getOrDefault(travelingClass, 0);
                quantityForEachTravelingClasses.put(travelingClass, count + 1);
            }
        }

        return quantityForEachTravelingClasses;
    }
    private Map<Ages, Integer> getQuantityForEachAges() {
        Map<Ages, Integer> quantityForEachTravelingClasses = new HashMap<>();

        for (Passenger passenger : data.getPassengers()) {
            Ages age = passenger.getAge();
            int count = quantityForEachTravelingClasses.getOrDefault(age, 0);
            quantityForEachTravelingClasses.put(age, count + 1);
        }

        return quantityForEachTravelingClasses;
    }
    private Map<Ages, Integer> getSurvivorQuantityForEachAges() {
        Map<Ages, Integer> quantityForEachTravelingClasses = new HashMap<>();

        for (Passenger passenger : data.getPassengers()) {
            if (passenger.isSurvivor()) {
                Ages age = passenger.getAge();
                int count = quantityForEachTravelingClasses.getOrDefault(age, 0);
                quantityForEachTravelingClasses.put(age, count + 1);
            }
        }

        return quantityForEachTravelingClasses;
    }

    private void initChart() {
        // Traveler By Class Chart

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

        CategoryPlot plot = (CategoryPlot) charts[0].getPlot();
        plot.getDomainAxis().setMaximumCategoryLabelLines(2);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Survivor By Class Chart

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

        plot = (CategoryPlot) charts[1].getPlot();
        plot.getDomainAxis().setMaximumCategoryLabelLines(2);

        rangeAxis = plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Traveler By Age Chart

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

        plot = (CategoryPlot) charts[2].getPlot();
        plot.getDomainAxis().setMaximumCategoryLabelLines(2);

        rangeAxis = plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // survivor by age Chart

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

        plot = (CategoryPlot) charts[3].getPlot();
        plot.getDomainAxis().setMaximumCategoryLabelLines(2);

        rangeAxis = plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    }
}
