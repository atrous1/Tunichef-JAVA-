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
        public void modifier(Evenement e) {

            /*String req = "UPDATE evenement SET eventName = ?, eventDate = ?, description = ? WHERE eventid = ?";
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setString(1, e.getEventName());
            pst.setDate(2, e.getEventDate());
            pst.setString(3, e.getDescription());
            pst.setInt(4, e.getEventId());
            pst.executeUpdate();*/


            try {
                String req = "UPDATE evenement SET eventName = '" + e.getEventName() + "', eventDate = '" + e.getEventDate() + "', description = '" + e.getDescription() +  "' WHERE eventid = " + e.getEventId();
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("evenement updated !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

        @Override
        public void supprimer(int id) {
            try {
                String req = "DELETE FROM evenement WHERE eventId = " + id;
                Statement st = cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("evenement deleted !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

        @Override
        public Evenement getOneById(int id) {
                Evenement event = null;
                try {
                    String req = "SELECT * FROM evenement WHERE eventId = " + id;
                    Statement st = cnx.createStatement();
                    ResultSet rs = st.executeQuery(req);
                    if (rs.next()) {
                        event = new Evenement(rs.getInt("eventId"), rs.getString("eventName"), rs.getString("eventDate"), rs.getString("description"));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                return event;
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




