package display;

import dataHandling.Data;

import javax.swing.*;

public class AbstractPanel extends JPanel {

    protected Data data = new Data();

    public AbstractPanel() {
        data.importPassengersFromFile("res/titanic.dbf");
    }
}
