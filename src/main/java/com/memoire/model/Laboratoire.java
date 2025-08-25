package com.memoire.model;

public class Laboratoire {
	private int idLaboratoire;
    private StructureSanitaire structureSanitaire;
    private int fixe;
    private String localisationGPS;
    private String siteWeb;

    // Getters and Setters
    public int getIdLaboratoire() { return idLaboratoire; }
    public void setIdLaboratoire(int idLaboratoire) { this.idLaboratoire = idLaboratoire; }
    public StructureSanitaire getStructureSanitaire() { return structureSanitaire; }
    public void setStructureSanitaire(StructureSanitaire structureSanitaire) { this.structureSanitaire = structureSanitaire; }
    public int getFixe() { return fixe; }
    public void setFixe(int fixe) { this.fixe = fixe; }
    public String getLocalisationGPS() { return localisationGPS; }
    public void setLocalisationGPS(String localisationGPS) { this.localisationGPS = localisationGPS; }
    public String getSiteWeb() { return siteWeb; }
    public void setSiteWeb(String siteWeb) { this.siteWeb = siteWeb; }

}
