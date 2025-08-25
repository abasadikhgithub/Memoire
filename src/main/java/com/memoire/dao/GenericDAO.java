package com.memoire.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.memoire.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder; // Pour la nouvelle Criteria API
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class GenericDAO<T> implements IGenericDAO<T> {

    private Class<T> entityClass;

    // Le constructeur détermine le type de l'entité au moment de l'instanciation
    @SuppressWarnings("unchecked") // Supprime l'avertissement pour la conversion de type
    public GenericDAO() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Session getCurrentSession() {
        // Obtenez une nouvelle session à chaque fois pour s'assurer qu'elle est propre
        // ou vous pouvez utiliser sessionFactory.getCurrentSession() si le contexte 'thread' est configuré
        // Pour l'instant, restons sur openSession() et gérons la fermeture
        return HibernateUtil.getSession();
    }

    @Override
    public void save(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(entity); // Pour les nouvelles entités
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde de l'entité " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public T findById(Long id) {
        Session session = null;
        try {
            session = getCurrentSession();
            return session.find(entityClass, id); // Utilise la méthode find de la session
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'entité " + entityClass.getSimpleName() + " par ID " + id + ": " + e.getMessage(), e);
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public List<T> findAll() {
        Session session = null;
        try {
            session = getCurrentSession();
            // Utilisation de CriteriaBuilder pour une requête générique
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(entityClass);
            Root<T> root = criteria.from(entityClass);
            criteria.select(root);
            return session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de toutes les entités " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public void update(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getCurrentSession();
            transaction = session.beginTransaction();
            session.merge(entity); // Pour mettre à jour une entité détachée
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour de l'entité " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public void delete(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getCurrentSession();
            transaction = session.beginTransaction();
            session.remove(entity); // Supprime l'entité persistante
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de l'entité " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getCurrentSession();
            transaction = session.beginTransaction();
            T entityToDelete = session.find(entityClass, id);
            if (entityToDelete != null) {
                session.remove(entityToDelete);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de l'entité " + entityClass.getSimpleName() + " par ID " + id + ": " + e.getMessage(), e);
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

}