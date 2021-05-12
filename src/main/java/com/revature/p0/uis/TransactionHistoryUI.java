package com.revature.p0.uis;

import com.revature.p0.pojos.TransactionPOJO;
import com.revature.p0.services.AccountService;
import com.revature.p0.utils.FileLogger;
import com.revature.p0.utils.datastructures.LinkedList;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * User interface for viewing all of the transactions associated with a bank account
 *
 * @author Kyle Plummer
 */
public class TransactionHistoryUI extends UserInterface{
    public TransactionHistoryUI(BufferedReader consoleReader) {
        super("/transactionhistory", consoleReader);
    }

    @Override
    public void render() {
//        System.out.printf("\n\n\n%s - %d  (Bal: $%.2f)\n", app.getCurrentAccount().getAccountDescription(),
//                app.getCurrentAccount().getAccountNum(), app.getCurrentAccount().getBalance());
        System.out.printf("\n\n\n%s (Acct# %d)  (Bal: $%.2f)\n", app.getCurrentAccount().getAccountDescription(),
                app.getCurrentAccount().getAccountNum(), app.getCurrentAccount().getBalance());
        System.out.println("Transaction History:\n==================================================");

        LinkedList<TransactionPOJO> transactionList = AccountService.listTransactionHistory(app.getCurrentAccount());

        for (int i = 0; i < transactionList.size(); i++) {
            System.out.printf("%s - %s: $%.2f\n",
                    transactionList.get(i).getTransactionDate(),
                    transactionList.get(i).getTransactionType(),
                    transactionList.get(i).getTransactionAmount());
        }

        System.out.println("==================================================");
        System.out.println("B) Back");
        System.out.println("Q) Quit");
        System.out.println("==================================================");

        try {
            switch(consoleReader.readLine()) {
                case "B":
                    app.navigate("/accounthome");
                    return;

                case "Q":
                    app.navigate("/quit");
                    return;
            }
        } catch (IOException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
            app.navigate("/quit");
            //return;
        }



    }
}
