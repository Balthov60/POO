package display;

import dataHandling.Data;

import javax.swing.JPanel;


public abstract class AbstractChartPanel extends JPanel {

    protected Data data;

    public AbstractChartPanel(Data data) {
        this.data = data;
    }

    protected abstract void initDataset();
    protected abstract void initChart();
}
