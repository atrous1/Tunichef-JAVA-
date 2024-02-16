//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

import edu.esprit.entities.Menu;
import edu.esprit.services.ServiceMenu;
import edu.esprit.services.ServiceProduit;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        new ServiceProduit();
        ServiceMenu serviceMenu = new ServiceMenu();
        new Menu(5, "aliiiii", "tunis");
        Menu m2 = new Menu(6, "houssem", "hahhaha");
        serviceMenu.modifiermenu(m2, 1);
        serviceMenu.affichermenu();
        System.out.println("**********************************************");
    }
}
