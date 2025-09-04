package com.memoire.beans;

import java.io.Serializable;
import com.memoire.model.AgentEtat;
import com.memoire.model.Medecin;
import com.memoire.model.Patient;
import com.memoire.model.Utilisateur;
import com.memoire.services.LoginService;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nomUtilisateur;
    private String motDePasse;

    @Inject
    private LoginService loginService;

    // Redirige l'utilisateur en fonction de son rôle après la connexion réussie
    public String login() {
        Utilisateur utilisateur = loginService.authentifier(nomUtilisateur, motDePasse);

        if (utilisateur != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("utilisateurConnecte", utilisateur);

            // Appel de la méthode pour obtenir le chemin de redirection
            return getDashboardOutcome();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de connexion", "Nom d'utilisateur ou mot de passe incorrect."));
            return null;
        }
    }

    // Déconnecte l'utilisateur en invalidant la session
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    // Vérifie si un utilisateur est connecté
    public boolean isUserLoggedIn() {
        return getUtilisateurConnecte() != null;
    }

    // Récupère l'utilisateur actuellement en session
    public Utilisateur getUtilisateurConnecte() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            return (Utilisateur) session.getAttribute("utilisateurConnecte");
        }
        return null;
    }

    // Fournit le chemin de redirection du tableau de bord en fonction du rôle
    public String getDashboardOutcome() {
        Utilisateur utilisateur = getUtilisateurConnecte();
        if (utilisateur == null) {
            return "/login.xhtml?faces-redirect=true";
        }
        
        if (utilisateur.isEstAdmin()) {
            return "/admin/dashboardAdmin.xhtml?faces-redirect=true";
        } else if (utilisateur instanceof Patient) {
            return "/patient/dashboardPatient.xhtml?faces-redirect=true";
        } else if (utilisateur instanceof Medecin) {
            return "/medecin/dashboardMedecin.xhtml?faces-redirect=true";
        } else if (utilisateur instanceof AgentEtat) {
            return "/agentetat/dashboardAgentEtat.xhtml?faces-redirect=true";
        }
        
        return "/public/index.xhtml?faces-redirect=true";
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}