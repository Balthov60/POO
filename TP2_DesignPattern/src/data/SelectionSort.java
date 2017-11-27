package data;

public class SelectionSort extends AbstractSort {

    private int current;

    public SelectionSort(Data data) {
        super(data);
        current = 0;
    }

    @Override
    public void next() {
        if (current >= data.getSize()) {
            return;
        }

        int min = data.get(current);
        int minIndex = current;
        for (int i = current; i < data.getSize(); i++) {
            if (min > data.get(i)) {
                min = data.get(i);
                minIndex = i;
            }
        }

        data.swap(minIndex, current);
        current++;
    }
}
