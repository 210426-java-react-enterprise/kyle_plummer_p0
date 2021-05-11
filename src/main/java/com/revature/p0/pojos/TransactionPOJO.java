package com.revature.p0.pojos;

import java.util.UUID;

public class TransactionPOJO {
    private UUID transactionID;
    private UUID accountID;
    private UUID userID;
    private String transactionType;
    private Double transactionAmount;
    private String transactionDate;
    private boolean active;

    public TransactionPOJO() {

    }

    public UUID getTransactionID() {
        return transactionID;
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

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setTransactionID(UUID transactionHistoryID) {
        this.transactionID = transactionHistoryID;
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

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
