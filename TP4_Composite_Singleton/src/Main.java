import data.Mark;
import data.Student;
import data.composite.Component;
import data.composite.Subject;
import data.composite.Unit;
import database.DBServices;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Student> students;
        ArrayList<Mark> marks;
        ArrayList<Component> components;

        try
        {
            students = getStudentFromDB();
            marks = getMarksFromDB();
            components = getComponentsFromDB();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return;
        }

        includeMarksInComponents(marks, components);

        System.out.println();
        System.out.println("STUDENT");
        System.out.println();

        for (Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName() + " ");
            for (Component parent : components) {
                for (Component unit : ((Unit) parent).getComponents()) {
                    for (Component module : ((Unit) unit).getComponents()) {
                        System.out.println("   " + module.getName() + " : " + module.getAverageFor(student.getId()));
                    }
                }
            }
        }

        System.out.println();
        System.out.println("MODULES");
        System.out.println();

        for (Component parent : components) {
            for (Component unit : ((Unit) parent).getComponents()) {
                for (Component module : ((Unit) unit).getComponents()) {
                    System.out.println("   " + module.getName() + " : " + module.getAverage() + " " + module.getCoefficient());
                }
            }
        }
    }

    private static ArrayList<Student> getStudentFromDB() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = DBServices.getConnection();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM etudiants");

        while(result.next()) {
            students.add(new Student(result.getInt(1),
                                     result.getString(2),
                                     result.getString(3))
            );
        }

        return students;
    }
    private static ArrayList<Mark> getMarksFromDB() throws SQLException {
        ArrayList<Mark> marks = new ArrayList<>();
        Connection connection = DBServices.getConnection();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM notes");

        while(result.next()) {
            marks.add(new Mark(result.getInt(1),
                               result.getInt(2),
                               result.getDouble(3))
            );
        }

        return marks;
    }

    // Improve with Order BY.
    private static ArrayList<Component> getComponentsFromDB() throws SQLException {
        ArrayList<Component> components = new ArrayList<>();
        Connection connection = DBServices.getConnection();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM modules");

        Component component;
        int idParent;
        while(result.next()) {
            // Create Component
            if (result.getDouble(4) == 0)
            {
                component = new Unit(result.getInt(1), result.getString(3));
            }
            else
            {
                component = new Subject(result.getInt(1),
                                      result.getString(3),
                                      result.getDouble(4)
                );
            }

            // Place Component in tree
            idParent = result.getInt(2);

            if (component instanceof Unit) {
                if (idParent == 0) {
                    components.add(component);
                }
                else if (component.getName().charAt(0) == 'U') { // If Component is "unit"
                    for (Component parent : components) {
                        if (parent.getId() == idParent) {
                            ((Unit) parent).addComponent(component);
                            break;
                        }
                    }
                }
                else {  // If Component is "module"
                    for (Component parent : components) {
                        for (Component unit : ((Unit) parent).getComponents()) {
                            if (unit.getId() == idParent) {
                                ((Unit) unit).addComponent(component);
                                break;
                            }
                        }
                    }
                }
            }
            else { // If Component is a Subject
                for (Component parent : components) {
                    for (Component unit : ((Unit) parent).getComponents()) {
                        for (Component module : ((Unit) unit).getComponents()) {
                            if (module.getId() == idParent) {
                                ((Unit) module).addComponent(component);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return components;
    }

    private static void includeMarksInComponents(ArrayList<Mark> marks, ArrayList<Component> components) {
        for (Mark mark : marks) {
            includeMark(mark, components);
        }
    }
    private static void includeMark(final Mark mark, ArrayList<Component> components) {
        for (Component parent : components) {
            for (Component unit : ((Unit) parent).getComponents()) {
                for (Component module : ((Unit) unit).getComponents()) {
                    for (Component subject : ((Unit) module).getComponents()) {
                        if (mark.getIdSubject() == subject.getId()) {
                            ((Subject) subject).addMark(mark);
                            return;
                        }
                    }
                }
            }
        }
    }
}
