package com.memoire.model;

public class Notification {
    private int idNotification;
    private Utilisateur utilisateur;
    private String type;
    private String contenu;
    private String dateEnvoi;
    private Boolean estVisible;

    // Getters and Setters
    public int getIdNotification() { return idNotification; }
    public void setIdNotification(int idNotification) { this.idNotification = idNotification; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public String getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(String dateEnvoi) { this.dateEnvoi = dateEnvoi; }
    public Boolean getEstVisible() { return estVisible; }
    public void setEstVisible(Boolean estVisible) { this.estVisible = estVisible; }
}