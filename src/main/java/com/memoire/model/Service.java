package com.memoire.model;

public class Service {
    private int idService;
    private String nom;
    private String description;
    private Boolean disponible;

    // Getters and Setters
    public int getIdService() { return idService; }
    public void setIdService(int idService) { this.idService = idService; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
}
