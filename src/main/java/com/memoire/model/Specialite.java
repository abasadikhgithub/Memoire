package com.memoire.model;

public class Specialite {
    private int idSpecilite;
    private Domaine domaine;
    private String nom;

    // Getters and Setters
    public int getIdSpecilite() { return idSpecilite; }
    public void setIdSpecilite(int idSpecilite) { this.idSpecilite = idSpecilite; }
    public Domaine getDomaine() { return domaine; }
    public void setDomaine(Domaine domaine) { this.domaine = domaine; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}
