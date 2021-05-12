package com.revature.p0.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * This factory maintains connection to the database, and returns connection objects as needed.
 *
 */
public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;
    private static Connection connection;
    private static String connPropsFilePath;

    static {
        connPropsFilePath = "src/main/resources/jdbc.properties";
    }

    /**
     * Private constructor. Attempts to establish a connection with database based on parameters
     * found in properties file. If an exception is thrown during this process, application
     * quit() method is invoked. If successful connection object is stored and served
     * by getConnection() method.
     */
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
            FileLogger.getFileLogger().writeExceptionToFile(e);
            System.out.println("Unable to connect to database. Quitting...");
            App.getApp().quitApp();
        } catch (ClassNotFoundException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
            System.out.println("JDBC driver not found. Cannot connect. Quitting...");
            App.getApp().quitApp();
        } catch (IOException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
            System.out.println("Database info not found. Quitting...");
            App.getApp().quitApp();
        }
    }


    /**
     * Returns connection reference used to access database. If connection not yet
     * established, invokes private constructor.
     * @return reference to connection object used to access database
     */
    public static Connection getConnection() {
        if(connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connection;
    }

    /**
     * Method to force connection to close. Gracefully terminates connection with database.
     * Can be used to cause next call to getConnection() to invoke constructor
     * and establish a new connection to database.
     */
    public void closeConnection() {
        try {
            connection.close();
            connectionFactory = null;
        } catch (SQLException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    /**
     * calls closeConnection() upon destruction of this object to gracefully terminate
     * connection before garbage collection
     */
    public void finalize() {
        this.closeConnection();
    }

}
