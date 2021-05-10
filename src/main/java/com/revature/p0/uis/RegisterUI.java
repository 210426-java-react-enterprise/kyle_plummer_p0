package com.revature.p0.uis;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.services.UserService;

import java.io.BufferedReader;


public class RegisterUI extends UserInterface{

    public RegisterUI(BufferedReader consoleReader) {
        super("/register", consoleReader);
    }

    @Override
    public void render() {
        UserPOJO newUser = new UserPOJO();
        int maxAttempts = 3;
        System.out.println("\n\n\nRegister User\n===================================");
        try {
            boolean usernameExists = false;
            do {
                newUser.setUsername(UserService.promptUser("Enter new username: ",
                        "Username malformed.",
                        maxAttempts,
                        "^[a-zA-Z0-9]{1,40}$",
                        consoleReader));
                if(usernameExists = UserService.checkUserExists(newUser)) {
                    System.out.println("Username already in use. Please try again.");
                }
            } while (usernameExists);

            newUser.setPassword(UserService.promptUser("Enter new password: ",
                    "Password malformed.",
                    maxAttempts,
                    "^(?=.*?[#?!@$%^&*-])[a-zA-Z0-9].{8,40}$",
                    consoleReader));

            newUser.setFirstName(UserService.promptUser("Enter first name: ",
                    "First name too long.",
                    maxAttempts,
                    "^[a-zA-Z]{1,40}$",
                    consoleReader));

            newUser.setLastName(UserService.promptUser("Enter last name: ",
                    "Last name too long.",
                    maxAttempts,
                    "^[a-zA-Z]{1,40}$",
                    consoleReader));

            newUser.setEmail(UserService.promptUser("Enter email address: ",
                    "Invalid email",
                    maxAttempts,
                    "^([0-9a-zA-Z.]+@[0-9a-zA-Z]+[.][a-zA-Z]+){1,40}$",
                    consoleReader));

            newUser.setAddress(UserService.promptUser("Enter street address: ",
                    "Address too long.",
                    maxAttempts,
                    "^[0-9a-zA-Z .,#-]{1,40}$",
                    consoleReader));

            newUser.setZipCode(UserService.promptUser("Enter Zip code: ",
                    "Invalid zip code",
                    maxAttempts,
                    "^[0-9]{5,5}$",
                    consoleReader));



            UserService.registerUser(newUser);
            app.setCurrentUser(newUser);
            app.navigate("/userhome");

        } catch(UserInputException e) {
           app.navigate("/welcome");
           return;
        }
    }
}
