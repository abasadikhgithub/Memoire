package com.memoire.util;

import org.hibernate.SessionFactory;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class HibernateContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
       
        sce.getServletContext().setAttribute("sessionFactory", sessionFactory);
        System.out.println("Hibernate SessionFactory initialized and set in ServletContext.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
        SessionFactory sessionFactory = (SessionFactory) sce.getServletContext().getAttribute("sessionFactory");
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("Hibernate SessionFactory closed.");
        }
    }
}
