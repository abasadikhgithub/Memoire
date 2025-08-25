package com.memoire.model;

public class ResultatAnalyse {
    private int idResultat;
    private Fichier fichier;
    private Analyse analyse;
    private String interpretation;

    // Getters and Setters
    public int getIdResultat() { return idResultat; }
    public void setIdResultat(int idResultat) { this.idResultat = idResultat; }
    public Fichier getFichier() { return fichier; }
    public void setFichier(Fichier fichier) { this.fichier = fichier; }
    public Analyse getAnalyse() { return analyse; }
    public void setAnalyse(Analyse analyse) { this.analyse = analyse; }
    public String getInterpretation() { return interpretation; }
    public void setInterpretation(String interpretation) { this.interpretation = interpretation; }
}
