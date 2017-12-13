package data.composite;

import java.util.ArrayList;

public class Unit extends Component {

    private ArrayList<Component> components;

    public Unit(int id, String name) {
        super(id, name);

        this.components = new ArrayList<>();
    }

    @Override
    public Double getAverageFor(int idStudent) {
        Double average = 0.0;

        for (Component component : components) {
            average += component.getAverageFor(idStudent);
        }

        return average / components.size();
    }

    @Override
    public Double getAverage() {
        Double average = 0.0;

        for (Component component : components) {
            average += component.getAverage();
        }

        return average / components.size();
    }

    @Override
    public Double getCoefficient() {
        Double average = 0.0;

        for (Component component : components) {
            average += component.getCoefficient();
        }

        return average / components.size();
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void addComponent(Component component) {
        components.add(component);
    }


}
