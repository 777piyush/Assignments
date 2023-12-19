package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hmbank";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getDBConn() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Connected to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void main(String args[]) {
    	try {
			System.out.println(getDBConn());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
