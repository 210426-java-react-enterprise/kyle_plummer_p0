package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.utils.datastructures.LinkedList;

public abstract class AccountService {
    public static LinkedList<AccountPOJO> getAccounts(UserPOJO currentUser) {
        return AccountDAO.getAccounts(currentUser);
    }
}
