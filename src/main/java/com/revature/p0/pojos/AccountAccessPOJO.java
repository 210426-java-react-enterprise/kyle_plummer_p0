package com.revature.p0.pojos;

import java.util.UUID;

public class AccountAccessPOJO {
    private UUID accountAccessPOJO;
    private UUID userID;
    private UUID accountID;
    private boolean active;

    public AccountAccessPOJO() {

    }

    public UUID getAccountAccessPOJO() {
        return accountAccessPOJO;
    }

    public UUID getUserID() {
        return userID;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public boolean isActive() {
        return active;
    }

    public void setAccountAccessPOJO(UUID accountAccessPOJO) {
        this.accountAccessPOJO = accountAccessPOJO;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
