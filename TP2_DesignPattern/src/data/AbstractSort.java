package data;

public abstract class AbstractSort {

    final Data data;
    public static final String[] SORT_TYPES = {"Simple Bubble Sort", "Advanced Bubble Sort", "Selection Sort"};

    AbstractSort(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }
    public abstract void next();
}
