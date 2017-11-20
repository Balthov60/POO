import dataHandling.Data;
import display.Windows;

public class Main {


    public static void main(String[] args) {
        Data data = new Data();
        data.importPassengersFromFile("res/titanic.dbf");

        new Windows();

    }
}
