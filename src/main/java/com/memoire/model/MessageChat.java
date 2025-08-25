package com.memoire.model;

public class MessageChat {
	private int idMessage;
    private Medecin medecin;
    private Patient patient;
    private String message;
    private Boolean estLu;

    // Getters and Setters
    public int getIdMessage() { return idMessage; }
    public void setIdMessage(int idMessage) { this.idMessage = idMessage; }
    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Boolean getEstLu() { return estLu; }
    public void setEstLu(Boolean estLu) { this.estLu = estLu; }

}
