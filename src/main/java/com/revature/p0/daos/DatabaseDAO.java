package com.revature.p0.daos;

import com.revature.p0.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public abstract class DatabaseDAO {
    protected static Connection conn;

    static {
        conn = ConnectionFactory.getConnection();
    }

    public static PreparedStatement createStatement(String sql, Object ...params) throws SQLException{
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            for (int i = 1; i <= params.length; i++) {
                pstmt.setObject(i, params[i-1]);
            }

            return pstmt;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
        return null;
    }


    public static String scrubString(String string) {
        String safeString = string.replaceAll("[/(,);!<>\"]","");
        safeString = safeString.replaceAll("-+","-").trim();
        return safeString;
    }

}
