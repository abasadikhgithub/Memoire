package com.memoire.services;

import com.memoire.dao.AgentEtatDAO;
import com.memoire.model.AgentEtat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;

@ApplicationScoped
public class AgentService {

    @Inject
    private AgentEtatDAO agentEtatDAO;

    public void enregistrerAgent(AgentEtat agent) {
        // Hachage du mot de passe
        String motDePasseHache = BCrypt.hashpw(agent.getMotDePasse(), BCrypt.gensalt());
        agent.setMotDePasse(motDePasseHache);

        // Définition des valeurs par défaut 
        Long d = System.currentTimeMillis();
        agent.setDateCreation(new Date(d));
        agent.setEstActif(true);
        agent.setEstAdmin(false); // S'assurer que les agents ne sont pas des admins par défaut

        // Enregistrement de l'agent via le DAO
        agentEtatDAO.save(agent);
    }
}