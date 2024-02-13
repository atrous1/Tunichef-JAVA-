package org.example.Services;
import org.example.entities.Reponse;
import org.example.utils.DataSource;
import java.sql.*;
import org.example.entities.Reclamation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ServiceReclamation implements IService<Reclamation> {
    //Statement ste;
    // Connection conn = DataSource.getInstance().getCnx();
    Connection cnx = DataSource.getInstance().getCnx();


    @Override
    public void ajouter(Reclamation r) {

        String req = "INSERT INTO `reclamation`(`idRec`,`id_user`, `Description`,`Avis`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getIdRec());
            ps.setInt(2, r.getId_user());
            ps.setString(3, r.getDescription());
            ps.setInt(4, r.getAvis());
            ps.executeUpdate();
            System.out.println("reclamation added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Reponse> afficher() {
        return null;
    }



    @Override
    public void supprimer(int id_user) {


        String req = "DELETE FROM reclamation WHERE id_user=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_user);
            ps.executeUpdate();
            System.out.println("Reclamation Suprimee !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    @Override
    public Set<Reclamation> getAll() {
        Set<Reclamation> reclamations = new HashSet<>();
        String req = "SELECT * FROM reclamation";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                int idRec = res.getInt(1);
                int id_user = res.getInt(2);


                String Description = res.getString(4);
                int Avis = res.getInt(3);

                Reclamation exp = new Reclamation(idRec, id_user, Description,Avis);
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
}
/*


}
/*
        @Override
        public List<Reclamation> afficher () {
            List<Reclamation> pers = new ArrayList<>();
            try {
                String req = "SELECT * FROM reclamation";
                ste = conn.createStatement();
                ResultSet result = ste.executeQuery(req);
                System.out.println(result);
                while (result.next()) {
                    Reclamation resultPerson = new Reclamation(result.getInt("id_user"), result.getInt("Avis"), result.getString("description"));
                    pers.add(resultPerson);
                }
                System.out.println(pers);

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return pers;
        }





    }
*/



