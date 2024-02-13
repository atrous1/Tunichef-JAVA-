package edu.esprit.service;
import edu.esprit.entities.Evenement;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import edu.esprit.util.DataSource;

    public class ServiceEvenement implements IService<Evenement> {

        Connection cnx = DataSource.getInstance().getCnx();

        @Override
        public void ajouter(Evenement e) {
        /*String req = "INSERT INTO `personne`(`nom`, `prenom`) VALUES ('"+p.getNom()+"','"+p.getPrenom()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

            String req = "INSERT INTO `evenement`(`eventName`, `eventDate`, `description`) VALUES (?,?,?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1,e.getEventName());
                ps.setString(2, e.getEventDate());
                ps.setString(3, e.getDescription());

                ps.executeUpdate();
                System.out.println("event added !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void modifier(Evenement p) {

        }

        @Override
        public void supprimer(int id) {

        }

        @Override
        public Evenement getOneById(int id) {
            return null;
        }

        @Override
        public Set<Evenement> getAll() {
            Set<Evenement> evenements = new HashSet<>();

            String req = "Select * from evenement";
            try {
                Statement st = cnx.createStatement();
                ResultSet res = st.executeQuery(req);
                while (res.next()){
                    int id = res.getInt(1);
                    String eventName = res.getString("eventName");
                    String eventDate = res.getString("eventDate");
                    String description = res.getString("description");
                    Evenement event = new Evenement(eventName, String.valueOf(Date.valueOf(eventDate)),description);
                    evenements.add(event);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


            return evenements;
        }
    }




