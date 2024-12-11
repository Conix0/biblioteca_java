package com.biblioteca.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class enlace {

    private static final String URL = "jdbc:sqlite:D:/Usuario/Documents/DB SQLite/biblioteca.db";
   
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Conexión exitosa a SQLite.");
        } catch (SQLException e) {
            System.out.println("Error en la conexión a SQLite: " + e.getMessage());
        }
        return connection;
    }
}



