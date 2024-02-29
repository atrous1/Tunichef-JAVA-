package org.example.tests;
import org.example.Services.ServiceReclamation;
import org.example.Services.ServiceReponse;
import org.example.entities.Reclamation;
import org.example.entities.Reponse;
import org.example.utils.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws SQLException {

        DataSource connexion = DataSource.getInstance();
        ServiceReclamation crud = new ServiceReclamation();
        ServiceReponse crudReponse = new ServiceReponse();
//////////////////////// REPONSE ////////////////////////////////////
      //  Reclamation reclamation = new Reclamation();
       // reclamation.setIdRec(31);
       //  Reponse reponse = new Reponse(10);
        //  try {
        //   reponse.setContenu("Salut");
        //   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Date dateRep = dateFormat.parse("2030-11-10");
        //  reponse.setDateRep(dateRep);
       // reponse.setReclamation(reclamation);
        //   } catch (ParseException e) {
        //    e.printStackTrace();
        // }
         //  crudReponse.ajouter(reponse);

       //  crudReponse.supprimer(26);
        //// affichage/////
        //  Set<Reponse> reponses = crudReponse.getAll();
        // for (Reponse repo : reponses) {
        // System.out.println(repo); }
        //  crudReponse.getAll();
        /////Modification//////

        //Reponse reponse = crudReponse.getReponseById(21);
        //if (reponse != null) {
         //   reponse.setContenu("Pidev");
          //  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          //  try {
           //     Date nouvelleDate = dateFormat.parse("2024-02-29");
            //    reponse.setDateRep(nouvelleDate);
           // } catch (ParseException e) {
             //   e.printStackTrace(); }
             // crudReponse.modifier(reponse);
            //}
////////////////////////reclamation//////////////////////////////
          //  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          //  try {
          //   Date dateRec = dateFormat.parse("2024-02-19");
          //  Reclamation r = new Reclamation(32, "guirat", 5, dateRec);
          //   crud.ajouter(r);
          //   } catch (ParseException e) {
          //     e.printStackTrace();
          //  }
//-------------Modification------------------
           // Reclamation reclamation = crud.getReclamationById(21);
            //if (reclamation != null) {
            // reclamation.setDescription("Bonjourtibou");
          //  reclamation.setAvis(4);
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Date nouvelleDate = null;
            //try {
            //  nouvelleDate = dateFormat.parse("2020-07-06");
            //reclamation.setDateRec(nouvelleDate);
            //} catch (ParseException e) {
            //e.printStackTrace(); }
          //  crud.modifier(reclamation); }
          //   crud.supprimer(33);
            // ---------Affichage -----------
            //  Set<Reclamation> reclamations = crud.getAll();
            // for (Reclamation recl : reclamations) {
            // System.out.println(recl);}
            //  crud.getAll();


        }
    }
