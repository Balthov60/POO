package model;

public class Etudiant {

    private String id;
    private String nom;
    private String prenom;
    private String departement;
    private String bac;

    public Etudiant(String id, String nom, String prenom, String departement, String bac) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.departement = departement;
        this.bac = bac;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDepartement() {
        return departement;
    }

    public String getBac() {
        return bac;
    }
}
