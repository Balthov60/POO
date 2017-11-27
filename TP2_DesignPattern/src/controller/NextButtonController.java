package controller;

import data.AbstractSort;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextButtonController implements ActionListener {

    private final AbstractSort sort;

    public NextButtonController(AbstractSort sort) {
        this.sort = sort;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sort.next();
    }
}
