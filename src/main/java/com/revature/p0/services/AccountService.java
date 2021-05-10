package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.pojos.AccountAccessPOJO;
import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.utils.datastructures.LinkedList;


import java.util.UUID;


public abstract class AccountService extends Service{

    public static LinkedList<AccountPOJO> getAccounts(UserPOJO currentUser) {
        return AccountDAO.getAccounts(currentUser);
    }

    public static void newAccount(AccountPOJO newAccount) {
        UserPOJO currentUser = app.getCurrentUser();
        newAccount.setAccountID(UUID.randomUUID());
        newAccount.setActive(true);
        AccountAccessPOJO access = new AccountAccessPOJO(UUID.randomUUID(), currentUser.getUserID(), newAccount.getAccountID());
        //save acct
        AccountDAO.newAccount(newAccount);
        //save acct access
        AccountDAO.newAccountAccess(access);
        System.out.println("DEBUG: setting new acct POJO with acct ID: " + newAccount.getAccountID());
        app.setCurrentAccount(AccountDAO.accountLookup(newAccount.getAccountID()));
        System.out.printf("DEBUG Current acct now: %s, %d\n", app.getCurrentAccount().getAccountID(), app.getCurrentAccount().getAccountNum());
    }






//moved into super class
//    public static String promptUser(String prompt, String error, int maxAttempts, String pattern, BufferedReader consoleReader) throws UserInputException {
//        boolean inputFlag = false;
//        int attempts = 1;
//        String input = new String();
//        try {
//            do {
//                System.out.printf(prompt);
//                input = consoleReader.readLine();
//                if (!(inputFlag = Pattern.compile(pattern).matcher(input).find())) {
//                    System.out.println(error);
//                    attempts++;
//                }
//            } while (!inputFlag && attempts <= maxAttempts);
//        } catch (IOException e) {
//            System.out.println("IOException " + e);
//        }
//        if (attempts > maxAttempts) {
//            throw new UserInputException();
//        }
//        return input;
//
//    }

    //depricated - see service.promptUser() method
//    public static boolean validateAccountType(String type) {
//        return Pattern.compile("^(checking|savings)$").matcher(type).find();
//    }
}
