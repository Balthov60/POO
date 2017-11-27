package Strategy;

public abstract class AbstractSort {

    protected final Data data;

    public AbstractSort(Data data) {
        this.data = data;
    }

    public abstract void next();
}
