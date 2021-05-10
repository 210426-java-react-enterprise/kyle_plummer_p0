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
            //String sql = "SELECT * FROM account_access WHERE user_id = ?";
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
}
