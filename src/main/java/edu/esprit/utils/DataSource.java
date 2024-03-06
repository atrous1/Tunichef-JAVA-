//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private String url = "jdbc:mysql://localhost:3306/tunichef";
    private String user = "root";
    private String passwd = "";
    private Connection cnx;
    private static DataSource instance;

    private DataSource() {
        try {
            this.cnx = DriverManager.getConnection(this.url, this.user, this.passwd);
            System.out.println("Connected to DB !");
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
        }

    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }

        return instance;
    }

    public Connection getCnx() {
        return this.cnx;
    }
}
