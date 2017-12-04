package view;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Promotion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class VueCamembertChart extends AbstractVue implements Observer {

    private Camembert camemb;

    public VueCamembertChart() {
        camemb = new Camembert();
        this.setContentPane(camemb);
        this.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        camemb = new Camembert(((Promotion) o).getStudentQuantityForeachDepartment());

        this.getContentPane().removeAll();
        this.setContentPane(camemb);
        this.pack();
    }

    public class Camembert extends ChartPanel {

        public Camembert() {
            super(null);
            this.setPreferredSize(new Dimension(450, 350));
            final PieDataset dataset = createSampleDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            setContentPane(chartPanel);
            this.setChart(chart);
        }

        public Camembert(HashMap<String, Integer> data) {
            super(null);
            this.setPreferredSize(new Dimension(450, 350));
            final PieDataset dataset = createDataset(data);
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            setContentPane(chartPanel);
            this.setChart(chart);
        }

        private PieDataset createSampleDataset() {
            final DefaultPieDataset result = new DefaultPieDataset();
            result.setValue("Java", new Double(43.2));
            result.setValue("Visual Basic", new Double(10.0));
            result.setValue("C/C++", new Double(17.5));
            result.setValue("PHP", new Double(32.5));
            result.setValue("Perl", new Double(1.0));
            return result;
        }
        private PieDataset createDataset(HashMap<String, Integer> data) {
            final DefaultPieDataset dataset = new DefaultPieDataset();

            for(String departement : data.keySet()) {
                dataset.setValue(departement, data.get(departement));
            }

            return dataset;
        }

        private JFreeChart createChart(final PieDataset dataset) {
            final JFreeChart chart = ChartFactory.createPieChart3D(
                    "Pie Chart 3D Demo 1", // chart title
                    dataset, // model
                    true, // include legend
                    true,
                    false
            );
            final PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            plot.setForegroundAlpha(0.5f);
            plot.setNoDataMessage("No model to display");
            return chart;
        }
    }
}
