package controller;

import model.Etudiant;
import model.Promotion;
import view.MainWindow;
import view.VueFormulaire;

import java.util.ArrayList;

public class RemoveFromFormController extends AbstractController {

    private final MainWindow mainWindow;

    public RemoveFromFormController(Promotion promotion, MainWindow mainWindow) {
        super(promotion);
        this.mainWindow = mainWindow;
    }

    @Override
    public void control(ArrayList<String> data) {
        Etudiant etudiant = promotion.rechercheEtudiant(data.get(0));

        if (etudiant != null)
            promotion.supressionEtudiant(etudiant);
        else
            mainWindow.displayError("Etudiant inexistant");
    }
}
