package com.revature.p0.uis;

import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.services.AccountService;
import com.revature.p0.utils.FileLogger;
import com.revature.p0.utils.datastructures.LinkedList;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * User interface for navigating to accounts associated with user. Displays a list of accounts to choose from
 *
 * @author Kyle Plummer
 */
public class UserHomeUI extends UserInterface{
    public UserHomeUI(BufferedReader consoleReader) {
        super("/userhome", consoleReader);
    }

    @Override
    public void render() {
        LinkedList<AccountPOJO> accountList = AccountService.getAccounts(app.getCurrentUser());
        app.setCurrentAccount(null);

        System.out.printf("\n\n\nWelcome, %s!\nUser Menu. Please make a selection.\n==================================================\n",
                app.getCurrentUser().getFirstName());
        System.out.println("N) New Account");
        for (int i = 0; i < accountList.size(); i++) {
            System.out.printf("%d) %s: %d (Bal: $%.2f)\n", i, accountList.get(i).getAccountDescription(),
                    accountList.get(i).getAccountNum(), accountList.get(i).getBalance());
        }
        System.out.println("Q) Quit");
        System.out.println("==================================================");
        try {

            String selection = consoleReader.readLine();
            switch (selection) {
                case "N":
                    app.navigate("/newaccount");
                    return;

                case "Q":
                    app.navigate("/quit");
                    return;

                default:
                    Integer accountSelection = Integer.parseInt(selection);
                    if(accountSelection >= 0 && accountSelection < accountList.size()) {
                        app.setCurrentAccount(accountList.get(accountSelection));
                        app.navigate("/accounthome");
                    }
                return;
            }

        } catch (IOException e) {
            //System.out.println("IOException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
            app.navigate("/quit");
        }

    }
}
