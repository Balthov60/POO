import data.Data;
import view.Windows;

public class Main {

    public static void main(String[] args) {
        Data data = new Data(10);
        data.addObserver(new Windows(data));
    }
}
