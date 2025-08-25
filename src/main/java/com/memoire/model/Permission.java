package com.memoire.model;

public class Permission {
    private int idPermission;
    private int codePermission;
    private String nom;
    private String description;

    // Getters and Setters
    public int getIdPermission() { return idPermission; }
    public void setIdPermission(int idPermission) { this.idPermission = idPermission; }
    public int getCodePermission() { return codePermission; }
    public void setCodePermission(int codePermission) { this.codePermission = codePermission; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
