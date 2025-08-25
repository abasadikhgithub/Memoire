package com.memoire.model;

public class Type {
    private int idType;
    private StructureSanitaire structureSanitaire;
    private Boolean estPublic;
    private int nbrePrive;
    private int nbrePublic;

    // Getters and Setters
    public int getIdType() { return idType; }
    public void setIdType(int idType) { this.idType = idType; }
    public StructureSanitaire getStructureSanitaire() { return structureSanitaire; }
    public void setStructureSanitaire(StructureSanitaire structureSanitaire) { this.structureSanitaire = structureSanitaire; }
    public Boolean getEstPublic() { return estPublic; }
    public void setEstPublic(Boolean estPublic) { this.estPublic = estPublic; }
    public int getNbrePrive() { return nbrePrive; }
    public void setNbrePrive(int nbrePrive) { this.nbrePrive = nbrePrive; }
    public int getNbrePublic() { return nbrePublic; }
    public void setNbrePublic(int nbrePublic) { this.nbrePublic = nbrePublic; }
}
