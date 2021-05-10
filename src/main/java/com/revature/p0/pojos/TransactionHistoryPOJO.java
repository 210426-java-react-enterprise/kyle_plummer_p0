package com.revature.p0.pojos;

import java.util.UUID;

public class TransactionHistoryPOJO {
    private UUID transactionHistoryID;
    private UUID accountID;
    private UUID userID;
    private String transactionType;
    private boolean active;

    public TransactionHistoryPOJO() {

    }

    public UUID getTransactionHistoryID() {
        return transactionHistoryID;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public boolean isActive() {
        return active;
    }

    public void setTransactionHistoryID(UUID transactionHistoryID) {
        this.transactionHistoryID = transactionHistoryID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
