package com.revature.p0.uis;

import java.io.BufferedReader;
import java.io.IOException;

public class UserHomeUI extends UserInterface{
    public UserHomeUI(BufferedReader consoleReader) {
        super("/userhome", consoleReader);
        //this.consoleReader = consoleReader;

    }

    @Override
    public void render() {
        System.out.println("\n\n\nWelcome!\nUser Menu. Please make a selection.\n===================================");
        //choose account
        //loop through accounts displaying each with an int.
        //N) new account
        //Q) quit
        System.out.println("===================================");
        try {

            String selection = consoleReader.readLine();

            switch (selection) {
                case "1":
                    app.navigate("/register");
                    break;

                case "2":
                    app.navigate("/login");
                    break;
            }

        } catch (IOException e) {
            System.out.println("IOException" + e);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }
}
