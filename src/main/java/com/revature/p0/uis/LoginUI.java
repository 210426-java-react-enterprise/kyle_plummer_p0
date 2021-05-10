package com.revature.p0.uis;

import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.services.UserService;

import java.io.BufferedReader;

public class LoginUI extends UserInterface{

    private UserService userService;

    public LoginUI(BufferedReader consoleReader) {
        super("/login", consoleReader);
    }

    @Override
    public void render() {
        String username;
        String password;

        try {
            System.out.println("\n\n\nLog in:\n===================================");

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

        } catch(NullPointerException e) {
            System.out.println("Failed to log in.");
            app.navigate("/welcome");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
