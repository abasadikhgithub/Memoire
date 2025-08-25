package com.memoire.model;

public class Capteur {
    private int idCapteur;
    private Patient patient;
    private String typeCapteur;
    private String modele;
    private String identifiantHardware;

    // Getters and Setters
    public int getIdCapteur() { return idCapteur; }
    public void setIdCapteur(int idCapteur) { this.idCapteur = idCapteur; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public String getTypeCapteur() { return typeCapteur; }
    public void setTypeCapteur(String typeCapteur) { this.typeCapteur = typeCapteur; }
    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }
    public String getIdentifiantHardware() { return identifiantHardware; }
    public void setIdentifiantHardware(String identifiantHardware) { this.identifiantHardware = identifiantHardware; }
}
