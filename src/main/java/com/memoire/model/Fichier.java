package com.memoire.model;

import java.util.Date;

public class Fichier {
	private int idFichier;
    private String nomFichier;
    private String urlFichier;
    private String typeFichier;
    private Date dateUpload;

    // Getters and Setters
    public int getIdFichier() { return idFichier; }
    public void setIdFichier(int idFichier) { this.idFichier = idFichier; }
    public String getNomFichier() { return nomFichier; }
    public void setNomFichier(String nomFichier) { this.nomFichier = nomFichier; }
    public String getUrlFichier() { return urlFichier; }
    public void setUrlFichier(String urlFichier) { this.urlFichier = urlFichier; }
    public String getTypeFichier() { return typeFichier; }
    public void setTypeFichier(String typeFichier) { this.typeFichier = typeFichier; }
    public Date getDateUpload() { return dateUpload; }
    public void setDateUpload(Date dateUpload) { this.dateUpload = dateUpload; }

}
