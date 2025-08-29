package com.memoire.dao;

import org.hibernate.Session;

import com.memoire.model.Utilisateur;
import com.memoire.util.HibernateUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@ApplicationScoped 
public class UtilisateurDAO extends GenericDAO<Utilisateur> {

    public Utilisateur findByNomUtilisateur(String nomUtilisateur) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Utilisateur> criteria = builder.createQuery(Utilisateur.class);
            Root<Utilisateur> root = criteria.from(Utilisateur.class);
            criteria.select(root).where(builder.equal(root.get("nomUtilisateur"), nomUtilisateur));
            return session.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null; // Retourne null si l'utilisateur n'est pas trouvé
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'utilisateur par nom : " + e.getMessage(), e);
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}