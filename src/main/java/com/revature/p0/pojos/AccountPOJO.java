package com.revature.p0.pojos;

import java.util.UUID;

public class AccountPOJO {
    private UUID accountID;
    private Integer accountNum;
    private String accountType;
    private double balance;
    private boolean active;

    public AccountPOJO() {

    }

    public UUID getAccountID() {
        return accountID;
    }

    public Integer getAccountNum() {
        return accountNum;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public void setAccountNum(Integer accountNum) {
        this.accountNum = accountNum;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
