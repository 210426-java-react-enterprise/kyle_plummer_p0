package com.revature.p0.uis;

import com.revature.p0.utils.FileLogger;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * User interface for application entry. This is the first screen the user is presented with,
 * allows uer to register or authenticate
 *
 * @author Kyle Plummer
 */
public class WelcomeUI extends UserInterface {

    public WelcomeUI(BufferedReader consoleReader) {
        super("/welcome", consoleReader);
    }

    @Override
    public void render() {
        System.out.println("\n\n\nWelcome to Bank of Plummer!");
        System.out.println("Main Menu. Please make a selection.\n==================================================");
        System.out.println("1) Register");
        System.out.println("2) Login");
        System.out.println("Q) Quit");
        System.out.println("==================================================");
        try {

            //String selection = ;

            switch (consoleReader.readLine()) {
                case "1":
                    app.navigate("/register");
                    return;

                case "2":
                    app.navigate("/login");
                    return;

                case "Q":
                    app.navigate("/quit");
                    return;

                default:
                    System.out.println("Invalid selection. Please try again.\n\n\n");
                    return;
            }


        } catch (IOException e) {
            //System.out.println("IOException" + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
            app.navigate("/quit");

        }
    }
}
