package org.example.Services;
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

