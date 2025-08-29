package com.memoire.dao;

import com.memoire.model.Patient;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientDAO extends GenericDAO<Patient> {
    // Aucune méthode supplémentaire n'est nécessaire pour l'instant si vous
    // utilisez uniquement les méthodes de GenericDAO
}