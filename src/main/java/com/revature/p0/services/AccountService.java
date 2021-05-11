package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.TransactionHistoryDAO;
import com.revature.p0.pojos.AccountAccessPOJO;
import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.pojos.TransactionPOJO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.utils.datastructures.LinkedList;


import java.sql.SQLException;
import java.util.UUID;


public abstract class AccountService extends Service{

    public static LinkedList<AccountPOJO> getAccounts(UserPOJO currentUser) {
        return AccountDAO.getAccounts(currentUser);
    }

    public static void newAccount(AccountPOJO newAccount) {
        try {
            UserPOJO currentUser = app.getCurrentUser();
            newAccount.setAccountID(UUID.randomUUID());
            newAccount.setActive(true);
            AccountAccessPOJO access = new AccountAccessPOJO(UUID.randomUUID(), currentUser.getUserID(), newAccount.getAccountID());
            AccountDAO.newAccount(newAccount);//save acct
            AccountDAO.newAccountAccess(access);        //save acct access
            System.out.println("DEBUG: setting new acct POJO with acct ID: " + newAccount.getAccountID());
            app.setCurrentAccount(AccountDAO.accountLookup(newAccount.getAccountID()));
            System.out.printf("DEBUG Current acct now: %s, %d\n", app.getCurrentAccount().getAccountID(), app.getCurrentAccount().getAccountNum());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }

    public static void depositIntoAccount(UserPOJO user, AccountPOJO account, Double amount) {
        try {
            if (amount > 0) {
                AccountDAO.updateBalance(account, amount);
                TransactionHistoryDAO.addTransaction(user, account, "deposit", amount);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }

    }

    public static void withdrawFromAccount(UserPOJO user, AccountPOJO account, Double amount) {
        try {
            if (amount > 0) {
                AccountDAO.updateBalance(account, -amount);
                TransactionHistoryDAO.addTransaction(user, account, "withdrawal", amount);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }

    public static void transferMoney(UserPOJO user, AccountPOJO source, AccountPOJO destination, Double amount) {
        if(amount > 0) {
            withdrawFromAccount(user, source, amount);
            depositIntoAccount(user, destination, amount);
        }
    }

    public static Double checkBalance (AccountPOJO account) {
        try {
            return AccountDAO.checkBalance(account);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        return 0.0;
    }

//    public static boolean accountExistsByNum (Integer accountNumber) {
//        try {
//            return AccountDAO.accountExistsByNum(accountNumber);
//        } catch(SQLException e) {
//            System.out.println("SQLException: " + e);
//        }
//        return false;
//    }

    public static AccountPOJO accountLookupByAccountNum (Integer accountNumber) {
        AccountPOJO account = new AccountPOJO();
        try {
            account = AccountDAO.accountLookup(accountNumber);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        return account;
    }

    public static LinkedList<TransactionPOJO> listTransactionHistory(AccountPOJO account) {
        LinkedList<TransactionPOJO> transactionsList = new LinkedList<TransactionPOJO>();
        try {
            transactionsList = TransactionHistoryDAO.listTransactions(account);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        return transactionsList;
    }

    public static void changeAccountAccess(UserPOJO user, AccountPOJO account, String access) {
        try {
            switch(access) {
                case "G":
                    AccountDAO.grantAccess(user, account);
                    break;

                case "R":
                    AccountDAO.revokeAccess(user, account);
                    break;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }



}
