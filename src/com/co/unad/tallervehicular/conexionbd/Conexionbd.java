package com.co.unad.tallervehicular.conexionbd;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexionbd {
    
    public Connection getConnection() {
        Connection connection = null;
        try {
            Properties config = loadProperties("src/resources/config.properties");
            String URL = config.getProperty("URL_BD");
            String USER = config.getProperty("USER_BD");
            String PASSWORD = config.getProperty("PASSWORD_BD");
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }
    private Properties loadProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
        }
        return properties;
    }
}
