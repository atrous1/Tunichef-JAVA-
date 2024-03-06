package Entities;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Reclamation {
    private int idRec,id,Avis;
    private  String Description;
    private Date DateRec;


    public Reclamation(int id, String description, int avis, Date dateRec ) {
        this.id = id;

        Description = description;
        Avis = avis;
        DateRec = dateRec;
        }



    public Reclamation(int idRec, int id, String description, int avis, Date dateRec) {
        this.idRec = idRec;
        this.id = id;

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

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
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
                " id :" + id +
                " , La date de la reclamation : " + DateRec +
                " , Description :" + Description +
                " , Avis :" + Avis + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reclamation that)) return false;
        return idRec == that.idRec && id == that.id && Avis == that.Avis && Objects.equals(Description, that.Description) && Objects.equals(DateRec, that.DateRec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRec, id, Avis, Description, DateRec);
    }
}
