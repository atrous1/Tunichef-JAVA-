package edu.esprit.service;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Promotion;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.esprit.util.DataSource;
public abstract class ServicePromotion implements IService<Promotion> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Promotion P) {
        String req = "INSERT INTO `promotion`(`promotionName`, `discount`, `expirationDate`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,P.getPromotionName());
            ps.setDouble(2,P.getDiscount());
            ps.setString(3,P.getExpirationDate());


            ps.executeUpdate();
            System.out.println("Promotion added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Promotion P, int id) {


        try {
            String req = "UPDATE evenement SET promotionName = '" + P.getPromotionName() + "', discount = '" + P.getDiscount() + "', expirationDate = '" + P.getExpirationDate() +  "' WHERE promotionId  = " + P.getPromotionId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM evenement WHERE promotionId = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Promotion getOneById(int id) {
        Promotion promo = null;
        try {
            String req = "SELECT * FROM evenement WHERE promotionId = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                promo = new Promotion(rs.getInt("promotionId"), rs.getString("promotionName"), rs.getDouble("discount"), rs.getString("expirationDate"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return promo;
    }

    @Override
    public Set<Promotion> getAll() {
        Set<Promotion> promos = new HashSet<>();

        String req = "Select * from evenement";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                int id = res.getInt(1);
                String promotionName = res.getString("promotionName");
                double discount = res.getDouble("discount");
                String expirationDate = res.getString("expirationDate");
                Promotion promo = new Promotion(promotionName,discount,String.valueOf(Date.valueOf(expirationDate)));
                promos.add(promo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return promos;
    }


}
