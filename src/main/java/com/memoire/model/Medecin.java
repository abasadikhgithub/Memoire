package com.memoire.model;

public class Medecin extends Utilisateur {
    private int numeroLicence;
    private StructureSanitaire structureSanitaire;

    // Getters and Setters
    public int getNumeroLicence() { return numeroLicence; }
    public void setNumeroLicence(int numeroLicence) { this.numeroLicence = numeroLicence; }
    public StructureSanitaire getStructureSanitaire() { return structureSanitaire; }
    public void setStructureSanitaire(StructureSanitaire structureSanitaire) { this.structureSanitaire = structureSanitaire; }
}
