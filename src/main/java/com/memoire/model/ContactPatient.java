package com.memoire.model;

import java.io.Serializable;
import java.util.Objects;

public class ContactPatient implements Serializable {
    private int patientId;
    private int contactId;

    // Getters and Setters
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public int getContactId() { return contactId; }
    public void setContactId(int contactId) { this.contactId = contactId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPatient that = (ContactPatient) o;
        return patientId == that.patientId && contactId == that.contactId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, contactId);
    }
}
