package com.memoire.model;

public class StructureSanitaire {
    private int idStructure;
    private Nature nature;
    private AgentEtat agentEtat;
    private String nom;
    private String adresse;
    private long telephone;
    private String email;

    // Getters and Setters
    public int getIdStructure() { return idStructure; }
    public void setIdStructure(int idStructure) { this.idStructure = idStructure; }
    public Nature getNature() { return nature; }
    public void setNature(Nature nature) { this.nature = nature; }
    public AgentEtat getAgentEtat() { return agentEtat; }
    public void setAgentEtat(AgentEtat agentEtat) { this.agentEtat = agentEtat; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public long getTelephone() { return telephone; }
    public void setTelephone(long telephone) { this.telephone = telephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
