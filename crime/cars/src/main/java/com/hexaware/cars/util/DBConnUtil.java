package com.hexaware.cars.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cars?useSSL=false&serverTimezone=UTC"; // Updated URL
    private static final String DB_USERNAME = "root";  // Your MySQL username
    private static final String DB_PASSWORD = "Zainab@072001";  // Your MySQL password

    // Method to get the connection
    public static Connection getConnection() {
        try {
            // Load the JDBC driver (MySQL)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); // Pass the password here
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
