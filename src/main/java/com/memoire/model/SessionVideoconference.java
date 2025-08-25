package com.memoire.model;

import java.util.Date;

public class SessionVideoconference {
    private int idSession;
    private String urlSession;
    private Date heureDebut;
    private Date heureFin;
    private String statutSession;

    // Getters and Setters
    public int getIdSession() { return idSession; }
    public void setIdSession(int idSession) { this.idSession = idSession; }
    public String getUrlSession() { return urlSession; }
    public void setUrlSession(String urlSession) { this.urlSession = urlSession; }
    public Date getHeureDebut() { return heureDebut; }
    public void setHeureDebut(Date heureDebut) { this.heureDebut = heureDebut; }
    public Date getHeureFin() { return heureFin; }
    public void setHeureFin(Date heureFin) { this.heureFin = heureFin; }
    public String getStatutSession() { return statutSession; }
    public void setStatutSession(String statutSession) { this.statutSession = statutSession; }
}
