package view;

import controller.AddNewEntryController;
import controller.RemoveFromFormController;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VueFormulaire extends AbstractVue implements ActionListener {

    private final JTextField txtNumeroSuppr = new JTextField(10);
    private final JTextField txtNumeroAjout = new JTextField(10);
    private final JTextField txtNom = new JTextField(10);
    private final JTextField txtPrenom = new JTextField(10);
    private final JComboBox boxBac = new JComboBox();
    private final JComboBox boxDpt = new JComboBox();
    private final JLabel lblNumeroAjout = new JLabel("Numero:");
    private final JLabel lblNumeroSuppr = new JLabel("Numero:");
    private final JLabel lblNom = new JLabel("Nom:");
    private final JLabel lblPrenom = new JLabel("Prénom:");
    private final JLabel lblBac = new JLabel("Bac:");
    private final JLabel lblDpt = new JLabel("Dpt:");
    private final JLabel lblPartieAjout = new JLabel("Ajout d'un étudiant");
    private final JLabel lblPartieSuppr = new JLabel("Suppression d'un étudiant:");
    private final JButton btAjout = new JButton("Ajout");
    private final JButton btSuppr = new JButton("Supprimer");

    RemoveFromFormController removeFromFormController;
    AddNewEntryController addNewEntryController;
    
    public VueFormulaire(RemoveFromFormController removeFromFormController, AddNewEntryController addNewEntryController) {
        this.removeFromFormController = removeFromFormController;
        this.addNewEntryController = addNewEntryController;

        //remplissage des box
        boxDpt.addItem("- - -");
        for (int i = 1; i < 96; i++) {
            if (i != 20) {
                if (i < 10) {
                    boxDpt.addItem("0" + i);
                } else {
                    boxDpt.addItem("" + i);
                }
            } else {
                boxDpt.addItem("2A");
                boxDpt.addItem("2B");
            }
        }
        for (int i = 971; i < 977; i++) {
            boxDpt.addItem("" + i);
        }
        boxDpt.addItem("Autre");
        //seconde box
        boxBac.addItem("- - -");
        boxBac.addItem("S");
        boxBac.addItem("STI");
        boxBac.addItem("ES");
        boxBac.addItem("STG");
        boxBac.addItem("Etranger");
        boxBac.addItem("Autre");

        //placement des objets
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        this.add(lblPartieAjout, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        this.add(lblNumeroAjout, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        this.add(txtNumeroAjout, gc);
        gc.gridx = 2;
        gc.gridy = 1;
        this.add(lblPrenom, gc);
        gc.gridx = 3;
        gc.gridy = 1;
        this.add(txtPrenom, gc);
        gc.gridx = 4;
        gc.gridy = 1;
        this.add(lblNom, gc);
        gc.gridx = 5;
        gc.gridy = 1;
        this.add(txtNom, gc);
        gc.gridx = 6;
        gc.gridy = 1;
        this.add(lblBac, gc);
        gc.gridx = 7;
        gc.gridy = 1;
        this.add(boxBac, gc);
        gc.gridx = 8;
        gc.gridy = 1;
        this.add(lblDpt, gc);
        gc.gridx = 9;
        gc.gridy = 1;
        this.add(boxDpt, gc);
        gc.gridx = 10;
        gc.gridy = 2;
        this.add(btAjout, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 11;
        //this.add(lblSeparation, gc);
        this.add(new JSeparator(SwingConstants.HORIZONTAL), gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        this.add(lblPartieSuppr, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 1;
        this.add(lblNumeroSuppr, gc);
        gc.gridx = 1;
        gc.gridy = 5;
        this.add(txtNumeroSuppr, gc);
        gc.gridx = 10;
        gc.gridy = 6;
        this.add(btSuppr, gc);
        this.pack();

        btSuppr.addActionListener(this);
        btAjout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> data = new ArrayList<>();

        if (e.getSource() == btSuppr) {
            data.add(txtNumeroSuppr.getText());
            removeFromFormController.control(data);
        }
        else if (e.getSource() == btAjout) {
            data.add(txtNumeroAjout.getText());
            data.add(txtNom.getText());
            data.add(txtPrenom.getText());
            data.add(boxDpt.getSelectedItem().toString());
            data.add(boxBac.getSelectedItem().toString());

            addNewEntryController.control(data);
        }
    }
}
