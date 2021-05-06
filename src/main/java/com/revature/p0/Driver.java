package com.revature.p0;

import com.revature.p0.daos.DatabaseDAO;
import com.revature.p0.daos.UserDAO;
import com.revature.p0.utils.ConnectionFactory;

import java.sql.*;


public class Driver {
    public static void main(String[] args) {
        Connection conn = ConnectionFactory.createConnection("src/main/resources/jdbc.properties").getConnection();
        UserDAO userDAO = new UserDAO(conn, "kplummer");
        System.out.println(userDAO.authenticate("password"));


    }
}
