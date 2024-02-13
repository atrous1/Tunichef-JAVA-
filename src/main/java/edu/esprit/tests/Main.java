package edu.esprit.tests;

import edu.esprit.entities.Evenement;
import edu.esprit.service.ServiceEvenement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ServiceEvenement se = new ServiceEvenement();
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormatted = sdf.format(date);
//        System.out.println(String.valueOf(new Date()));
        Evenement ev = new Evenement("dfsdf",dateFormatted,"fffxdfxdfx");
//        se.ajouter(ev);

        System.out.println(se.getAll());
    }

}
