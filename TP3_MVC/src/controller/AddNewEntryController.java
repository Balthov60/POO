package controller;

import model.Etudiant;
import model.Promotion;
import view.MainWindow;

import java.util.ArrayList;

public class AddNewEntryController extends AbstractController {

    private final MainWindow mainWindow;

    public AddNewEntryController(Promotion promotion, MainWindow mainWindow) {
        super(promotion);
        this.mainWindow = mainWindow;
    }

    @Override
    public void control(ArrayList<String> data) {
        for (int i = 0; i < 5; i++) {
            if (data.get(i).isEmpty()) {
                mainWindow.displayError("Des champs sont vides");
                return;
            }
        }

        if (promotion.rechercheEtudiant(data.get(0)) != null) {
            mainWindow.displayError("Des champs sont vides");
            return;
        }

        promotion.ajoutEtudiant(new Etudiant(
                data.get(0),
                data.get(1),
                data.get(2),
                data.get(3),
                data.get(4)
        ));
    }
}
