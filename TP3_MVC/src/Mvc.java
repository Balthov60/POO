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

        MainWindow fen=new MainWindow(promotion);

        fen.setVisible(true);

        promotion.loadFileCSV("data/promoDUT.csv");
    }
    
}
