package com.memoire.model;

import java.io.Serializable;
import java.util.Objects;

public class SpecialiteMedecin implements Serializable {
    private int idUtilisateur;
    private int idSpecilite;

    // Getters and Setters
    public int getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public int getIdSpecilite() { return idSpecilite; }
    public void setIdSpecilite(int idSpecilite) { this.idSpecilite = idSpecilite; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialiteMedecin that = (SpecialiteMedecin) o;
        return idUtilisateur == that.idUtilisateur && idSpecilite == that.idSpecilite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, idSpecilite);
    }
}
