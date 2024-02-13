package org.example.entities;

import java.util.Objects;

public class Reclamation {
    private int idRec,id_user,Avis;
    private  String Description;

    public Reclamation(int id_user, String description, int avis ) {
        this.id_user = id_user;

        Description = description;
        Avis = avis;
    }

    public Reclamation(int idRec, int id_user, String description, int avis) {
        this.idRec = idRec;
        this.id_user = id_user;

        Description = description;
        Avis = avis;
    }

    public Reclamation() {
    }

    public Reclamation(int idRec) {
        this.idRec = idRec;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getAvis() {
        return Avis;
    }

    public void setAvis(int avis) {
        Avis = avis;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "idRec=" + idRec +
                ", id_user=" + id_user +
                ", Avis=" + Avis +
                ", Description='" + Description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reclamation that)) return false;
        return idRec == that.idRec && id_user == that.id_user && Avis == that.Avis && Objects.equals(Description, that.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRec, id_user, Avis, Description);
    }
}
