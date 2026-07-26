package com.contactbook.contactbook.database;

import com.contactbook.contactbook.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private Connection connection;
    private DatabaseConnection() {

    }

    /**
     * This method hands you back the one and only DatabaseConnection object that exists in the whole app.
     * If it doesn't exist yet, it creates it here.
     * If it already exists, you just get the same one back
     */
    public static synchronized DatabaseConnection getInstance() {
        if(instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try{
            //Load MYSQL DRIVR
            Class.forName(DatabaseConfig.DB_DRIVER);

            //Create a new connection
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
            return connection;
        }catch(ClassNotFoundException e){
            throw new SQLException("MYSQL JDBC DRIVER NOT FOUND: " + e.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        if(connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }

    public boolean testConnection(){
        try{
            Connection test = getConnection();
            if(test != null && !test.isClosed()) {
                return true;
            }
        }catch(SQLException e){
            System.err.println("✗ Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
