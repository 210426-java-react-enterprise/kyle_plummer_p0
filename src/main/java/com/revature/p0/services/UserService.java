package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.UserAlreadyExists;
import com.revature.p0.exceptions.UserFailedToLogin;
import com.revature.p0.pojos.UserPOJO;

import java.util.regex.Pattern;


public class UserService {

    public static UserPOJO authenticateUser(String username, String password) throws UserFailedToLogin {
        UserPOJO user = new UserPOJO(username, password);
        if (UserDAO.authenticate(user)) {
            return user;
        } else {
            throw new UserFailedToLogin();
        }
    }

    public static UserPOJO registerUser(String firstname, String lastname, String username, String password,
                                        String address, Integer zip, String email) throws UserAlreadyExists{
        UserPOJO user = new UserPOJO(firstname, lastname, username, password, address, zip, email);
        if(!UserDAO.checkUserExists(user)) {

        } else {
            throw new UserAlreadyExists("This username is already in use");
        }
    return null;//change me later, just placeholder for testing for now

    }

    public static boolean validateUsername(String username) {
        return (Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?!=.*?[#?!@$%^&*-]).{6,}$").matcher(username).find()
                    && !UserDAO.checkUserExists(username));
    }

    public static boolean validatePassword(String pass) {
        return Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$").matcher(pass).find();
    }

    public static boolean validateFirstName(String firstName) {
        return ();
    }





}
