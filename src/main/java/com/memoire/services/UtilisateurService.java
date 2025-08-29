package com.memoire.services;

import com.memoire.dao.UtilisateurDAO;
import com.memoire.model.Utilisateur;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UtilisateurService {

    @Inject
    private UtilisateurDAO utilisateurDAO;

    public Utilisateur findByNomUtilisateur(String nomUtilisateur) {
        return utilisateurDAO.findByNomUtilisateur(nomUtilisateur);
    }
}