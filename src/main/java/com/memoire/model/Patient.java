package com.memoire.model;

import java.util.Date;

public class Patient extends Utilisateur {
    private Date dateNaissance;
  
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

    
}
