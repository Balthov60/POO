package controller;

import model.Promotion;

import java.util.ArrayList;

public abstract class AbstractController {

    protected final Promotion promotion;

    public AbstractController(Promotion promotion) {
        this.promotion = promotion;
    }

    public abstract void control(ArrayList<String> data);
}
