package com.memoire.model;

import java.util.Date;

public class Facture {
	private int idFacture;
    private int montant;
    private String statut;
    private Date dateEmission;

    // Getters and Setters
    public int getIdFacture() { return idFacture; }
    public void setIdFacture(int idFacture) { this.idFacture = idFacture; }
    public int getMontant() { return montant; }
    public void setMontant(int montant) { this.montant = montant; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public Date getDateEmission() { return dateEmission; }
    public void setDateEmission(Date dateEmission) { this.dateEmission = dateEmission; }

}
