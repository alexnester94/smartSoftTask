package ru.nesteria.db.dbFunctions;

import java.sql.*;

public class DBConnect {
    private static String JDBC_CONNECTION_URL = "jdbc:postgresql://localhost:5432/nester";


    public static Connection getCon() throws SQLException {
        Connection connection = null;
        System.out.println("connection started");
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_CONNECTION_URL, "postgres", "49yhiS49");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("connection ready");
        return connection;
    }



}
