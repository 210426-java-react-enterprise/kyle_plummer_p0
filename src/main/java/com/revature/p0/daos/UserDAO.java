package com.revature.p0.daos;

import com.revature.p0.pojos.UserPOJO;
import com.sun.istack.internal.localization.NullLocalizable;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDAO extends DatabaseDAO {

    private UserDAO() {
        //private constructor. No need to instantiate this class.
    }

    public static boolean authenticate(UserPOJO user) {
        try {
            String sql = "SELECT user_id, firstname, lastname, streetaddress, zipcode, email, active " +
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
                user.setZipCode(rs.getInt("zipcode"));
                user.setEmail(rs.getString("email"));
                user.setActive(rs.getBoolean("active"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
        return false;
    }

    public static boolean checkUserExists(String username /*UserPOJO user*/) {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        PreparedStatement pstmt = createStatement(sql, username);
        try {
            ResultSet rs = pstmt.executeQuery();
            //System.out.println("DEBUG checkUserName statement: " + sql + ", username: " + user.getUsername());
            if(!rs.next()) {
                //System.out.println("DEBUG: user does not exist.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
        //System.out.println("DEBUG: user does exist.");
        return true;
    }


    public static void registerUser(UserPOJO newUser) {
        try {
            String sql = "INSERT INTO users (user_id, firstname, lastname, streetaddress, zipcode, email, username, password, active) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = createStatement(sql, newUser.getUserID(), newUser.getFirstName(), newUser.getLastName(),
                    newUser.getAddress(), newUser.getZipCode(), newUser.getEmail(), newUser.getUsername(), newUser.getPassword(), newUser.isActive());
            //System.out.println("Executing sql statement: " + sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e);
        }
    }

}
