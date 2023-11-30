/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sobtech.crud.data;

import java.sql.*;

/**
 *
 * @author HP
 */

public class MySQLConnection {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javafx_crud";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection connection = null;

    public static Connection getConnection()
    {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //////////////////////// How to use ////////////////////////////
    /*Connection connection = MySQLConnection.getConnection();

    // Execute SQL queries
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table");

    // Process the results
    while (resultSet.next()) {
        // Get column values
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        // Do something with the data
        System.out.println("ID: " + id + ", Name: " + name);
    }

    // Close the connection
    MySQLConnection.closeConnection();*/
    ///////////////////////////////////////////////////////////
}