package com.revature.p0.uis;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.Service;
import com.revature.p0.utils.FileLogger;

import java.io.BufferedReader;

/**
 * User interface for removing money from a bank account
 *
 * @author Kyle Plummer
 */
public class WithdrawalUI extends UserInterface{
    public WithdrawalUI(BufferedReader consoleReader) {
        super("/withdrawal", consoleReader);
    }

    @Override
    public void render() {
        System.out.printf("\n\n\n%s (Acct# %d)  (Bal: $%.2f)\n", app.getCurrentAccount().getAccountDescription(),
                app.getCurrentAccount().getAccountNum(), app.getCurrentAccount().getBalance());
        System.out.println("==================================================");

        try {
            Double amount = Double.parseDouble(Service.promptUser("Enter withdrawal amount: $",
                    "Invalid amount.",
                    3,
                    "^[0-9]{1,7}(.[0-9]{1,2})?$",
                    consoleReader));

            if (amount <= AccountService.checkBalance(app.getCurrentAccount())) {
                AccountService.withdrawFromAccount(app.getCurrentUser(), app.getCurrentAccount(), amount);
                System.out.printf("$%.2f withdrawn\n", amount);
                app.navigate("/accounthome");
                //return;
            } else {
                System.out.println("Insufficent funds.");
                app.navigate("/accounthome");
                //return;
            }

        } catch (UserInputException e) {
            //System.out.println("UserInputException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
            System.out.println("Unable to withdraw funds.");
            app.navigate("/accounthome");
        }
    }
}
