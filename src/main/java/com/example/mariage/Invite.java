package com.example.mariage;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Invite {

    @Id
    public String id;
    private String nom;
    private String prenom;
    private String plat;
    private Boolean repas;
    private Boolean ceremonie;
    private Boolean vin;

    public Invite() {
    }

    public Invite(String nom, String prenom, String plat, boolean ceremonie,
            boolean vin, boolean repas) {
        this.nom = nom;
        this.prenom = prenom;
        this.plat = plat;
        this.repas = repas;
        this.ceremonie = ceremonie;
        this.vin = vin;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom() {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom() {
        this.prenom = prenom;
    }

    public String getPlat() {
        return plat;
    }

    public boolean getRepas() {
        return repas;
    }

    public boolean getCeremonie() {
        return ceremonie;
    }

    public boolean getVin() {
        return vin;
    }

    public String toString() {

        return nom + " " + prenom + " " + plat + " " + ceremonie + " " + vin + " " + repas;
    }

}
