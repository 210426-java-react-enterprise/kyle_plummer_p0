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
        System.out.printf("\n\n\nAccount: %s - %d  (Bal: $%.2f)\n", app.getCurrentAccount().getAccountType(),
                            app.getCurrentAccount().getAccountNum(), app.getCurrentAccount().getBalance());
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
            switch(consoleReader.readLine()){
                case "1":
                    app.navigate("/deopsit");
                    return;
                case "2":
                    app.navigate("/withdrawal");
                    return;
                case "3":
                    app.navigate("/transfer");
                    return;
                case "4":
                    app.navigate("/grantaccess");
                    return;
                case "5":
                    app.navigate("/transactionhistory");
                    return;
                case "B":
                    app.setCurrentAccount(null);
                    app.navigate("/userhome");
                    return;
                case "Q":
                    app.navigate("/quit");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.\n\n\n");
                    return;

            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

    }
}
