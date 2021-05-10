package com.revature.p0.uis;

import com.revature.p0.pojos.AccountPOJO;

import java.io.BufferedReader;
import java.io.IOException;

public class AccountHomeUI extends UserInterface{
    public AccountHomeUI(BufferedReader consoleReader) {
        super("/accounthome", consoleReader);
    }

    @Override
    public void render() {
        AccountPOJO currentAccount = app.getCurrentAccount();
        System.out.printf("\n\n\nAccount: %s - %d  (Bal: $%.2f)\n", currentAccount.getAccountType(), currentAccount.getAccountNum(), currentAccount.getBalance());
        System.out.println("Please make a selection.\n===================================");
        System.out.println("1) Deposit");
        System.out.println("2) Withdrawal");
        System.out.println("3) Transfer");
        System.out.println("4) Grant access");
        System.out.println("5) View transaction history");
        System.out.println("B) Back");
        System.out.println("Q) Quit");
        System.out.println("===================================");

        try {
            consoleReader.readLine();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

    }
}
