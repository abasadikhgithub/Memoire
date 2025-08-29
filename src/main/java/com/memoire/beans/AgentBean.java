package com.memoire.beans;

import com.memoire.model.AgentEtat;
import com.memoire.services.AgentService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AgentBean implements Serializable {

    private AgentEtat agent = new AgentEtat();

    @Inject
    private AgentService agentService;

    public String creerAgent() {
        try {
            agentService.enregistrerAgent(agent);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "L'agent a été créé avec succès."));
            // Réinitialiser le bean pour une nouvelle création
            agent = new AgentEtat();
            return null; // Reste sur la même page
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Une erreur est survenue lors de la création de l'agent."));
            e.printStackTrace();
            return null;
        }
    }

    // Getters et Setters
    public AgentEtat getAgent() {
        return agent;
    }

    public void setAgent(AgentEtat agent) {
        this.agent = agent;
    }
}