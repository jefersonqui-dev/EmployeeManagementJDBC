package com.devsenior.jquiguantar.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    public static Connection getConnection() throws SQLException {
        var url = "jdbc:postgresql://localhost:5432/RH";
        var user = "postgres";
        var pass = "";
        return DriverManager.getConnection(url, user, pass);
    }
}
