package com.memoire.model;

import java.io.Serializable;
import java.util.Objects;

public class NecessiteDetail implements Serializable {
    private int idDetail;
    private int idNecessite;

    // Getters and Setters
    public int getIdDetail() { return idDetail; }
    public void setIdDetail(int idDetail) { this.idDetail = idDetail; }
    public int getIdNecessite() { return idNecessite; }
    public void setIdNecessite(int idNecessite) { this.idNecessite = idNecessite; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NecessiteDetail that = (NecessiteDetail) o;
        return idDetail == that.idDetail && idNecessite == that.idNecessite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetail, idNecessite);
    }
}
