package controller;

import model.Etudiant;
import model.Promotion;

import java.util.ArrayList;

public class RemoveFromFormController extends AbstractController {

    public RemoveFromFormController(Promotion promotion) {
        super(promotion);
    }

    @Override
    public void control(ArrayList<String> data) {
        for (String id : data) {
            Etudiant etudiant = promotion.rechercheEtudiant(id);
            if (etudiant != null)
                promotion.supressionEtudiant(etudiant);
        }
    }
}
