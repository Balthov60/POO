package data.composite;

import data.Mark;

import java.util.ArrayList;

public class Subject extends Component {

    private Double coefficient;
    private ArrayList<Mark> marks;

    public Subject(int id, String name, Double coefficient) {
        super(id, name);

        this.coefficient = coefficient;
        this.marks = new ArrayList<>();
    }

    @Override
    public Double getAverageFor(int idStudent) {

        for (Mark mark : marks) {
            if (mark.getIdStudent() == idStudent)
                return mark.getValue();
        }
        return 0.0;
    }

    @Override
    public Double getAverage() {
        Double average = 0.0;

        for (Mark mark : marks) {
            average += mark.getValue();
        }
        return average / marks.size();
    }

    @Override
    public Double getCoefficient() {
        return coefficient;
    }

    public void addMark(Mark mark) {
        marks.add(mark);
    }
}
