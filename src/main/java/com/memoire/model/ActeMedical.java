package com.memoire.model;

import java.util.Date;

public class ActeMedical {
    private int idActe;
    private Date date;
    private String type;
    private Patient patient;
    private Facture facture;


    public int getIdActe() { return idActe; }
    public void setIdActe(Integer idActe) { this.idActe = idActe; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Facture getFacture() { return facture; }
    public void setFacture(Facture facture) { this.facture = facture; }

}
