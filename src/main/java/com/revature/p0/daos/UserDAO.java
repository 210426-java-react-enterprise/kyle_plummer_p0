package com.revature.p0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDAO extends DatabaseDAO {
    private String username;
    private UUID userID;
    private boolean authenticated;

    public UserDAO(Connection conn, String username) {
        super(conn);
        this.username = username;
        this.authenticated = false;

        try {
            String sql = "select user_id from users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                this.userID = (UUID)rs.getObject("user_id");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
    }

    public boolean authenticate(String password) {
        try {
            String sql = "select authenticate(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, userID);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if(rs.getBoolean("authenticate")){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
