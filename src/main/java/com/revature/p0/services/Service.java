package com.revature.p0.services;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.utils.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

public abstract class Service {
    protected static App app;

    static {
        app = App.getApp();
    }


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
