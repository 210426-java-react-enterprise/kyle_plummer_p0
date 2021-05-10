package com.revature.p0.pojos;

import java.util.UUID;

public class AccountAccessPOJO {
    private UUID accountAccessID;
    private UUID userID;
    private UUID accountID;

    public AccountAccessPOJO() {

    }

    public AccountAccessPOJO(UUID accountAccessID, UUID userID, UUID accountID) {
        this.accountAccessID = accountAccessID;
        this.userID = userID;
        this.accountID = accountID;
    }

    public UUID getAccountAccessID() {
        return accountAccessID;
    }

    public UUID getUserID() {
        return userID;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public void setAccountAccessID(UUID accountAccessPOJO) {
        this.accountAccessID = accountAccessPOJO;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }
}
