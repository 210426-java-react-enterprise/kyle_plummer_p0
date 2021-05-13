package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.utils.FileLogger;

import java.sql.SQLException;
import java.util.UUID;

/**
 * Abstract class, interfaces at the border of DAOs and user interfaces. This service has static methods used to
 * access CRUD functions in DAOs pertaining to user accounts
 *
 * @author Kyle Plummer
 */
public abstract class UserService extends Service{

    /**
     * Check username and password to authenticate user and retrieve user entry from database
     * @param username String username for uniquely identifying user account
     * @param password String password for authenticating user as owner of account
     * @return UserPOJO containing the contents of user table entry for user account
     */
    public static UserPOJO authenticateUser(String username, String password) {
        try {
            UserPOJO user = new UserPOJO(username, password);
            if (UserDAO.authenticate(user)) {
                return user;
            }
        } catch (SQLException e) {
            //System.out.println("SQLException: "+ e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        } catch (NullPointerException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
        return null;
    }

    /**
     * Saves a new user account entry in user table
     * @param newUser UserPOJO containing data to be saved to user table
     */
    public static void registerUser(UserPOJO newUser) {
        try {
            newUser.setUserID(UUID.randomUUID());
            newUser.setActive(true);
            UserDAO.registerUser(newUser);
        } catch (SQLException e) {
            //System.out.println("SQLException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    /**
     * Verifies if a user account exists in user table (check if username already in use)
     * @param user UserPOJO containing username to check existence in user table
     * @return true if username found, else false.
     */
    public static boolean checkUserExists(UserPOJO user) {
        try {
            return UserDAO.checkUserExists(user.getUsername());
        } catch (SQLException e) {
            //System.out.println("SQLException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
        return false;
    }

    /**
     * Get a UserPOJO containing table data for user looked up by username
     * @param username String username to check existence in user table
     * @return UserPOJO containing user account info from user table matching username
     */
    public static UserPOJO getUserByUsername(String username) {
        UserPOJO user = new UserPOJO();
        try {
            user = UserDAO.getUserByUsername(username);
        } catch (SQLException e) {
            //System.out.println("SQLException" + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
        return user;
    }

}
