package org.example.Services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.messaging.v1.service.PhoneNumber;
import org.example.entities.Reclamation;
import org.example.entities.Reponse;
import org.example.utils.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceReponse  implements IService <Reponse> {
    Connection cnx = DataSource.getInstance().getCnx();


    @Override
    public void ajouter(Reponse r) {

        String req = "INSERT INTO `reponse_reclamation`(`idRep`,`Contenu`, `idRec`,`DateRep`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getIdRep());
            ps.setString(2, r.getContenu());
            ps.setInt(3, r.getReclamation().getIdRec());
            ps.setDate(4, new java.sql.Date(r.getDateRep().getTime()));
            ps.executeUpdate();
            System.out.println("Réponse ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<Reponse> afficher() {
        return null;
    }

    @Override
    public void supprimer(int idRep) {

        String req = "DELETE FROM reponse_reclamation WHERE idRep=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idRep);
            ps.executeUpdate();
            System.out.println("Réponse Supprimée avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Reponse p) {
        String req = "UPDATE reponse_reclamation SET  Contenu=?, DateRep=? WHERE idRep=?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getContenu());
            ps.setDate(2, new java.sql.Date(p.getDateRep().getTime()));
            ps.setInt(3, p.getIdRep());
            ps.executeUpdate();
            System.out.println("Réponse modifiée avec succès  !");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Set<Reponse> getAll() {
        Set<Reponse> reponses = new HashSet<>();
        String req = " SELECT * From reponse_reclamation";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                int idRep = res.getInt("idRep");
                String Contenu = res.getString("Contenu");
                int reclamationidRec = res.getInt("idRec");
                Date DateRep=(res.getDate("DateRep"));
                Reponse reponse = new Reponse(idRep,Contenu,new Reclamation(reclamationidRec),DateRep );
                reponses.add(reponse);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reponses;
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
        com.twilio.rest.api.v2010.account.Message message = Message.creator(
                to,
                from,
                messageText
        ).create();

        // Print a confirmation message
        System.out.println("Message SID: " + message.getSid());
    }

    @Override
        public Reponse getOneById ( int id){
            return null;
        }
    public Reponse getReponseById(int idRep) {
        Reponse reponse = null;
        String req = "SELECT * FROM reponse_reclamation WHERE idRep=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, idRep);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Reclamation reclamation = new ServiceReclamation().getReclamationById(rs.getInt("idRec"));
                    int reclamationidRec = reclamation.getIdRec();
                    String Contenu = rs.getString("Contenu");
                    Date DateRep=rs.getDate("DateRep");
                    reponse = new Reponse(idRep,Contenu, new Reclamation(reclamationidRec), DateRep);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reponse;
    }
}

