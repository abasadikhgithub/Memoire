package com.memoire.beans;

import com.memoire.model.AgentEtat;
import com.memoire.model.Medecin;
import com.memoire.model.Patient;
import com.memoire.model.Utilisateur;
import com.memoire.services.LoginService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomUtilisateur;
    private String motDePasse;

    @Inject
    private LoginService loginService;
    
    @PostConstruct
    public void checkSession() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

            if (session != null && session.getAttribute("utilisateurConnecte") != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");

                if (utilisateur.isEstAdmin()) {
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/admin/dashboardAdmin.xhtml");
                } else if (utilisateur instanceof Patient) {
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/patient/dashboardPatient.xhtml");
                } else if (utilisateur instanceof Medecin) {
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/medecin/dashboardMedecin.xhtml");
                } else if (utilisateur instanceof AgentEtat) {
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/agentetat/dashboardAgentEtat.xhtml");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String login() {
        // Tente d'authentifier l'utilisateur
        Utilisateur utilisateur = loginService.authentifier(nomUtilisateur, motDePasse);

        if (utilisateur != null) {
            // Connexion réussie, stocker l'utilisateur dans la session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("utilisateurConnecte", utilisateur);

            // Redirection en fonction du type d'utilisateur
            if (utilisateur.isEstAdmin()) {
                return "/admin/dashboardAdmin.xhtml?faces-redirect=true";
            } else if (utilisateur instanceof Patient) {
                return "/patient/dashboardPatient.xhtml?faces-redirect=true";
            } else if (utilisateur instanceof Medecin) {
                return "/medecin/dashboardMedecin.xhtml?faces-redirect=true";
            } else if (utilisateur instanceof AgentEtat) {
                return "/agentetat/dashboardAgentEtat.xhtml?faces-redirect=true";
            } else {
                return "/dashboard.xhtml?faces-redirect=true";
            }
        } else {
            // Connexion échouée
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de connexion", "Nom d'utilisateur ou mot de passe incorrect."));
            return null; // Reste sur la page de connexion
        }
    }

    // Getters et Setters pour nomUtilisateur et motDePasse
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