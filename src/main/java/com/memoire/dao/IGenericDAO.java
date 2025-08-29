package com.memoire.dao;

import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped 
public interface IGenericDAO<T> {

    void save(T entity); // Persist ou merge selon le contexte
    T findById(Long id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
    void deleteById(Long id);

}