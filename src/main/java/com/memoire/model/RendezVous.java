package com.memoire.model;

import java.util.Date;

public class RendezVous {
    private int idRendezVous;
    private Medecin medecin;
    private Patient patient;
    private Date dateHeure;
    private String statut;
    private String motif;

    // Getters and Setters
    public int getIdRendezVous() { return idRendezVous; }
    public void setIdRendezVous(int idRendezVous) { this.idRendezVous = idRendezVous; }
    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Date getDateHeure() { return dateHeure; }
    public void setDateHeure(Date dateHeure) { this.dateHeure = dateHeure; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }
}
