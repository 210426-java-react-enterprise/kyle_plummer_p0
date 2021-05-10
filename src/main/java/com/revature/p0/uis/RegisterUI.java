package com.revature.p0.uis;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class RegisterUI extends UserInterface{

    public RegisterUI(BufferedReader consoleReader) {
        super("/register", consoleReader);
        //this.consoleReader = consoleReader;
    }

    @Override
    public void render() {

        UserPOJO newUser = new UserPOJO();
        boolean inputFlag = false;
        int attempts = 1;
        int maxAttempts = 3;
        System.out.println("\n\n\nRegister User\n===================================");
        try {
            do {
                System.out.printf("Enter new username: ");
                newUser.setUsername(consoleReader.readLine());
                if(inputFlag = UserDAO.checkUserExists(newUser)){
                    System.out.println("That username is already in use. Please try again. Attempts: " + attempts + "/" + maxAttempts);
                    attempts++;
                }
            } while (inputFlag && attempts <= maxAttempts);

            if (attempts > maxAttempts) {
                System.out.println("Too many attempts. Exiting to main menu.");
                app.navigate("/welcome");
                return;
            }


            attempts = 1;
            inputFlag = false;
            do {
                System.out.printf("Enter new password: ");
                newUser.setPassword(consoleReader.readLine());
                if(!(inputFlag = UserService.validatePassword(newUser.getPassword()))){
                    System.out.println("Password should be 8 characters and have at least uppercase, lowercase, number, and special character.");
                    attempts++;
                }
            } while (inputFlag && attempts <= maxAttempts);

            if (attempts > maxAttempts) {
                System.out.println("Too many attempts. Exiting to main menu.");
                app.navigate("/welcome");
                return;
            }

            attempts = 1;
            inputFlag = false;
            do {
                System.out.printf("Enter first name: ");
                newUser.setFirstName(consoleReader.readLine());
                if(!(inputFlag = newUser.getFirstName().length() <= 40)){
                    System.out.println("First name too long.");
                    attempts++;
                }
            } while (inputFlag && attempts <= maxAttempts);

            if (attempts > maxAttempts) {
                System.out.println("Too many attempts. Exiting to main menu.");
                app.navigate("/welcome");
                return;
            }

            attempts = 1;
            inputFlag = false;
            do {
                System.out.printf("Enter last name: ");
                newUser.setLastName(consoleReader.readLine());
                if(!(inputFlag = newUser.getLastName().length() <= 40)){
                    System.out.println("Last name too long.");
                    attempts++;
                }
            } while (inputFlag && attempts <= maxAttempts);

            if (attempts > maxAttempts) {
                System.out.println("Too many attempts. Exiting to main menu.");
                app.navigate("/welcome");
                return;
            }


            attempts = 1;
            inputFlag = false;
            do {
                System.out.printf("Enter email address: ");
                newUser.setEmail(consoleReader.readLine());
                if(!(inputFlag = newUser.getEmail().length() <= 40)){
                    System.out.println("Email address too long.");
                    attempts++;
                }
            } while (inputFlag && attempts <= maxAttempts);

            if (attempts > maxAttempts) {
                System.out.println("Too many attempts. Exiting to main menu.");
                app.navigate("/welcome");
                return;
            }


            attempts = 1;
            inputFlag = false;
            do {
                System.out.printf("Enter street address: ");
                newUser.setAddress(consoleReader.readLine());
                if(!(inputFlag = newUser.getAddress().length() <= 40)){
                    System.out.println("Address too long.");
                    attempts++;
                }
            } while (inputFlag && attempts <= maxAttempts);

            if (attempts > maxAttempts) {
                System.out.println("Too many attempts. Exiting to main menu.");
                app.navigate("/welcome");
                return;
            }


            attempts = 1;
            inputFlag = false;
            do {
                System.out.printf("Enter Zip code (#####):");
                String inputString = consoleReader.readLine();
                try {
                    newUser.setZipCode(Integer.parseInt(inputString));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid zip code");
                    attempts++;
                    continue;
                }
                newUser.setLastName(consoleReader.readLine());
                if(!(inputFlag = newUser.getZipCode() <= 99999 && newUser.getZipCode() >= 11111)){
                    System.out.println("Last name too long.");
                    attempts++;
                }
            } while (inputFlag && attempts <= maxAttempts);

            if (attempts > maxAttempts) {
                System.out.println("Too many attempts. Exiting to main menu.");
                app.navigate("/welcome");
                return;
            }



            app.storeObject(newUser);
            app.navigate("/userhome");

        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }


    }
}
