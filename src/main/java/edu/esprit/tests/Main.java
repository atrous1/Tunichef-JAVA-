package edu.esprit.tests;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Promotion;
import edu.esprit.service.ServiceEvenement;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        ServiceEvenement se = new ServiceEvenement();
        Date date = new Date();

         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         String dateFormatted = sdf.format(date);
       //  System.out.println(String.valueOf(new Date()));
       // Evenement ev = new Evenement("blackfriday",dateFormatted,"dzijdzijd");
       //se.ajouter(ev);

       // Promotion pr1 = new Promotion("humburger",10.5,dateFormatted);






       // System.out.println(se.getAll());

       // System.out.println(se.getOneById(2));

        //System.out.println(se.supprimer(1););


    }

}
