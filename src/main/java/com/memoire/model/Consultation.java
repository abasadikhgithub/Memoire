package com.memoire.model;

import java.util.Date;

public class Consultation extends ActeMedical {
    private Date dateHeure;
    private String notesMedecin;
    private Medecin medecinConsultation;
    private SessionVideoconference sessionVideoconference;
    private RendezVous rendezVous;

    // Getters and Setters
    public Date getDateHeure() { return dateHeure; }
    public void setDateHeure(Date dateHeure) { this.dateHeure = dateHeure; }
    public String getNotesMedecin() { return notesMedecin; }
    public void setNotesMedecin(String notesMedecin) { this.notesMedecin = notesMedecin; }
    public Medecin getMedecinConsultation() { return medecinConsultation; }
    public void setMedecinConsultation(Medecin medecinConsultation) { this.medecinConsultation = medecinConsultation; }
    public SessionVideoconference getSessionVideoconference() { return sessionVideoconference; }
    public void setSessionVideoconference(SessionVideoconference sessionVideoconference) { this.sessionVideoconference = sessionVideoconference; }
    public RendezVous getRendezVous() { return rendezVous; }
    public void setRendezVous(RendezVous rendezVous) { this.rendezVous = rendezVous; }
}
