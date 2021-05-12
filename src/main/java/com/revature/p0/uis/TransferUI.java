package com.revature.p0.uis;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.Service;
import com.revature.p0.utils.FileLogger;

import java.io.BufferedReader;

/**
 * User interface for transferring money between bank accounts.
 *
 * @author Kyle Plummer
 */
public class TransferUI extends UserInterface{
    public TransferUI(BufferedReader consoleReader) {
        super("/transfer", consoleReader);
    }

    @Override
    public void render() {
        System.out.printf("\n\n\n%s (Acct# %d)  (Bal: $%.2f)\n", app.getCurrentAccount().getAccountDescription(),
                app.getCurrentAccount().getAccountNum(), app.getCurrentAccount().getBalance());
        System.out.println("==================================================");

        try {
            Integer destinationAccountNum = Integer.parseInt(Service.promptUser("Enter destination account number: ",
                    "Malformed account number.",
                    3,
                    "^[0-9]{7,7}$",
                    consoleReader));

            AccountPOJO destinationAccount = AccountService.accountLookupByAccountNum(destinationAccountNum);
            if (destinationAccount.getAccountID() == null) {
                System.out.println("That account does not exist.");
                app.navigate("/accounthome");
                return;
            }

            Double amount = Double.parseDouble(Service.promptUser("Enter amount to transfer: $",
                    "Invalid amount.",
                    3,
                    "^[0-9]{1,7}(.[0-9]{1,2})?$",
                    consoleReader));

            if(amount <= AccountService.checkBalance(app.getCurrentAccount())) {
                AccountService.transferMoney(app.getCurrentUser(), app.getCurrentAccount(), destinationAccount, amount);
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
            System.out.println("Unable to transfer funds.");
            app.navigate("/accouthome");
        }
    }
}
