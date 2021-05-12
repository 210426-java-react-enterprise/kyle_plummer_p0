package com.revature.p0.daos;

import com.revature.p0.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Abstract class, to be inherited by the specific DAO classes which will handle SQL CRUD operations
 *
 * @author Kyle Plummer
 */
public abstract class DatabaseDAO {
    protected static Connection conn;

    static {
        conn = ConnectionFactory.getConnection();
    }

    public static PreparedStatement createStatement(String sql, Object ...params) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (int i = 1; i <= params.length; i++) {
            pstmt.setObject(i, params[i-1]);
        }
        return pstmt;
    }


    public static String scrubString(String string) {
        String safeString = string.replaceAll("[/(,);!<>\"]","");
        safeString = safeString.replaceAll("-+","-").trim();
        return safeString;
    }

}
