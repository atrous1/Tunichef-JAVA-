package org.example.entities;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Reclamation {
    private int idRec,idUser,Avis;
    private  String Description;
    private Date DateRec;

    public Reclamation(int idUser, String description, int avis, Date dateRec ) {
        this.idUser = idUser;

        Description = description;
        Avis = avis;
        DateRec = dateRec;
        }



    public Reclamation(int idRec, int idUser, String description, int avis, Date dateRec) {
        this.idRec = idRec;
        this.idUser = idUser;

        this.Description = description;
        this.Avis = avis;
        this.DateRec = dateRec;
    }

    public Reclamation(TextField description, TextField avis, DatePicker date) {
    }

    public Reclamation(int idRec) {
        this.idRec = idRec;
    }

    public Reclamation() {

    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public Date getDateRec() {
        return DateRec;
    }

    public void setDateRec(Date dateRec) {
        DateRec = dateRec;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
              //  "idRec=" + idRec +
                " idUser :" + idUser +
                " , La date de la reclamation : " + DateRec +
                " , Description :" + Description +
                " , Avis :" + Avis + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reclamation that)) return false;
        return idRec == that.idRec && idUser == that.idUser && Avis == that.Avis && Objects.equals(Description, that.Description) && Objects.equals(DateRec, that.DateRec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRec, idUser, Avis, Description, DateRec);
    }
}
