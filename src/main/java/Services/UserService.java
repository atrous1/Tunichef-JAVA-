package Services;

import Entities.Role;
import Entities.User;
import Utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService implements IService{

    Connection conn = DataSource.getInstance().getCnx();
    @Override
    public void ajouterUser(User user) throws SQLException {

        String query = "INSERT INTO user (nom, prenom, numtel,email,password,role,image) VALUES (?, ?, ?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, user.getNom());
        preparedStatement.setString(2, user.getPrenom());
        preparedStatement.setInt(3, user.getNumtel());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setString(6, user.getRole().toString());
        preparedStatement.setString(7, user.getImage());
        preparedStatement.executeUpdate();
        System.out.println("user ajouté");

    }
    @Override
    public void modifierUser(User user, int id) {
        try {
            String query = "UPDATE `user` SET `nom` = '" + user.getNom() + "', `prenom` = '" + user.getPrenom() + "', `numtel` = '" + user.getNumtel()  + "', `email` = '" + user.getEmail()  + "', `password` = '" + user.getPassword()  + "', `image` = '" + user.getImage() + "', `role` = '" + user.getRole()+  "' WHERE `user`.`id` = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            System.out.println("user updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerUser(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("user deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> afficherUser() {
        List<User> list = new ArrayList<>();
        try {
            String query = "Select * from user";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(query);
            while (RS.next()) {
                User user = new User();
                user.setId(RS.getInt(1));
                user.setNom(RS.getString(2));
                user.setPrenom(RS.getString(3));
                user.setNumtel(RS.getInt(4));
                user.setEmail(RS.getString(5));
                user.setPassword(RS.getString(6));
                user.setRole(Role.valueOf(RS.getString(7)));
                user.setImage(RS.getString(8));

                System.out.println(user.getImage());
                list.add(user);
                System.out.println(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public boolean test_num_telephonique(String numtel) {
        int i;
        String[] tab = {"0", "1", "4", "6", "8"};
        for (i = 0; i < tab.length; i++) {
            if (numtel.charAt(0) == tab[i].charAt(0)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean test_Tel(String numtel) {
        int i;

        if (numtel.length() != 8) {
            return false;
        }

        for (i = 0; i < numtel.length(); i++) {

            if ((!(numtel.charAt(i) >= '0' && numtel.charAt(i) <= '9')) || (test_num_telephonique(numtel) == false)) {
                return false;
            }

        }
        return true;
    }

    @Override
    public boolean test_Email(String mail) {
        int test = 0;
        int position = 0;
        int test2 = 0;
        String[] tab = {"/", ";", ",", ":", "'", "&", "=", ">", "-", "_", "+", " ","!"};

        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == "@".charAt(0)) {
                test++;
                position = i;
            }

        }
        for (int k = 0; k < mail.length(); k++) {

            for (String tab1 : tab) {
                if (mail.charAt(k) == tab1.charAt(0)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < mail.length(); i++) {
            if ((test == 1) && (mail.charAt(i) == ".".charAt(0))) {

                if (((mail.length() > i + 2) && (i > position + 4))) {
                    for (int j = position; j < mail.length(); j++) {
                        if (mail.charAt(j) == ".".charAt(0)) {
                            test2++;

                        }
                    }
                    if (test2 > 1) {
                        return false;
                    }

                    return true;
                }

            }

        }
        return false;
    }

    @Override
    public List<User> rechercheUser(int id) {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user` WHERE id= " + id;
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                User user = new User();
                user.setId(RS.getInt("id"));
                user.setNom(RS.getString("nom"));
                user.setPrenom(RS.getString("prenom"));
                user.setNumtel(RS.getInt("numtel"));
                user.setEmail(RS.getString("email"));
                user.setPassword(RS.getString("password"));
                user.setRole(Role.valueOf(RS.getString("role")));
                user.setImage(RS.getString("image"));

                System.out.println(user);
                list.add(user);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return list;
    }

    @Override
    public boolean verfier_mail(String mail) {
        Statement stm = null;
        ResultSet rst = null;

        try {
            stm = conn.createStatement();
            String query = "SELECT * FROM user WHERE email='" + mail + "'";
            rst = stm.executeQuery(query);
            if (rst.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

    public User rechercheUserByEmail(String email) throws SQLException {
        User user = null;
        String req = "SELECT * FROM `user` WHERE email= ?";

        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, email);
            ResultSet RS = ps.executeQuery();

            if (RS.next()) {
                user = new User();
                user.setId(RS.getInt("id"));
                user.setNom(RS.getString("nom"));
                user.setPrenom(RS.getString("prenom"));
                user.setNumtel(RS.getInt("numtel"));
                user.setEmail(RS.getString("email"));
                user.setPassword(RS.getString("password"));
                user.setImage(RS.getString("image"));
                System.out.println(user);
            }
        }

        return user;
    }

    public void modifier_password(int id, String password) {

        Statement stm;
        try {
            stm = conn.createStatement();

            String query = "UPDATE user SET password='" + password + "' WHERE id= " + id;
            stm.executeUpdate(query);
            System.out.println("password updated");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
