package com.memoire.beans;

import java.io.IOException;
import java.io.Serializable;

import org.primefaces.model.file.UploadedFile;

import com.memoire.model.Patient;
import com.memoire.services.PatientService;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;

@Named
@ViewScoped
public class PatientBean implements Serializable {

    private Patient patient = new Patient();
    private UploadedFile uploadedFile; // Pour le téléchargement de la photo

    @Inject
    private PatientService patientService;

    public String inscrirePatient() {
        if ((uploadedFile != null && uploadedFile.getFileName() != null) ) {
            try {
                // Obtenez le nom de fichier
                String fileName = uploadedFile.getFileName();
             // --- Début de la modification ---
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
                String realPath = servletContext.getRealPath("/telemedecin/photos/"); // Chemin réel sur le serveur
                
                patientService.enregistrerPatient(patient, uploadedFile.getInputStream(), fileName, realPath);
                // --- Fin de la modification ---
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Inscription réussie !"));
                return "login.xhtml?faces-redirect=true"; // Redirigez vers la page de connexion
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Erreur lors de l'upload de la photo."));
                e.printStackTrace();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Veuillez télécharger une photo de profil."));
        }
        return null; // Restez sur la même page en cas d'erreur
    }

    // Getters et Setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}