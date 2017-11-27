package data;

import java.util.Observable;


public class Data extends Observable {

    private final int size;
    private int[] data;

    public Data(int size) {
        this.size = size;
        reset();
    }

    public void reset() {
        data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (int) (Math.random() * 100);
        }

        setChangedAndNotifyObservers();
    }
    void swap(int firstIndex, int secondIndex) {
        if (firstIndex < 0 || firstIndex >= size ||secondIndex < 0 || secondIndex >= size)
            return;

        int temp = data[firstIndex];
        data[firstIndex] = data[secondIndex];
        data[secondIndex] = temp;

        setChangedAndNotifyObservers();
    }

    public int getSize() {
        return size;
    }
    public int get(int index) {
        if (index < 0 || index >= size)
            return 0;

        return data[index];
    }


    private void setChangedAndNotifyObservers() {
        setChanged();
        notifyObservers();
    }
}
