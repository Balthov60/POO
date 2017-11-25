package display;

import dataHandling.Data;
import dataHandling.TravelingClass;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Map;

public class Bar extends AbstractChartPanel {

    private DefaultCategoryDataset dataset;
    private JFreeChart chart;

    public Bar(Data data) {
        super(data);

        initDataset();
        initChart();

        this.add(new ChartPanel(chart));
    }

    protected void initDataset() {
        dataset = new DefaultCategoryDataset();
        Map<TravelingClass, Integer> quantityForEachTravelingClasses = data.getQuantityForEachTravelingClasses();

        for(TravelingClass travelingClass : quantityForEachTravelingClasses.keySet()) {
            dataset.setValue(quantityForEachTravelingClasses.get(travelingClass), travelingClass, travelingClass);
        }
    }
    protected void initChart() {
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
    }
}
