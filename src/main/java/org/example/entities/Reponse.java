package org.example.entities;

import java.util.Objects;

public class Reponse {

        private int idRec,idRep;
        private  String Contenu;



    public Reponse(int idRep, String Contenu, int idRec)
    {
        this.idRep = idRep;
        Contenu = Contenu;
        this.idRec = idRec;

    }
    public Reponse(int idRep) {
        this.idRep = idRep;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public int getIdRep() {
        return idRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        Contenu = Contenu;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "idRec=" + idRec +
                ", idRep=" + idRep +
                ", Contenu='" + Contenu + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reponse reponse)) return false;
        return idRec == reponse.idRec && idRep == reponse.idRep && Objects.equals(Contenu, reponse.Contenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRec, idRep, Contenu);
    }
}
