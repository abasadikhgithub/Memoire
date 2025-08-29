package com.memoire.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

import org.mindrot.jbcrypt.BCrypt;

import com.memoire.dao.PatientDAO;
import com.memoire.model.Patient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PatientService {

	@Inject
    private PatientDAO patientDAO; // Utilisation de votre DAO générique

    public void enregistrerPatient(Patient patient, InputStream photoInputStream, String fileName, String realPath) throws IOException {
        // Hachage du mot de passe
        String motDePasseHache = BCrypt.hashpw(patient.getMotDePasse(), BCrypt.gensalt());
        patient.setMotDePasse(motDePasseHache);

        // Sauvegarde de la photo sur le serveur
        Path uploadPath = Paths.get(realPath);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String photoPath = realPath + fileName;
        Files.copy(photoInputStream, Paths.get(photoPath), StandardCopyOption.REPLACE_EXISTING);
        patient.setPhoto(photoPath);

        // Définition des valeurs par défaut
        Long d = System.currentTimeMillis();
        
        patient.setDateCreation(new Date(d));
        patient.setEstActif(true);

        // Enregistrement du patient via le DAO générique
        patientDAO.save(patient);
    }
}