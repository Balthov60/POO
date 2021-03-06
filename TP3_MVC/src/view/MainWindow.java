package view;

import controller.AddNewEntryController;
import controller.RemoveFromFormController;
import controller.RemoveFromListController;
import javafx.scene.control.Alert;
import model.Promotion;

import javax.swing.*;

public class MainWindow extends JFrame{

    private final Promotion promotion;

    private final VueFormulaire form;
    private final VueCamembertChart camemb;
    private final VueListe liste;
    private final VueHistogrammeChart histo;
    
    public MainWindow(Promotion promotion) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new JDesktopPane());

        this.promotion = promotion;
        
        //vue formulaire
        form=new VueFormulaire(new RemoveFromFormController(promotion, this),
                               new AddNewEntryController(promotion, this));
        this.add(form);
        form.setTitle("Saisie d'étudiants");
        form.setVisible(true);
        form.setLocation(0, 0);
        
        //vue camembert
        camemb=new VueCamembertChart();
        this.add(camemb);
        camemb.setTitle("Départements d'origine");
        camemb.setVisible(true);
        camemb.setLocation(0, form.getHeight());
        promotion.addObserver(camemb);
        
        //vue histogramme
        histo=new VueHistogrammeChart();
        this.add(histo);
        histo.setTitle("Bacs d'origine");
        histo.setVisible(true);
        histo.setLocation(camemb.getWidth(), form.getHeight());
        promotion.addObserver(histo);

        //vue liste
        liste=new VueListe(new RemoveFromListController(promotion));
        this.add(liste);
        liste.setTitle("Liste des étudiants");
        liste.setLocation(camemb.getWidth()+histo.getWidth(), 0);
        liste.setVisible(true);
        promotion.addObserver(liste);
        
         //taille de la fenetre
        this.setSize(camemb.getWidth()+histo.getWidth()+liste.getWidth(), form.getHeight()+camemb.getHeight()+55);

    }

    public void displayError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
}
