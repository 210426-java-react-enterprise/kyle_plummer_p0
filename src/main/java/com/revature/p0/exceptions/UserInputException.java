package com.revature.p0.exceptions;

/**
 * Custom Exception to be thrown when user input is rejected too many times. Should cause app to
 * navigate to an appropriate screen in catch block.
 *
 * @author Kyle Plummer
 */
public class UserInputException extends Exception {
    public UserInputException(String str) {
        super(str);
    }
}
