package controller;


import data.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SortSelectionController implements ActionListener {

    private AbstractSort sort;

    public SortSelectionController(AbstractSort sort) {
        this.sort = sort;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox<String> comboBox = (JComboBox<String>) e.getSource();;
        String selected = comboBox.getSelectedItem().toString();

        if (Objects.equals(selected, AbstractSort.SORT_TYPES[0]))
        {
            sort = new SimpleBubbleSort(sort.getData());
        }
        else if (Objects.equals(selected, AbstractSort.SORT_TYPES[1]))
        {
            sort = new AdvancedBubbleSort(sort.getData());
        }
        else if (Objects.equals(selected, AbstractSort.SORT_TYPES[2]))
        {
            sort = new SelectionSort(sort.getData());
        }
        sort.getData().reset();
    }
}
