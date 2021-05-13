package com.revature.p0.daos;

import com.revature.p0.pojos.UserPOJO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Abstract class, contains methods to preform SQL CRUD operations pertaining to user accounts
 *
 * @author Kyle Plummer
 */
public abstract class UserDAO extends DatabaseDAO {

    public static boolean authenticate(UserPOJO user) throws SQLException, NullPointerException {

        String sql = "SELECT user_id, firstname, lastname, streetaddress, zipcode, email, username, password, active " +
                    "FROM users " +
                    "WHERE username = ? " +
                    "AND password = ? ";
        //System.out.println("DEBUGL outgoing query: " + sql + " + username=" + user.getUsername() + " +password=" + user.getPassword());
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("User authenticated!");
            user.setUserID((UUID)rs.getObject("user_id"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setAddress(rs.getString("streetaddress"));
            user.setZipCode(rs.getString("zipcode"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username")); //for testing purposes
            user.setPassword(rs.getString("password")); //for testing purposes
            user.setActive(rs.getBoolean("active"));
            return true;
        }
        return false;
    }

    public static boolean checkUserExists(String username) throws SQLException {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        PreparedStatement pstmt = createStatement(sql, username);

        ResultSet rs = pstmt.executeQuery();
        return(rs.next());
//        if(!rs.next()) {
//            return false;
//        }
//
//        return true;
    }


    public static void registerUser(UserPOJO newUser) throws SQLException, NullPointerException {
        String sql = "INSERT INTO users (user_id, firstname, lastname, streetaddress, zipcode, email, username, password, active) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = createStatement(sql, newUser.getUserID(), newUser.getFirstName(), newUser.getLastName(),
                newUser.getAddress(), newUser.getZipCode(), newUser.getEmail(), newUser.getUsername(), newUser.getPassword(), newUser.isActive());
        pstmt.executeUpdate();

    }

    public static UserPOJO getUserByUsername(String username) throws SQLException {
        String sql = "SELECT user_id, firstname, lastname, streetaddress, zipcode, email, active " +
                "FROM users " +
                "WHERE username = ? ";
        PreparedStatement pstmt = createStatement(sql, username);
        ResultSet rs = pstmt.executeQuery();
        UserPOJO user = new UserPOJO();
        if (rs.next()) {
            user.setUserID((UUID)rs.getObject("user_id"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setAddress(rs.getString("streetaddress"));
            user.setZipCode(rs.getString("zipcode"));
            user.setEmail(rs.getString("email"));
            user.setActive(rs.getBoolean("active"));
        }
        return user;
    }

}
