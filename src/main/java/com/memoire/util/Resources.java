package com.memoire.util;

import com.memoire.dao.GenericDAO;
import com.memoire.dao.IGenericDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent; // Importez le scope Dependent
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@ApplicationScoped // Cette annotation est correcte pour la classe Resources
public class Resources {

    // Cette méthode produit et gère le cycle de vie de la SessionFactory pour CDI
    @Produces
    @ApplicationScoped
    public SessionFactory createSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Cette méthode produit un IGenericDAO<T> pour n'importe quel type T
    // Le scope Dependent est le bon choix pour ce type de production
    @Produces
    @Dependent
    public <T> IGenericDAO<T> createGenericDAO(InjectionPoint injectionPoint, SessionFactory sessionFactory) {
        ParameterizedType type = (ParameterizedType) injectionPoint.getType();
        @SuppressWarnings("unchecked")
        Class<T> entityClass = (Class<T>) type.getActualTypeArguments()[0];
        
        return new GenericDAO<T>(entityClass, sessionFactory);
    }
}