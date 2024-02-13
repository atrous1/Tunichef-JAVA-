package org.example;

import org.example.Services.ServiceReclamation;

import org.example.Services.ServiceReponse;
import org.example.entities.Reclamation;
import org.example.entities.Reponse;
import org.example.utils.DataSource;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Entrée with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Maj+F10 or click the green arrow button in the gutter to run the code.
        //for (int i = 1; i <= 5; i++) {

            // Press Maj+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            //System.out.println("i = " + i);
        DataSource connexion = DataSource.getInstance();
        ServiceReclamation crud= new ServiceReclamation();
        ServiceReponse crud2= new ServiceReponse();
       // ServiceReponse crud2= new ServiceReponse();

        // Reclamation r = new Reclamation(68, 1,"mahlouka", result.getInt("Avis"));
      // Reclamation r = new Reclamation(51, "touuuuunsi",5);
       Reponse r= new Reponse(13,"ff",0);


       crud2.ajouter( r);
      // crud.getAll();
        //crud.modifierreclamation(r,6);
     // crud.supprimer(77);

        }
    }
