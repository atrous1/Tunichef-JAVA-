//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.esprit.services;

import edu.esprit.entities.Produit;
import edu.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceProduit {
    private Connection cnx = DataSource.getInstance().getCnx();

    public ServiceProduit() {
    }

    public void ajouterProduit(Produit p) {
        try {
            String query = "INSERT INTO produit(nom_produit,description_produit,image_produit,prix_produit) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = this.cnx.prepareStatement(query, 1);
            preparedStatement.setString(1, p.getNom_produit());
            preparedStatement.setString(2, p.getDescription_produit());
            preparedStatement.setString(3, p.getImage_produit());
            preparedStatement.setDouble(4, p.getPrix_produit());
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                p.setId_produit(rs.getInt(1));
            }

            preparedStatement.executeUpdate();
            System.out.print("succes!!!");
        } catch (SQLException var5) {
            System.err.println(var5.getMessage());
        }

    }

    public void modifier(Produit produit, int id) {
        String var10000 = produit.getNom_produit();
        String req = "UPDATE produit set nom_produit = '" + var10000 + "', description_produit = '" + produit.getDescription_produit() + "', image_produit = '" + produit.getImage_produit() + "', prix_produit = '" + produit.getPrix_produit() + "' WHERE  `produit`.`id_produit` = " + id;

        try {
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("produit modifié !");
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
        }

    }

    public void supprimerProduit(int id_produit) {
        try {
            String req = "DELETE FROM `produit` WHERE id_produit = " + id_produit;
            Statement st = this.cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("produit deleted !");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public List<Produit> afficherProduits() {
        List<Produit> listproduits = new ArrayList();

        try {
            String req = "SELECT * FROM produit";
            Statement st = this.cnx.createStatement();
            ResultSet CA = st.executeQuery(req);

            while(CA.next()) {
                Produit p = new Produit();
                p.setId_produit(CA.getInt("id_produit"));
                p.setNom_produit(CA.getString("nom_produit"));
                p.setDescription_produit(CA.getString("description_produit"));
                p.setImage_produit(CA.getString("image_produit"));
                p.setPrix_produit(CA.getDouble("prix_produit"));
                listproduits.add(p);
                System.out.println(p);
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return listproduits;
    }
}
