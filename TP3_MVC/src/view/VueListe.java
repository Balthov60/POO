package view;

import model.Promotion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class VueListe extends AbstractVue implements Observer {

    private final JList liste;
    private final JButton btSuppr = new JButton("Supprimer");

    private  String [] list = {"Louis Ferdinand Céline","Marcel Proust","JD Salinger","Apollinaire","Fédor Dostoievski","Victor Hugo","Balzac","André Gide","Rabelais","Arthur Rimbaud"};

    public VueListe() {
        liste = new JList();
        liste.setLayoutOrientation(JList.VERTICAL);
        liste.setVisibleRowCount(27);
        JScrollPane scrollPane = new JScrollPane(liste);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(scrollPane, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(btSuppr, gc);
        liste.setPreferredSize(new Dimension (150,500));
        remplissageListe();
        this.pack();
    }

    private void remplissageListe() {
        liste.removeAll();
        liste.setListData(list);
        this.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        list = ((Promotion) o).getListOfStudent();
        remplissageListe();
    }
}
