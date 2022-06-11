package com.gsnotes.utils.importing.model;

import com.gsnotes.utils.enumeration.ActionType;

import java.io.Serializable;

public class ImportModel implements Serializable {
    private Long id;
    private String cne;
    private String nom;
    private String prenom;
    private Long idNiveau;
    private ActionType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
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

    public Long getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Long idNiveau) {
        this.idNiveau = idNiveau;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ImportModel{" +
                "id=" + id +
                ", cne='" + cne + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", idNiveau=" + idNiveau +
                ", type=" + type +
                '}';
    }
}
