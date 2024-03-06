package Services;

import Entities.Menu;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceMenu {
    private Connection cnx = DataSource.getInstance().getCnx();
    Statement ste;

    public ServiceMenu() {
    }

    public void ajouterMenu(Menu m) throws SQLException {

        String query = "INSERT INTO menu (nbr_page, categorie, origine) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setInt(1, m.getNbr_page());
        preparedStatement.setString(2, m.getCategorie());
        preparedStatement.setString(3, m.getOrigine());
        preparedStatement.executeUpdate();
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

    public List<Menu> rechMenu(int id) {
        List<Menu> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `menu` WHERE id_menu= " + id;
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Menu r = new Menu();
                r.setId_menu(RS.getInt("id_menu"));
                r.setNbr_page(RS.getInt("nbr_page"));
                r.setCategorie(RS.getString("categorie"));
                r.setOrigine(RS.getString("origine"));

                list.add(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT categorie FROM menu";
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String categorie = resultSet.getString("categorie");
                categories.add(categorie);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

}
