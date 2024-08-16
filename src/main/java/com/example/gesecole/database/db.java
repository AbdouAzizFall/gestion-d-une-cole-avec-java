package com.example.gesecole.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class db {

    private final String server = "localhost";

    private final String username = "root";

    private final String password = "";
    private final String bd = "gesecole";

    private final String url = ""
            + "jdbc:mysql://"+server+":3306/"+bd;

    private Connection conn;
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(url, username,password);
            System.out.println("Connecté");
        } catch (Exception ex) {
            ex.printStackTrace();
            conn = null;
            System.out.println("Erreur");
        }

        return conn;
    }



}
