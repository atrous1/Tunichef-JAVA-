package Services;
import Entities.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public void ajouter(T re) throws SQLException;
    public void supprimer(int id)throws SQLException;
    public void modifier(T p)throws SQLException;

    List<Reponse> afficher();

    public T getOneById(int id);
    public Set<T> getAll() throws  SQLException;

    // Define the method to send the SMS message
    void sendSms(String toPhoneNumber, String messageText);
    public void ajouterUser(User user)throws SQLException;
    public void modifierUser(User user, int id);
    public void supprimerUser(int id);
    public List<User> afficherUser();
    public boolean test_NumTelWithPrefix(String numtell);
    public boolean test_num_telephonique(String numtel);
    public boolean test_Tel(String numtel);
    public boolean test_Email(String mail);
    public List<User> rechercheUser(int id);
    public boolean verfier_mail(String mail);
    }
