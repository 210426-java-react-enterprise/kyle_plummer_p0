package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.pojos.UserPOJO;

import java.sql.SQLException;
import java.util.UUID;


public abstract class UserService extends Service{

    public static UserPOJO authenticateUser(String username, String password) {
        UserPOJO user = new UserPOJO(username, password);
        if (UserDAO.authenticate(user)) {
            return user;
        } else {
            return null;
        }
    }

    public static void registerUser(UserPOJO newUser) {
        newUser.setUserID(UUID.randomUUID());
        newUser.setActive(true);
        UserDAO.registerUser(newUser);
    }

    public static boolean checkUserExists(UserPOJO user) {
        try {
            return UserDAO.checkUserExists(user.getUsername());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        return false;
    }

    public static UserPOJO getUserByUsername(String username) {
        UserPOJO user = new UserPOJO();
        try {
            user = UserDAO.getUserByUsername(username);
        } catch (SQLException e) {
            System.out.println("SQLException" + e);
        }
        return user;
    }

}
