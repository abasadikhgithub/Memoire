package com.memoire.model;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {
    private int idUtilisateur;
    private int idPermission;
    private String date;

    // Getters and Setters
    public int getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public int getIdPermission() { return idPermission; }
    public void setIdPermission(int idPermission) { this.idPermission = idPermission; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role that = (Role) o;
        return idUtilisateur == that.idUtilisateur && idPermission == that.idPermission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, idPermission);
    }
}
