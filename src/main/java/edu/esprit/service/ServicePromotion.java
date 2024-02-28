package edu.esprit.service;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Promotion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.esprit.util.DataSource;
public class ServicePromotion implements IService<Promotion> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Promotion P) throws SQLException{
        String req = "INSERT INTO `promotion`(`promotionName`, `prix_promo`, `expirationDate`, `fk_event`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,P.getPromotionName());
            ps.setDouble(2, P.getPrix_Promo());
            java.util.Date d = P.getExpirationDate();
            java.sql.Date sqlDate = new java.sql.Date(d.getTime());
            ps.setDate(3, sqlDate);
            ps.setInt(4, P.getEvenement().getEventId());


            ps.executeUpdate();
            System.out.println("Promotion added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Promotion P, int id) {


        try {
            String req = "UPDATE promotion SET promotionName = '" + P.getPromotionName() + "', prix_promo = '" + P.getPrix_Promo() + "', expirationDate = '" + P.getExpirationDate() +  "' WHERE promotionId = " + id;
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
            String req = "DELETE FROM promotion WHERE promotionId = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Promotion deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }



    @Override
    public Promotion getOneById(int id) {
        Promotion P = new Promotion();

        try {
            String req = "SELECT * FROM promotion WHERE promotionId= " + id;
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                P.setPromotionId(RS.getInt("promotionId"));
                P.setPromotionName(RS.getString("promotionName"));
                P.setPrix_Promo(RS.getDouble("prix_promo"));
                P.setExpirationDate(RS.getDate("expirationDate"));

                ServiceEvenement se = new ServiceEvenement();
                Evenement evenement = se.getOneById(RS.getInt("fk_event"));
                P.setEvenement(evenement);
                System.out.println(P);

            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return P;
    }

    @Override
    public Set<Promotion> getAll() {
        Set<Promotion> promotions = new HashSet<>();

        String req = "Select * from promotion";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()){
                int id = res.getInt(1);
                String promotionName = res.getString("promotionName");
                double prix_promo = res.getDouble("prix_promo");
                java.util.Date expirationDate = res.getDate("expirationDate");
                Promotion promo = new Promotion(id, promotionName, prix_promo, expirationDate,null);
                promotions.add(promo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return promotions;
    }

    public List<Promotion> rechPromo(int id) {
        List<Promotion> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM promotion WHERE `promotionId` = " + id;
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Promotion P = new Promotion();
                P.setPromotionId(RS.getInt("promotionId"));
                P.setPromotionName(RS.getString("promotionName"));
                P.setPrix_Promo(RS.getDouble("prix_promo"));
                P.setExpirationDate(RS.getDate("expirationDate"));

                list.add(P);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

    public List<Promotion> GetPromoEvent(int id) {
        List<Promotion> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM promotion WHERE `fk_event` = " + id;
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Promotion P = new Promotion();
                P.setPromotionId(RS.getInt("promotionId"));
                P.setPromotionName(RS.getString("promotionName"));
                P.setPrix_Promo(RS.getDouble("prix_promo"));
                P.setExpirationDate(RS.getDate("expirationDate"));

                list.add(P);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }


}
