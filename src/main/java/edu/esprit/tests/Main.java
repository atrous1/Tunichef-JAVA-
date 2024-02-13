package edu.esprit.tests;

import edu.esprit.entities.Evenement;
import edu.esprit.service.ServiceEvenement;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ServiceEvenement se = new ServiceEvenement();
        Date date = new Date();

         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         String dateFormatted = sdf.format(date);
       //  System.out.println(String.valueOf(new Date()));
      //  Evenement ev = new Evenement("dfsdf",dateFormatted,"fffxdfxdfx");
//        se.ajouter(ev);

        System.out.print("ID de l evenement à modifier : ");
        int idToUpdate = Scanner.nextInt();
        se.getOneById(idToUpdate);
        if (evenementToUpdate != null) {
            System.out.print("Nouveau nom de la réservation : ");
            reservationToUpdate.setNomReservation(scanner.next());
            System.out.print("Nouveau numéro de table : ");
            reservationToUpdate.setNumTable(scanner.nextInt());
            System.out.print("Nouveau numéro de téléphone : ");
            reservationToUpdate.setNumTel(scanner.next());
            System.out.print("Nouveau calendrier (YYYY-MM-DD) : ");
            String newDateString = scanner.next();
            reservationToUpdate.setCalendrier(java.sql.Date.valueOf(newDateString));
            System.out.print("Nouvel ID de l'utilisateur : ");
            reservationToUpdate.setIdUser(scanner.nextInt());

            reservationService.modifier(reservationToUpdate);
        } else {
            System.out.println("Réservation non trouvée.");
        }



       // System.out.println(se.getAll());

       // System.out.println(se.getOneById(2));

        //System.out.println(se.supprimer(1););


    }

}
