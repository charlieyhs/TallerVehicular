package com.co.unad.tallervehicular.conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexionbd {
    private static final String URL = "jdbc:mysql://localhost:3306/tallervehicular";
    private static final String USER = "root"; 
    private static final String PASSWORD = "Elmundodeplaton2*"; 
    
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }
}
