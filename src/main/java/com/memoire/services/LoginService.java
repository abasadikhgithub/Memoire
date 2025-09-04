package com.memoire.services;

import com.memoire.dao.UtilisateurDAO;
import com.memoire.model.Utilisateur;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class LoginService {

    @Inject
    private UtilisateurDAO utilisateurDAO;

    /**
     * Tente de connecter un utilisateur en vérifiant son nom et son mot de passe.
     * @param nomUtilisateur Le nom d'utilisateur saisi.
     * @param motDePasse Le mot de passe non haché saisi.
     * @return L'objet Utilisateur si la connexion est réussie, sinon null.
     */
    public Utilisateur authentifier(String nomUtilisateur, String motDePasse) {
        // 1. Chercher l'utilisateur dans la base de données par son nom
        Utilisateur utilisateur = utilisateurDAO.findByNomUtilisateur(nomUtilisateur);

        // 2. Vérifier si l'utilisateur existe et si le mot de passe correspond
        if (utilisateur != null && BCrypt.checkpw(motDePasse, utilisateur.getMotDePasse()) && utilisateur.isEstActif()) {
            return utilisateur; // Connexion réussie
        }

        return null; // Connexion échouée
    }
}