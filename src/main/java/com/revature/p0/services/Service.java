package com.revature.p0.services;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.utils.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Abstract class, to be inherited by the specific service classes which will interface between user interfaces and DAOs.
 *
 * @author Kyle Plummer
 */
public abstract class Service {
    protected static App app;

    static {
        app = App.getApp();
    }


    /**
     *
     * @param prompt String message to prompt user for input, will be repeated up to maxAttempts times
     * @param error String to be displayed if input does not match regex pattern
     * @param maxAttempts Maximum number of times to prompt user before throwing UserInputException
     * @param pattern Regex pattern to validate user input
     * @param consoleReader BufferedReader used for retrieving input
     * @return String validated input from user
     * @throws UserInputException Thrown if user exceeds maxAttempts
     */
    public static String promptUser(String prompt, String error, int maxAttempts, String pattern, BufferedReader consoleReader) throws UserInputException {
        boolean inputFlag = false;
        int attempts = 1;
        String input = new String();
        try {
            do {
                System.out.printf(prompt);
                input = consoleReader.readLine();
                if (!(inputFlag = Pattern.compile(pattern).matcher(input).find())) {
                    System.out.println(error);
                    attempts++;
                }
            } while (!inputFlag && attempts <= maxAttempts);
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
        if (attempts > maxAttempts) {
            throw new UserInputException("Too many failed attempts at input.");
        }
        return input;

    }
}
