package controller;

import model.Etudiant;
import model.Promotion;

import java.util.ArrayList;

public class AddNewEntryController extends AbstractController {
    public AddNewEntryController(Promotion promotion) {
        super(promotion);
    }

    @Override
    public void control(ArrayList<String> data) {
        for (int i = 0; i < 5; i++) {
            if (data.get(i).isEmpty())
                return;
        }

        if (promotion.rechercheEtudiant(data.get(0)) != null)
            return;

        promotion.ajoutEtudiant(new Etudiant(
                data.get(0),
                data.get(1),
                data.get(2),
                data.get(3),
                data.get(4)
        ));
    }
}
