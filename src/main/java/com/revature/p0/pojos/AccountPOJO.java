package com.revature.p0.pojos;

import java.util.UUID;

public class AccountPOJO {
    private UUID accountID;
    private double balance;
    private boolean active;

    public AccountPOJO() {

    }

    public UUID getAccountID() {
        return accountID;
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
