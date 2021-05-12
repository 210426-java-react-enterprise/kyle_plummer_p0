package com.revature.p0.daos;

import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.pojos.TransactionPOJO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.utils.datastructures.LinkedList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Abstract class, contains methods to preform SQL CRUD operations pertaining to the transaction history
 *
 * @author Kyle Plummer
 */
public abstract class TransactionHistoryDAO extends DatabaseDAO{

    public static void addTransaction(UserPOJO user, AccountPOJO account, String type, Double amount) throws SQLException, NullPointerException {
        String sql = "INSERT INTO transaction_history (transaction_id, account_id, user_id, transaction_type, amount, transaction_date) VALUES (?, ?, ?, ?, ?, NOW())";
        PreparedStatement pstmt = createStatement(sql, UUID.randomUUID(), account.getAccountID(), user.getUserID(), type, amount);
        pstmt.executeUpdate();
    }

    public static LinkedList<TransactionPOJO> listTransactions(AccountPOJO account) throws SQLException, NullPointerException {
        String sql = "SELECT transaction_id, account_id, user_id, transaction_type, amount, " +
                "TO_CHAR(transaction_date, 'MM/DD/YYYY HH24:MI') as transaction_date " +
                "FROM transaction_history WHERE account_id = ? ORDER BY transaction_date";
        PreparedStatement pstmt = createStatement(sql, account.getAccountID());
        ResultSet rs = pstmt.executeQuery();

        LinkedList<TransactionPOJO> transactionList = new LinkedList<TransactionPOJO>();
        while(rs.next()) {
            TransactionPOJO transaction = new TransactionPOJO();
            transaction.setTransactionID((UUID)rs.getObject("transaction_id"));
            transaction.setAccountID((UUID)rs.getObject("account_id"));
            transaction.setUserID((UUID)rs.getObject("user_id"));
            transaction.setTransactionType(rs.getString("transaction_type"));
            transaction.setTransactionAmount(rs.getDouble("amount"));
            transaction.setTransactionDate(rs.getString("transaction_date"));
            transactionList.add(transaction);
        }
        return transactionList;
    }

}
