package display;

import dataHandling.Passenger;
import dataHandling.TravelingClass;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.HashMap;
import java.util.Map;

public class BarChart extends AbstractPanel {

    private DefaultCategoryDataset dataset;
    private JFreeChart chart;

    public BarChart() {
        super();

        initDataset();
        initChart();

        this.add(new ChartPanel(chart));
    }

    private void initDataset() {
        dataset = new DefaultCategoryDataset();

        Map<TravelingClass, Integer> quantityForEachTravelingClasses = getQuantityForEachTravelingClasses();

        for(TravelingClass travelingClass : quantityForEachTravelingClasses.keySet()) {
            dataset.setValue(quantityForEachTravelingClasses.get(travelingClass), travelingClass, travelingClass);
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

    private void initChart() {
        chart = ChartFactory.createBarChart3D(
                "Passenger Classes",
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final CategoryPlot plot = chart.getCategoryPlot();
        final CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
        );
        final BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
        renderer.setDrawBarOutline(false);
    }
}
