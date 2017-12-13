package data;

public class Mark {

    private int idStudent;
    private int idSubject;
    private Double value;

    public Mark(int idSubject, int idStudent, Double value) {
        this.idStudent = idStudent;
        this.idSubject = idSubject;
        this.value = value;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public Double getValue() {
        return value;
    }
}
