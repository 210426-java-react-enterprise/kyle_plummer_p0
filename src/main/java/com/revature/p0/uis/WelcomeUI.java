package com.revature.p0.uis;

import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeUI extends UserInterface {

    //private BufferedReader consoleReader;//moved into super class

    public WelcomeUI(BufferedReader consoleReader) {
        super("/welcome", consoleReader);
        //this.consoleReader = consoleReader;
    }

    @Override
    public void render() {
        System.out.println("\n\n\nWelcome to Bank of Plummer!");
        int attempts = 1;
        int maxAttempts = 3;
        boolean inputFlag = false;
        do {
            System.out.println("Main Menu. Please make a selection.\n===================================");
            System.out.println("1) Register");
            System.out.println("2) Login");
            System.out.println("Q) Quit");
            System.out.println("===================================");
            try {

                String selection = consoleReader.readLine();

                switch (selection) {
                    case "1":
                        app.navigate("/register");
                        inputFlag = true;
                        break;

                    case "2":
                        app.navigate("/login");
                        inputFlag = true;
                        break;

                    default:
                        System.out.println("Invalid selection. Please try again.\n\n\n");
                        attempts++;
                        break;
                }


            } catch (IOException e) {
                System.out.println("IOException" + e);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        } while (inputFlag == false && attempts <= maxAttempts);

        if (attempts > maxAttempts) {
            System.out.println("Too many invalid attempts. Exiting...");
            app.navigate("/quit");
        }

    }
}
