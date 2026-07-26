package com.contactbook.contactbook.config;

public class DatabaseConfig {
    public static final String DB_HOST = "localhost";
    public static final String DB_PORT = "3306";
    public static final String DB_NAME = "contactbook";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = ""; //empty for root on localhost

    //JDBC URL
    public static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    //MSQL Driver class
    public static final String DB_DRIVER =  "com.mysql.cj.jdbc.Driver";

    //Connection pool settings
    public static final int CONNECTION_POOL_SIZE = 5;
    public static final int CONNECTION_TIMEOUT = 50000; //50 seconds
}
