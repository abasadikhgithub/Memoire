package com.memoire.model;

public class Prescription {
    private int idPrescription;
    private Consultation consultation;
    private String type;
    private String analyse;

    // Getters and Setters
    public int getIdPrescription() { return idPrescription; }
    public void setIdPrescription(int idPrescription) { this.idPrescription = idPrescription; }
    public Consultation getConsultation() { return consultation; }
    public void setConsultation(Consultation consultation) { this.consultation = consultation; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getAnalyse() { return analyse; }
    public void setAnalyse(String analyse) { this.analyse = analyse; }
}
