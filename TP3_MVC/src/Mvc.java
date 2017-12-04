import javax.swing.SwingUtilities;
import static javax.swing.SwingUtilities.updateComponentTreeUI;
import javax.swing.UIManager;

import model.Etudiant;
import model.Promotion;
import view.MainWindow;
import view.VueCamembertChart;


public class Mvc {


    public static void main(String[] args) {
        Promotion promotion = new Promotion();
        promotion.loadFileCSV("data/promoDUT.csv");

        MainWindow fen=new MainWindow(promotion);

        promotion.ajoutEtudiant(new Etudiant("test", "test", "test", "test", "test"));

        fen.setVisible(true);
    }
    
}
