package com.revature.p0.uis;

import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.services.AccountService;
import com.revature.p0.utils.datastructures.LinkedList;

import java.io.BufferedReader;
import java.io.IOException;

public class UserHomeUI extends UserInterface{
    public UserHomeUI(BufferedReader consoleReader) {
        super("/userhome", consoleReader);
    }

    @Override
    public void render() {
        LinkedList<AccountPOJO> accountList = AccountService.getAccounts(app.getCurrentUser());

        System.out.printf("\n\n\nWelcome, %s!\nUser Menu. Please make a selection.\n==================================================\n",
                app.getCurrentUser().getFirstName());
        System.out.println("N) New Account");
        for (int i = 0; i < accountList.size(); i++) {
            System.out.printf("%d) %s: %d (Bal: $%.2f)\n", i, accountList.get(i).getAccountType(),
                    accountList.get(i).getAccountNum(), accountList.get(i).getBalance());
        }
        System.out.println("Q) Quit");
        System.out.println("==================================================");
        try {

            String selection = consoleReader.readLine();
            switch (selection) {
                case "N":
                    app.navigate("/newaccount");
                    break;

                case "Q":
                    app.navigate("/quit");
                    break;

                default:
                    Integer accountSelection = Integer.parseInt(selection);
                    if(accountSelection >= 0 && accountSelection < accountList.size()) {
                        app.setCurrentAccount(accountList.get(accountSelection));
                        app.navigate("/accounthome");
                    }
                break;
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }
}
