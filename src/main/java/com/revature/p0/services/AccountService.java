package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.daos.TransactionHistoryDAO;
import com.revature.p0.pojos.AccountAccessPOJO;
import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.pojos.TransactionPOJO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.utils.FileLogger;
import com.revature.p0.utils.datastructures.LinkedList;


import java.sql.SQLException;
import java.util.UUID;

/**
 * Abstract class, interfaces at the border of DAOs and user interfaces. This service has static methods used to
 * access CRUD functions in DAOs pertaining to bank accounts and transactions.
 *
 * @author Kyle Plummer
 */
public abstract class AccountService extends Service{

    /**
     * Gets accounts associated with a user by user_id according to account_access
     * @param currentUser UserPOJO containing user_id to use for lookup
     * @return LinkedList<AccountPOJO> list of accounts
     */
    public static LinkedList<AccountPOJO> getAccounts(UserPOJO currentUser) {
        try {
            return AccountDAO.getAccounts(currentUser);
        } catch (SQLException e) {

            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
        return null;
    }

    /**
     * Creates a new entry in account_access and accounts tables, implicitly uses app.currentUser who will be
     * associated with the new account.
     * @param newAccount AccountPOJO containing all fields to be saved in database
     */
    public static void newAccount(AccountPOJO newAccount) {
        try {
            UserPOJO currentUser = app.getCurrentUser();
            newAccount.setAccountID(UUID.randomUUID());
            newAccount.setActive(true);
            AccountAccessPOJO access = new AccountAccessPOJO(UUID.randomUUID(), currentUser.getUserID(), newAccount.getAccountID());
            AccountDAO.newAccount(newAccount);//save acct
            AccountDAO.newAccountAccess(access);//save acct access
            app.setCurrentAccount(AccountDAO.accountLookup(newAccount.getAccountID()));
        } catch (SQLException e) {
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    /**
     * Increases account balance by amount, and records a transaction history entry
     * @param user UserPOJO for tracking the transaction in the transaction_history table
     * @param account AccountPOJO which will have it's balance altered
     * @param amount Amount by which to increase account balance
     */
    public static void depositIntoAccount(UserPOJO user, AccountPOJO account, Double amount) {
        try {
            if (amount > 0) {
                AccountDAO.updateBalance(account, amount);
                TransactionHistoryDAO.addTransaction(user, account, "deposit", amount);
            }
        } catch (SQLException e) {
            //System.out.println("SQLException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

    }

    /**
     * Reduces account balance by amount, and records a transaction history entry
     * @param user UserPOJO for tracking the transaction in the transaction_history table
     * @param account AccountPOJO which will have it's balance altered
     * @param amount Amount by which to increase account balance
     */
    public static void withdrawFromAccount(UserPOJO user, AccountPOJO account, Double amount) {
        try {
            if (amount > 0) {
                AccountDAO.updateBalance(account, -amount);
                TransactionHistoryDAO.addTransaction(user, account, "withdrawal", amount);
            }
        } catch (SQLException e) {
            //System.out.println("SQLException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    /**
     * Calls withdrawFromAccount() and depositIntoAccount() on the same amount in order to transfer
     * balance from one account to the other. those methods record transaction history.
     * @param user UserPOJO associated with this transfer, the user who initialized it
     * @param source AccountPOJO for account which will have balance reduced
     * @param destination AccountPOJO for account which will have balance increased
     * @param amount amount by which to change account balances
     */
    public static void transferMoney(UserPOJO user, AccountPOJO source, AccountPOJO destination, Double amount) {
        if(amount > 0) {
            withdrawFromAccount(user, source, amount);
            depositIntoAccount(user, destination, amount);
        }
    }

    /**
     * Looks up an account by ID and returns the balance
     * @param account AccountPOJO containing account_id to lookup balance
     * @return Double balance as recorded in account table
     */
    public static Double checkBalance (AccountPOJO account) {
        try {
            Double bal = AccountDAO.checkBalance(account);
            //System.out.println("DEBUG bal: " + bal);
            return bal;
        } catch (SQLException e) {
            //System.out.println("SQLException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
        return 0.0;
    }

    /**
     * Gets all database info from account table for a specific account, looked up by unique account number.
     * @param accountNumber Integer, unique account number for users to identify accounts.
     * @return AccountPOJO complete database entry for account
     */
    public static AccountPOJO accountLookupByAccountNum (Integer accountNumber) {
        AccountPOJO account = new AccountPOJO();
        try {
            account = AccountDAO.accountLookup(accountNumber);
        } catch (SQLException e) {
            //System.out.println("SQLException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
        return account;
    }

    /**
     * Retrieve list of all transactions associated with an account
     * @param account AccountPOJO containing account_id to be used to look up transactions
     * @return LinkedList<TransactionPOJO> a list of all entries in transaction_history table associated with
     * the account
     */
    public static LinkedList<TransactionPOJO> listTransactionHistory(AccountPOJO account) {
        LinkedList<TransactionPOJO> transactionsList = new LinkedList<TransactionPOJO>();
        try {
            transactionsList = TransactionHistoryDAO.listTransactions(account);
        } catch (SQLException e) {
            //System.out.println("SQLException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
        return transactionsList;
    }

    /**
     * Creates or removes entries in account_access table which links a bank account to a user account.
     * @param user UserPOJO containing user_id for associating user account
     * @param account AccountPOJO containing account_id for associating bank account
     * @param access - grant or revoke access (call DAO method)
     */
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
            //System.out.println("SQLException: " + e);
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }



}
