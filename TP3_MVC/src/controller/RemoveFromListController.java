package controller;

import model.Promotion;

import java.util.ArrayList;

public class RemoveFromListController extends AbstractController {

    public RemoveFromListController(Promotion promotion) {
        super(promotion);
    }

    @Override
    public void control(ArrayList<String> data) {
        for(String entry : data) {
            promotion.supressionEtudiant(promotion.rechercheEtudiant(entry.split(" ")[0]));
        }
    }
}
