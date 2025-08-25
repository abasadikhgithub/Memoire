package com.memoire.model;

import java.math.BigDecimal;
import java.util.Date;

public class DonneeCapteur {
	private int id;
    private Capteur capteur;
    private BigDecimal valeur;
    private Date dateReception;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Capteur getCapteur() { return capteur; }
    public void setCapteur(Capteur capteur) { this.capteur = capteur; }
    public BigDecimal getValeur() { return valeur; }
    public void setValeur(BigDecimal valeur) { this.valeur = valeur; }
    public Date getDateReception() { return dateReception; }
    public void setDateReception(Date dateReception) { this.dateReception = dateReception; }

}
