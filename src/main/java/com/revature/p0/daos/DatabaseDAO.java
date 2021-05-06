package com.revature.p0.daos;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseDAO {
    Connection conn;

    public DatabaseDAO(Connection conn){
        try {
            if (conn.isValid(10)) {
                this.conn = conn;
            }
        } catch (SQLException e) {
            System.out.println("Connection is no longer valid. " + e);

        }
    }

    public static String scrubString(String string) {
        String safeString = string.replaceAll("[/(,);!<>\"]","");
        safeString = safeString.replaceAll("-+","-").trim();
        return safeString;
    }

}
