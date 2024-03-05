package org.example.Services;
//import com.google.protobuf.Message;
import com.twilio.Twilio;
import com.twilio.rest.messaging.v1.service.PhoneNumber;
import org.example.entities.Reponse;
import org.example.utils.DataSource;
import java.sql.*;
import org.example.entities.Reclamation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.twilio.rest.api.v2010.account.Message;


import static com.google.protobuf.Message.*;


public class ServiceReclamation implements IService<Reclamation> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation r)throws SQLException {
        String req = "INSERT INTO `reclamation`(`idRec`,`idUser`, `Description`,`Avis`,`DateRec`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getIdRec());
            ps.setInt(2, r.getIdUser());
            ps.setString(3, r.getDescription());
            ps.setInt(4, r.getAvis());
            ps.setDate(5, new java.sql.Date(r.getDateRec().getTime()));
            ps.executeUpdate();
            System.out.println("Reclamation ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void modifier(Reclamation exp) {

        String req = "UPDATE reclamation SET  Description=?, Avis=? WHERE idRec=?";
        try{
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, exp.getDescription());
            ps.setInt(2, exp.getAvis());
            ps.setInt(3, exp.getIdRec());
            ps.executeUpdate();
            System.out.println("Reclamation modifiée avec succès  !");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<Reponse> afficher()
    {
        return null;
    }

    @Override
    public void supprimer(int idRec) {


        String req = "DELETE FROM reclamation WHERE idRec=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idRec);
            ps.executeUpdate();
            System.out.println("Reclamation Supprimée avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<Reclamation> getAll()throws SQLException {
        Set<Reclamation> reclamations = new HashSet<>();
        String req = " SELECT idUser, Description, Avis, DateRec FROM reclamation";

        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                int idUser = res.getInt(1);
                String Description = res.getString(2);
                int Avis = res.getInt(3);
                Date DateRec=(res.getDate(4));
                Reclamation exp = new Reclamation( idUser, Description,Avis,DateRec);
                reclamations.add(exp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reclamations;
    }


    @Override
    public Reclamation getOneById(int id) {
        return null;

    }



    public Reclamation getReclamationById(int idRec) {
        Reclamation reclamation = null;
        String req = "SELECT * FROM reclamation WHERE idRec=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, idRec);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idUser = rs.getInt("idUser");
                    String description = rs.getString("Description");
                    int avis = rs.getInt("Avis");
                    Date DateRec=rs.getDate("DateRec");
                    reclamation = new Reclamation(idRec, idUser, description, avis,DateRec);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reclamation;
    }
    private static final String ACCOUNT_SID = "AC5f30c61a472e288900d2e1fb14d3b5b3";
    private static final String AUTH_TOKEN = "348eab68dd79dd13eb813691337783d5";
    private static final String TWILIO_PHONE_NUMBER = "+17573472962";
    private static final String toPhoneNumber = "+20427036";
    // Define the method to send the SMS message
    @Override
    public void sendSms(String toPhoneNumber, String messageText) {
        // Initialize the Twilio API client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Set the phone numbers for the SMS message
        com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber(toPhoneNumber);
        com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber(TWILIO_PHONE_NUMBER);

        // Use the Message creator to send the SMS message
        Message message = Message.creator(
                to,
                from,
                messageText
        ).create();

        // Print a confirmation message
        System.out.println("Message SID: " + message.getSid());
    }
}






