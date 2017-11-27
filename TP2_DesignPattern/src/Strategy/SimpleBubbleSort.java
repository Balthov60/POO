package Strategy;

public class SimpleBubbleSort extends AbstractSort {

    public SimpleBubbleSort(Data data) {
        super(data);
    }

    @Override
    public void next() {
        for (int i = 1; i < data.getSize(); i++) {
            if (data.get(i) < data.get(i - 1)) {
                data.swap(i, i - 1);
                return;
            }
        }
    }
}
