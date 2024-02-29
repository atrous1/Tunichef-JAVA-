package org.example.entities;
import javafx.scene.control.TextField;

import java.util.Date;
import java.util.Objects;

public class Reponse {

        private int idRep;
        private Reclamation reclamation;
        private  String Contenu;
    private Date DateRep;

    public Reponse(int idRep, String contenu, int idRec, Date dateRep) {
    }


    public Reponse(int idRep, String Contenu, Reclamation reclamation, Date dateRep)
    {
        this.idRep = idRep;
        this.Contenu = Contenu;
        this.reclamation = reclamation;
        DateRep = dateRep;

    }

    public Reponse(Reclamation reclamation, String contenu, Date dateRep) {
        this.reclamation = reclamation;
        Contenu = contenu;
        DateRep = dateRep;
    }

    public Reponse(int idRep) {
        this.idRep = idRep;
    }

    public Reponse(TextField contenu, Date date) {
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public int getidRec() {
        return reclamation.getIdRec();  // Obtenir idRec via l'objet Reclamation
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
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
        this.Contenu = Contenu;
    }

    public Date getDateRep() {
        return DateRep;
    }

    public void setDateRep(Date dateRep) {
        DateRep = dateRep;
    }
    @Override
    public String toString() {
        return "Reponse{" +
              //  "idRep=" + idRep +
                "Reclamation : " + reclamation.getIdRec() +
                //", idUser=" + reclamation.getIdUser()+
                ", Contenu = '" + Contenu + '\'' +
                ", Date de la réponse = " + DateRep +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reponse reponse)) return false;
        return idRep == reponse.idRep && Objects.equals(reclamation, reponse.reclamation) && Objects.equals(Contenu, reponse.Contenu) && Objects.equals(DateRep, reponse.DateRep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRep, reclamation, Contenu, DateRep);
    }

    public void setReclamation(int i) {
    }
}
