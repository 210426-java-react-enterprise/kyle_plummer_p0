package com.revature.p0;

import com.revature.p0.utils.ConnectionFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Driver {
    public static void main(String[] args) {
        Connection conn = ConnectionFactory.createConnection("src/main/resources/jdbc.properties").getConnection();

        try {

            String sql = "select * from quizzard.users where id = 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //pstmt.setString(1, "quizzard_app");
            //pstmt.setString(2, "revature");

            ResultSet rs = pstmt.executeQuery();
            System.out.println("DEBUG: RS");
            while (rs.next()) {
                System.out.println("DEBUG WHILE");
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("password"));
                System.out.println(rs.getString("first_name"));
                System.out.println(rs.getString("last_name"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getInt("age"));
//                user = new AppUser();
//                user.setId(rs.getInt("id"));
//                user.setUsername(rs.getString("username"));
//                user.setPassword(rs.getString("password"));
//                user.setFirstName(rs.getString("first_name"));
//                user.setLastName(rs.getString("last_name"));
//                user.setEmail(rs.getString("email"));
//                user.setAge(rs.getInt("age"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Properties testProps = new Properties();
//        try (FileReader jdbcPropFile = new FileReader("src/main/resources/jdbc.properties");) {
//            testProps.load(jdbcPropFile);
//        } catch (IOException e) {
//            System.out.println("File not found: jdbc.properties.");
//        }
//        try (FileReader propFile = new FileReader("src/main/resources/test.properties");) {
//            testProps.load(propFile);
//        } catch (IOException e) {
//            System.out.println("File not found: jdbc.properties.");
//        }
//        System.out.println("testprops: ");
//        testProps.list(System.out);
//


//        Properties jdbcProperties = new Properties();
//        try (FileReader jdbcPropFile = new FileReader("src/main/resources/jdbc.properties");) {
//            jdbcProperties.load(jdbcPropFile);
//        } catch (IOException e) {
//            System.out.println("File not found: jdbc.properties.");
//        }
//
//        try (Connection conn2 = DriverManager.getConnection(
//                "jdbc:postgresql://"
//                        + jdbcProperties.getProperty("host") + ":"
//                        + jdbcProperties.getProperty("port") + "/"
//                        + jdbcProperties.getProperty("dbname"),
//                jdbcProperties.getProperty("username"),
//                jdbcProperties.getProperty("password"))) {
//            System.out.println("DEBUG2: jdbc:postgresql://"
//                            + jdbcProperties.getProperty("host") + ":"
//                            + jdbcProperties.getProperty("port") + "/"
//                            + jdbcProperties.getProperty("dbname") + ", "
//                    + jdbcProperties.getProperty("username") + ", "
//                    + jdbcProperties.getProperty("password"));
//            Class.forName("org.postgresql.Driver");
//            if (conn2 != null) {
//                System.out.println("Connected to the database!");
//            } else {
//                System.out.println("Failed to make connection!");
//            }
//
//        } catch (SQLException e) {
//            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
