package edu.esprit.services;

import edu.esprit.entities.Menu;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceMenu {
    private Connection cnx = DataSource.getInstance().getCnx();

    public ServiceMenu() {
    }

    public void ajouterMenu(Menu m) {
        try {
            String query = "INSERT INTO menu(nbr_page,categorie,origine) VALUES (?,?,?)";
            PreparedStatement preparedStatement = this.cnx.prepareStatement(query, 1);
            preparedStatement.setInt(1, m.getNbr_page());
            preparedStatement.setString(2, m.getCategorie());
            preparedStatement.setString(3, m.getOrigine());
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                m.setId_menu(rs.getInt(1));
            }

            preparedStatement.executeUpdate();
            System.out.print("succes!!!");
        } catch (SQLException var5) {
            System.err.println(var5.getMessage());
        }

    }

    public void modifiermenu(Menu menu, int id) {
        int var10000 = menu.getNbr_page();
        String req = "UPDATE menu set nbr_page = '" + var10000 + "', categorie = '" + menu.getCategorie() + "', origine = '" + menu.getOrigine() + "' WHERE  `menu`.`id_menu` = " + id;

        try {
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("menu modifié !");
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
        }

    }

    public void supprimermenu(int id_menu) {
        try {
            String req = "DELETE FROM `menu` WHERE id_menu = " + id_menu;
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("menu deleted !");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public List<Menu> affichermenu() {
        List<Menu> listmenu = new ArrayList();

        try {
            String req = "SELECT * FROM menu";
            Statement st = this.cnx.createStatement();
            ResultSet CA = st.executeQuery(req);

            while(CA.next()) {
                Menu m = new Menu();
                m.setId_menu(CA.getInt("id_menu"));
                m.setNbr_page(CA.getInt("nbr_page"));
                m.setCategorie(CA.getString("categorie"));
                m.setOrigine(CA.getString("origine"));
                listmenu.add(m);
                System.out.println(m);
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return listmenu;
    }
}
