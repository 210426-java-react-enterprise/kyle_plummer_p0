package com.revature.p0.daos;

import com.revature.p0.pojos.AccountAccessPOJO;
import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.utils.datastructures.LinkedList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public abstract class AccountDAO extends DatabaseDAO{
    public static LinkedList<AccountPOJO> getAccounts(UserPOJO currentUser) {
        try {
            String sql = "SELECT a.account_id, a.account_num, a.account_type, a.balance, a.active " +
                    "FROM account_access aa JOIN accounts a on aa.account_id = a.account_id WHERE aa.user_id = ?";
            PreparedStatement pstmt = createStatement(sql, currentUser.getUserID());
            ResultSet rs = pstmt.executeQuery();
            LinkedList<AccountPOJO> accountList = new LinkedList<AccountPOJO>();
            while(rs.next()) {
                AccountPOJO pojo = new AccountPOJO();
                pojo.setAccountID((UUID)rs.getObject("account_id"));
                pojo.setAccountNum(rs.getInt("account_num"));
                pojo.setAccountType(rs.getString("account_type"));
                pojo.setBalance(rs.getDouble("balance"));
                pojo.setActive(rs.getBoolean("active"));
                accountList.add(pojo);
            }
            return accountList;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    return null;
    }

    public static void newAccountAccess(AccountAccessPOJO access) {
        try {
            String sql = "INSERT INTO account_access (account_access_id, user_id, account_id) VALUES (?, ?, ?)";
            PreparedStatement pstmt = createStatement(sql, access.getAccountAccessID(), access.getUserID(), access.getAccountID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e);
        }
    }

    public static void newAccount(AccountPOJO newAccount) {
        try {
            String sql = "INSERT INTO accounts (account_id, account_type, account_desc, balance, active) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = createStatement(sql, newAccount.getAccountID(), newAccount.getAccountType(),
                    newAccount.getAccountDescription(), 0.0f, newAccount.isActive());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e);
        }
    }

    public static AccountPOJO accountLookup(UUID accountID) {
        AccountPOJO account = new AccountPOJO();
        try {
            String sql = "SELECT * FROM accounts WHERE account_id = ?";
            PreparedStatement pstmt = createStatement(sql, accountID);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                account.setAccountID((UUID)rs.getObject("account_id"));
                account.setAccountNum(rs.getInt("account_num"));
                account.setAccountType(rs.getString("account_type"));
                account.setAccountDescription(rs.getString("account_desc"));
                account.setBalance(rs.getDouble("balance"));
                account.setActive(rs.getBoolean("active"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e);
        }
        System.out.printf("DEBUG: account num: %d\n", account.getAccountNum());
        return account;
    }




}
