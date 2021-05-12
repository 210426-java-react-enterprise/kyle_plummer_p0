package com.revature.p0.uis;

import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.services.UserService;
import com.revature.p0.utils.FileLogger;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * User interface for authenticating user, leads to user home
 *
 * @author Kyle Plummer
 */
public class LoginUI extends UserInterface{

    public LoginUI(BufferedReader consoleReader) {
        super("/login", consoleReader);
    }

    @Override
    public void render() {
        String username;
        String password;

        try {
            System.out.println("\n\n\nLog in:\n==================================================");

            System.out.printf("username: ");
            username = consoleReader.readLine();

            System.out.printf("password: ");
            password = consoleReader.readLine();

            UserPOJO user = UserService.authenticateUser(username, password);

            if (user.getUserID() != null) {
                app.setCurrentUser(user);
                app.navigate("/userhome");
            } else {
                System.out.println("Failed to log in.");
                app.navigate("/welcome");
            }

        } catch (IOException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
            app.navigate("/quit");
            //return;
        } //catch ()
    }
}
