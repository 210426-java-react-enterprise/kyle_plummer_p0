package com.revature.p0.uis;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.Service;
import com.revature.p0.utils.FileLogger;

import java.io.BufferedReader;

/**
 * User interface for depositing money into an account
 *
 * @author Kyle Plummer
 */
public class DepositUI extends UserInterface{

    public DepositUI(BufferedReader consoleReader) {
        super("/deposit", consoleReader);
    }

    @Override
    public void render() {
        System.out.printf("\n\n\n%s (Acct# %d)  (Bal: $%.2f)\n", app.getCurrentAccount().getAccountDescription(),
                app.getCurrentAccount().getAccountNum(), app.getCurrentAccount().getBalance());
        System.out.println("==================================================");

        try {
            Double amount = Double.parseDouble(Service.promptUser("Enter deposit amount: $",
                    "Invalid amount.",
                    3,
                    "^[0-9]{1,7}(.[0-9]{1,2})?$",
                    consoleReader));

            AccountService.depositIntoAccount(app.getCurrentUser(), app.getCurrentAccount(), amount);
            System.out.printf("$%.2f deposited\n", amount);
            app.navigate("/accounthome");
            //return;


        } catch (UserInputException e) {
            //System.out.println("UserInputException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
            app.navigate("/accounthome");
            //return;
        }

    }
}
