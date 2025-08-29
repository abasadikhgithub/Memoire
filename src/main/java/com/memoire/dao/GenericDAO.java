package com.memoire.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.memoire.util.HibernateUtil;

import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder; // Pour la nouvelle Criteria API
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class GenericDAO<T> implements IGenericDAO<T> {

    private Class<T> entityClass;
    
    @Inject
    private SessionFactory sessionFactory;

    // Le constructeur détermine le type de l'entité au moment de l'instanciation
    @SuppressWarnings("unchecked")
    public GenericDAO() {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }
    
 // Constructeur pour l'injection CDI
    public GenericDAO(Class<T> entityClass, SessionFactory sessionFactory) {
        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }
    public Class<T> getEntityClass() {
        return this.entityClass;
    }

    private Session getCurrentSession() {
        return HibernateUtil.getSession();
    }

    @Override
    public void save(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'entité " + entityClass.getSimpleName(), e);
        }
    }
    
    @Override
    public T findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(entityClass, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'entité " + entityClass.getSimpleName() + " par ID " + id, e);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            // Utilisation de CriteriaBuilder pour une requête générique
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(entityClass);
            Root<T> root = criteria.from(entityClass);
            criteria.select(root);
            return session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de toutes les entités " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        } 
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity); // Pour mettre à jour une entité détachée
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour de l'entité " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity); // Supprime l'entité persistante
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de l'entité " + entityClass.getSimpleName() + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
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
        }
    }

}