package com.memoire.model;

public class DetailPrescription {
    private int idDetail;
    private Prescription prescription;
    private String libelle;
    private String produit;
    private int quantite;

    // Getters and Setters
    public int getIdDetail() { return idDetail; }
    public void setIdDetail(int idDetail) { this.idDetail = idDetail; }
    public Prescription getPrescription() { return prescription; }
    public void setPrescription(Prescription prescription) { this.prescription = prescription; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public String getProduit() { return produit; }
    public void setProduit(String produit) { this.produit = produit; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}
