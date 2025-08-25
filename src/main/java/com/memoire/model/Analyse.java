package com.memoire.model;

import java.util.Date;

public class Analyse extends ActeMedical {
    private int numero;
    private String nom;
    private String description;
    private Date datePrescription;
    private Date dateRealisation;
    private Boolean urgence;
    private Medecin medecin;
    private Laboratoire laboratoire;
    private Patient patientAnalyse;

    // Getters and Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDatePrescription() { return datePrescription; }
    public void setDatePrescription(Date datePrescription) { this.datePrescription = datePrescription; }
    public Date getDateRealisation() { return dateRealisation; }
    public void setDateRealisation(Date dateRealisation) { this.dateRealisation = dateRealisation; }
    public Boolean getUrgence() { return urgence; }
    public void setUrgence(Boolean urgence) { this.urgence = urgence; }
    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }
    public Laboratoire getLaboratoire() { return laboratoire; }
    public void setLaboratoire(Laboratoire laboratoire) { this.laboratoire = laboratoire; }
    public Patient getPatientAnalyse() { return patientAnalyse; }
    public void setPatientAnalyse(Patient patientAnalyse) { this.patientAnalyse = patientAnalyse; }
}