package com.revature.p0.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;
    private static Connection connection;
    private static String connPropsFilePath;

    static {
        connPropsFilePath = "src/main/resources/jdbc.properties";
    }

    private ConnectionFactory() {
        Properties props = new Properties();
        try (FileReader jdbcPropFile = new FileReader(connPropsFilePath)){
            props.load(jdbcPropFile);
            Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://"
                        + props.getProperty("host") + ":"
                        + props.getProperty("port") + "/"
                        + props.getProperty("dbname")
                        + "?currentSchema=" + props.getProperty("schemaname"),
                props.getProperty("username"),
                props.getProperty("password"));
            Class.forName(props.getProperty("driver"));
            if (connection != null) {
                //System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            ConnectionFactory.connection = connection;

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (IOException e) {
            System.out.println("File not found: jdbc.properties. " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    //depricate this
//    public static ConnectionFactory createConnection(String filePath) {
//        if(connectionFactory == null || !connPropsFilePath.equals(filePath)) {
//            connPropsFilePath = filePath;
//            Properties props = new Properties();
//            try (FileReader jdbcPropFile = new FileReader(filePath);) {
//                props.load(jdbcPropFile);
//            } catch (IOException e) {
//                System.out.println("File not found: jdbc.properties. " + e);
//            } catch (Exception e) {
//                System.out.println("Generic exception: " + e);
//            }
//            connectionFactory = new ConnectionFactory(props);
//        }
//
//        return connectionFactory;
//    }

    public static Connection getConnection() {
        if(connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            connectionFactory = null;
        } catch (SQLException e) {
            System.out.println("SQL Exception while closing conneciton: " + e);
        }
    }

    public void finalize() {
        this.closeConnection();
    }

}
