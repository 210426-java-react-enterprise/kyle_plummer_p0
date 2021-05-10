package com.revature.p0.exceptions;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String error) {
        super(error);
    }
}
