package org.example.Services;
import org.example.entities.Reclamation;
import org.example.entities.Reponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IService<T> {
    public void ajouter(T re) throws SQLException;
    public  void supprimer(int id)throws SQLException;
    public void modifier(T p)throws SQLException;

    List<Reponse> afficher();

   public T getOneById(int id);
    public Set<T> getAll() throws  SQLException;

    // Define the method to send the SMS message
    void sendSms(String toPhoneNumber, String messageText);
}
