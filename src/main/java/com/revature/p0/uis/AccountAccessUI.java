package com.revature.p0.uis;

import com.revature.p0.exceptions.UserInputException;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.services.AccountService;
import com.revature.p0.services.Service;
import com.revature.p0.services.UserService;
import com.revature.p0.utils.FileLogger;

import java.io.BufferedReader;

/**
 * User interface for account access, allows user to grant or revoke account access to another user by username
 *
 * @author Kyle Plummer
 */
public class AccountAccessUI extends UserInterface{

    public AccountAccessUI(BufferedReader consoleReader) {
        super("/accountaccess", consoleReader);
    }

    @Override
    public void render() {
        System.out.printf("\n\n\n%s (Acct# %d)  (Bal: $%.2f)\n", app.getCurrentAccount().getAccountDescription(),
                app.getCurrentAccount().getAccountNum(), app.getCurrentAccount().getBalance());
        System.out.println("==================================================");

        try {
            String targetUsername = Service.promptUser("Enter username to grant access to: ",
                    "Invalid username.",
                    3,
                    "^[a-zA-Z0-9]{1,40}$",
                    consoleReader);

            UserPOJO targetUser = UserService.getUserByUsername(targetUsername);
            if (targetUser.getUserID() == null) {
                System.out.println("Username not found.");
                app.navigate("/accounthome");
                return;
            }

            String access = Service.promptUser("(G)rant or (R)evoke access? ",
                    "Invalid selection, choose G or R.",
                    3,
                    "^(G|R)$",
                    consoleReader);

            AccountService.changeAccountAccess(targetUser, app.getCurrentAccount(), access);
            app.navigate("/accounthome");
            //return;

        } catch (UserInputException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
            app.navigate("/accounthome");
            //return;
        }
    }
}
