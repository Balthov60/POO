package view;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Promotion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

public class VueHistogrammeChart extends AbstractVue implements Observer {

    private Histogramme histo;


    public VueHistogrammeChart() {
        histo = new Histogramme();
        this.setContentPane(histo);
        this.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        histo = new Histogramme(((Promotion) o).getStudentQuantityForeachBac());

        this.getContentPane().removeAll();
        this.setContentPane(histo);
        this.pack();
    }


    // internal class
    public class Histogramme extends ChartPanel {

        public Histogramme() {
            super(null);
            this.setPreferredSize(new Dimension(285, 350));
            CategoryDataset dataset = createSampleDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            this.setChart(chart);
        }
        public Histogramme(HashMap<String, Integer> data) {
            super(null);
            this.setPreferredSize(new Dimension(285, 350));
            CategoryDataset dataset = createDataset(data);
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            this.setChart(chart);
        }

        private CategoryDataset createSampleDataset() {
            final double[][] data = new double[][]{{10.0, 4.0, 15.0, 14.0}, {}};
            return DatasetUtilities.createCategoryDataset("Series ", "Category ", data);
        }
        private CategoryDataset createDataset(HashMap<String, Integer> data) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for(String bac : data.keySet()) {
                dataset.setValue(data.get(bac), bac, bac);
            }

            return dataset;
        }

        private JFreeChart createChart(final CategoryDataset dataset) {
            final JFreeChart chart = ChartFactory.createBarChart3D(
                    "3D Bar Chart Demo", // chart title
                    "Category", // domain axis label
                    "Value", // range axis label
                    dataset, // model
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips
                    false // urls
            );

            final CategoryPlot plot = chart.getCategoryPlot();
            final CategoryAxis axis = plot.getDomainAxis();
            axis.setCategoryLabelPositions(
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
            );
            final BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
            renderer.setDrawBarOutline(false);

            return chart;

        }

    }

}
