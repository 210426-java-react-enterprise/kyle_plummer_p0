package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.UserAlreadyExists;
import com.revature.p0.exceptions.UserFailedToLogin;
import com.revature.p0.pojos.UserPOJO;

import java.util.UUID;
import java.util.regex.Pattern;


public abstract class UserService {

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
        //System.out.println("DEBUG: registering new user");
        UserDAO.registerUser(newUser);
    }

    public static boolean validateUsername(String username) {
        //System.out.println("DEBUG: validating username - regex: " + Pattern.compile("^[a-zA-Z0-9]{1,40}$").matcher(username).find());
        //System.out.println("DEBUG: validating username - check doesn't exist: " + !UserDAO.checkUserExists(username));
        return (Pattern.compile("^[a-zA-Z0-9]{1,40}$").matcher(username).find()
                    && (!UserDAO.checkUserExists(username)));
    }

    public static boolean validatePassword(String pass) {
        return Pattern.compile("^(?=.*?[#?!@$%^&*-])[a-zA-Z0-9].{8,40}$").matcher(pass).find();
    }

    public static boolean validateFirstName(String firstName) {
        return Pattern.compile("^[a-zA-Z]{1,40}$").matcher(firstName).find();
    }

    public static boolean validateLastName(String lastName) {
        return Pattern.compile("^[a-zA-Z]{1,40}$").matcher(lastName).find();
    }

    public static boolean validateAddress(String address) {
        return Pattern.compile("^[0-9a-zA-Z .,#-]{1,40}$").matcher(address).find();
    }

    public static boolean validateZipCode(Integer zip) {
        return (zip <= 99999 && zip >= 10000);
    }





}
