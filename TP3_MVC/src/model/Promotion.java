package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Observable;

public class Promotion extends Observable {
    private ArrayList<Etudiant> etudiants;

    public Promotion() {
        etudiants = new ArrayList<>();
    }

    public void ajoutEtudiant(Etudiant etudiant) {
        etudiants.add(etudiant);
        setChanged();
        notifyObservers();
    }
    public void supressionEtudiant(Etudiant etudiant) {
        if (etudiants.contains(etudiant))
            etudiants.remove(etudiant);

        setChanged();
        notifyObservers();
    }
    public Etudiant rechercheEtudiant(String id) {
        for (Etudiant etudiant : etudiants) {
            if (Objects.equals(etudiant.getId(), id))
                return etudiant;
        }
        return null;
    }

    public void loadFileCSV(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            while ((line = br.readLine()) != null)
            {
                createEtudiantFromLine(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createEtudiantFromLine(String line) {
        String[] data = line.split(";");

        this.ajoutEtudiant(new Etudiant(
                data[0],
                data[1],
                data[2],
                data[3],
                data[4]
        ));
    }

    public HashMap<String, Integer> getStudentQuantityForeachDepartment() {
        HashMap<String, Integer> studentQuantityForeachDepartment = new HashMap<>();
        String department;

        for (Etudiant etudiant : etudiants) {
            department = etudiant.getDepartement();

            int count = studentQuantityForeachDepartment.getOrDefault(department, 0);
            studentQuantityForeachDepartment.put(department, count + 1);
        }

        return studentQuantityForeachDepartment;
    }
    public HashMap<String, Integer> getStudentQuantityForeachBac() {
        HashMap<String, Integer> studentQuantityForeachDepartment = new HashMap<>();
        String bac;

        for (Etudiant etudiant : etudiants) {
            bac = etudiant.getBac();

            int count = studentQuantityForeachDepartment.getOrDefault(bac, 0);
            studentQuantityForeachDepartment.put(bac, count + 1);
        }

        return studentQuantityForeachDepartment;
    }
    public String[] getListOfStudent() {
        String[] list = new String[etudiants.size()];
        for (int i = 0; i < etudiants.size(); i++) {
            list[i] = formatForList(etudiants.get(i));
        }

        return list;
    }
    private String formatForList(Etudiant etudiant) {
        return etudiant.getId() + " " +
                etudiant.getPrenom() + " " + etudiant.getNom() +
                " (" + etudiant.getDepartement() + ")";
    }
}
