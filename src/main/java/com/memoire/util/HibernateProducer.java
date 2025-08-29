package com.memoire.util;

import org.hibernate.SessionFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

public class HibernateProducer {

    @Produces
    @ApplicationScoped
    public SessionFactory produceSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }
}