package com.revature.p0.uis;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.services.AccountService;

import java.io.BufferedReader;

public class NewAccountUI extends UserInterface{
    public NewAccountUI(BufferedReader consoleReader) {
        super("/newaccount", consoleReader);
    }

    @Override
    public void render() {
        AccountPOJO newAccount = new AccountPOJO();
        System.out.println("\n\n\nCreate new account\n=========================");
        //String returnedString = new String();
        try {
            newAccount.setAccountType(AccountService.promptUser(
                    "Enter account type (checking|savings): ",
                    "Invalid account type.",
                    3,
                    "^(checking|savings)$",
                    consoleReader));

            newAccount.setAccountDescription(AccountService.promptUser(
                    "Enter account description: ",
                    "Invalid account description.",
                    3,
                    "^[a-zA-Z ,'-]{1,50}$",
                    consoleReader));
            app.setCurrentAccount(newAccount);
            AccountService.newAccount(newAccount);
            app.navigate("/accounthome");


        } catch (UserInputException e) {
            app.navigate("/userhome");
            return;
        } catch (Exception e) {
            System.out.println("IOException: " + e);
        }
    }
}
