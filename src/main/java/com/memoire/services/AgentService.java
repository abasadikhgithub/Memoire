package com.memoire.services;

import com.memoire.dao.AgentEtatDAO;
import com.memoire.model.AgentEtat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.util.List;

@ApplicationScoped
public class AgentService {

    @Inject
    private AgentEtatDAO agentEtatDAO;

    public void enregistrerAgent(AgentEtat agent) {
        String motDePasseHache = BCrypt.hashpw(agent.getMotDePasse(), BCrypt.gensalt());
        agent.setMotDePasse(motDePasseHache);
        Long d = System.currentTimeMillis();
        agent.setDateCreation(new Date(d));
        agent.setEstActif(true);
        agent.setEstAdmin(false);
        agentEtatDAO.save(agent);
    }
    
    public List<AgentEtat> findAll() {
        return agentEtatDAO.findAll();
    }
    
    public void update(AgentEtat agent) {
        agentEtatDAO.update(agent);
    }
    
    public void delete(AgentEtat agent) {
        agentEtatDAO.delete(agent);
    }
}