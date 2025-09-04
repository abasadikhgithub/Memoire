package com.memoire.beans;

import com.memoire.model.AgentEtat;
import com.memoire.services.AgentService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


@Named
@ViewScoped
public class GererAgentsBean implements Serializable {

    private AgentEtat agent = new AgentEtat();
    private List<AgentEtat> agents;
    private AgentEtat agentSelectionne;
    private String filtreGlobal; 

    @Inject
    private AgentService agentService;

    @PostConstruct
    public void init() {
        chargerAgents();
    }
    
    public void chargerAgents() {
        this.agents = agentService.findAll();
    }
    
    public void prepareNewAgent() {
        this.agent = new AgentEtat();
        this.agentSelectionne = null;
    }

    // Nouvelle méthode unique pour la création et la modification
    public void saveAgent() {
        try {
            if (this.agent.getIdUtilisateur() == 0) {
                // C'est une création
                agentService.enregistrerAgent(agent);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "L'agent a été créé avec succès."));
            } else {
                // C'est une modification
                agentService.update(this.agent);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "L'agent a été mis à jour avec succès."));
            }
            
            chargerAgents();
            this.agentSelectionne = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Erreur lors de la gestion de l'agent."));
        }
    }
    
    public void prepareModifierAgent() {
        if (this.agentSelectionne != null) {
            // Créer une nouvelle instance pour la modification pour éviter les problèmes de session
            this.agent = this.agentSelectionne;
        }
    }
    
    public void bloquerAgent() {
        if (agentSelectionne != null) {
            agentSelectionne.setEstActif(false);
            agentService.update(agentSelectionne);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "L'agent a été bloqué."));
            chargerAgents();
            agentSelectionne = null;
        }
    }

    public void debloquerAgent() {
        if (agentSelectionne != null) {
            agentSelectionne.setEstActif(true);
            agentService.update(agentSelectionne);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "L'agent a été débloqué."));
            chargerAgents();
            agentSelectionne = null;
        }
    }

    public void supprimerAgent() {
        if (agentSelectionne != null) {
            agentService.delete(agentSelectionne);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "L'agent a été supprimé."));
            chargerAgents();
            agentSelectionne = null;
        }
    }
    
    // méthode pour obtenir la liste filtrée
    public List<AgentEtat> getAgentsFiltres() {
        if (filtreGlobal == null || filtreGlobal.isEmpty()) {
            return agents; // Retourne la liste complète si le filtre est vide
        } else {
            // Logique de filtrage
            String filtreEnMinuscules = filtreGlobal.toLowerCase();
            return agents.stream()
                         .filter(agent -> 
                             agent.getNomUtilisateur().toLowerCase().contains(filtreEnMinuscules) ||
                             agent.getEmail().toLowerCase().contains(filtreEnMinuscules) ||
                             agent.getTelephone().toLowerCase().contains(filtreEnMinuscules) ||
                             String.valueOf(agent.isEstActif()).toLowerCase().contains(filtreEnMinuscules))
                         .collect(Collectors.toList());
        }
    }
    
    public void notifierParEmail() {
        if (agentSelectionne == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Aucun agent sélectionné pour la notification."));
            return;
        }

        final String fromEmail = ""; // Remplacer par votre email
        final String password = ""; // Remplacer par votre mot de passe d'application

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host de votre fournisseur de messagerie
        props.put("mail.smtp.port", "587"); // Port
        props.put("mail.smtp.auth", "true"); // Activation de l'authentification
        props.put("mail.smtp.starttls.enable", "true"); // TLS

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(agentSelectionne.getEmail()));
            
            String sujet = "Notification sur votre compte";
            String contenu = "Bonjour " + agentSelectionne.getNomUtilisateur() + ",\n\n";
            if (agentSelectionne.isEstActif()) {
                contenu += "Votre compte a été activé. Vous pouvez maintenant vous connecter.";
            } else {
                contenu += "Votre compte a été bloqué par un administrateur. Veuillez contacter le support si vous avez des questions.";
            }
            contenu += "\n\nCordialement,\nL'équipe d'administration.";

            message.setSubject(sujet);
            message.setText(contenu);
            
            Transport.send(message);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Email de notification envoyé à " + agentSelectionne.getEmail()));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Échec de l'envoi de l'email : " + e.getMessage()));
            // e.printStackTrace(); // Utile pour le débogage
        }
    }

    // Getters et Setters
    public AgentEtat getAgent() {
        return agent;
    }

    public void setAgent(AgentEtat agent) {
        this.agent = agent;
    }

    public List<AgentEtat> getAgents() {
        return agents;
    }

    public void setAgents(List<AgentEtat> agents) {
        this.agents = agents;
    }

    public AgentEtat getAgentSelectionne() {
        return agentSelectionne;
    }

    public void setAgentSelectionne(AgentEtat agentSelectionne) {
        this.agentSelectionne = agentSelectionne;
    }
    
    public String getFiltreGlobal() {
        return filtreGlobal;
    }

    public void setFiltreGlobal(String filtreGlobal) {
        this.filtreGlobal = filtreGlobal;
    }
}