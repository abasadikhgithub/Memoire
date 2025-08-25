package com.memoire.model;

import java.io.Serializable;
import java.util.Objects;

public class ServiceStructure implements Serializable {
    private int idStructure;
    private int idService;

    // Getters and Setters
    public int getIdStructure() { return idStructure; }
    public void setIdStructure(int idStructure) { this.idStructure = idStructure; }
    public int getIdService() { return idService; }
    public void setIdService(int idService) { this.idService = idService; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceStructure that = (ServiceStructure) o;
        return idStructure == that.idStructure && idService == that.idService;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStructure, idService);
    }
}
