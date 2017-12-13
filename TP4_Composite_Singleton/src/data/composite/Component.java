package data.composite;

public abstract class Component {

    protected int id;
    protected String name;

    protected Component(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract Double getAverageFor(int idStudent);
    public abstract Double getAverage();
    public abstract Double getCoefficient();

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
