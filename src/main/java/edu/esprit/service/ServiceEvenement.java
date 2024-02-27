package edu.esprit.service;
import edu.esprit.entities.Evenement;
import edu.esprit.entities.Promotion;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.esprit.entities.Promotion;
import edu.esprit.util.DataSource;

    public class ServiceEvenement implements IService<Evenement> {

        Connection cnx = DataSource.getInstance().getCnx();

        @Override
        public void ajouter(Evenement e) throws SQLException{
            String req = "INSERT INTO `evenement`(`eventName`, `eventDate`, `description`) VALUES (?,?,?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1,e.getEventName());
                Date d = e.getEventDate();
                java.sql.Date sqlDate = new java.sql.Date(d.getTime());
                ps.setDate(2, sqlDate);
                ps.setString(3, e.getDescription());

                ps.executeUpdate();
                System.out.println("event added !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void modifier(Evenement e, int id) {

            /*String req = "UPDATE evenement SET eventName = ?, eventDate = ?, description = ? WHERE eventid = ?";
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setString(1, e.getEventName());
            pst.setDate(2, e.getEventDate());
            pst.setString(3, e.getDescription());
            pst.setInt(4, e.getEventId());
            pst.executeUpdate();*/


            try {
                String req = "UPDATE evenement SET eventName = '" + e.getEventName() + "', eventDate = '" + e.getEventDate() + "', description = '" + e.getDescription() +  "' WHERE eventid = " + id;
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
            Evenement r = new Evenement();
            try {
                String req = "SELECT * FROM evenement WHERE eventId= " + id;
                Statement st = cnx.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                    r.setEventId(RS.getInt("eventId"));
                    r.setEventName(RS.getString("eventName"));
                    r.setEventDate(RS.getDate("eventDate"));
                    r.setDescription(RS.getString("description"));

                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }

            return r;
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
                    Date eventDate = res.getDate("eventDate");
                    String description = res.getString("description");
                    Evenement event = new Evenement(id, eventName, eventDate, description, null);
                    evenements.add(event);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


            return evenements;
        }

        @Override
        public void afficter(Evenement e, List<Promotion> promotions) {

        }

        public List<Evenement> rechEvent(int id) {
            List<Evenement> list = new ArrayList<>();
            try {
                String req = "SELECT * FROM evenement WHERE `eventId` = " + id;
                Statement st = cnx.createStatement();
                ResultSet RS = st.executeQuery(req);
                while (RS.next()) {
                    Evenement r = new Evenement();
                    r.setEventId(RS.getInt("eventId"));
                    r.setEventName(RS.getString("eventName"));
                    r.setEventDate(RS.getDate("eventDate"));
                    r.setDescription(RS.getString("description"));

                    list.add(r);
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }

            return list;
        }


    }




