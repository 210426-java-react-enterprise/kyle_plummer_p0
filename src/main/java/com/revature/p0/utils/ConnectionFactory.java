package com.revature.p0.utils;

import java.io.FileNotFoundException;
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

    private static String testStr;


    private ConnectionFactory(Properties props) {
        //private constructor

        System.out.println("DEBUG: jdbc:postgresql://"
                        + props.getProperty("host") + ":"
                        + props.getProperty("port") + "/"
                        + props.getProperty("dbname") + ", "
                + props.getProperty("username") + ", "
                + props.getProperty("password"));

        try {
            Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://"
                        + props.getProperty("host") + ":"
                        + props.getProperty("port") + "/"
                        + props.getProperty("dbname"),
                props.getProperty("username"),
                props.getProperty("password"));
            Class.forName("org.postgresql.Driver");
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            ConnectionFactory.connection = connection;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //src/main/resources/jdbc.properties
    public static ConnectionFactory createConnection(String filePath) {
        if(connectionFactory == null || !connPropsFilePath.equals(filePath)) {
            connPropsFilePath = filePath;
            Properties props = new Properties();
            try (FileReader jdbcPropFile = new FileReader(filePath);) {
                props.load(jdbcPropFile);
            } catch (IOException e) {
                System.out.println("File not found: jdbc.properties. " + e);
            } catch (Exception e) {
                System.out.println("Generic exception: " + e);
            }
            connectionFactory = new ConnectionFactory(props);
        }

        return connectionFactory;
    }

    public Connection getConnection() {
        return connection;
    }

    public void finalize() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception while closing conneciton: " + e);
        }
    }
}
