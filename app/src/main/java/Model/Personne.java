package Model;

import java.io.Serializable;

public class Personne implements Serializable {
    private String nom;
    private String prenom;
    private String address;
    private String profil;
    private int id;

    public Personne(String nom, String prenom, String email, String profil) {
        this.nom = nom;
        this.prenom = prenom;
        this.address = email;
        this.profil = profil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return address;
    }

    public void setEmail(String email) {
        this.address = email;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + address + '\'' +
                ", profil='" + profil + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
