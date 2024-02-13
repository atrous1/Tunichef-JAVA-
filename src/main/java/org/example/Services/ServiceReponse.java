package org.example.Services;

import org.example.entities.Reclamation;
import org.example.entities.Reponse;
import org.example.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class ServiceReponse  implements IService <Reponse>{
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Reponse r) {



            String req = "INSERT INTO `reponse_reclamation`(`idRep`,`Contenu`, `idRec`) VALUES (?,?,?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, r.getIdRep());

                ps.setInt(2, r.getIdRec());
                ps.setString(3, r.getContenu());
                ps.executeUpdate();
                System.out.println("reponse added !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }



    @Override
    public List<Reponse> afficher() {
        return null;
    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public Reponse getOneById(int id) {
        return null;
    }

    @Override
    public Set<Reponse> getAll() {
        return null;
    }

}
