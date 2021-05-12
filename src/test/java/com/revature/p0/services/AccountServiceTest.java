package com.revature.p0.services;

import com.revature.p0.daos.AccountDAO;
import com.revature.p0.pojos.AccountPOJO;
import com.revature.p0.pojos.UserPOJO;
import com.revature.p0.utils.datastructures.LinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class AccountServiceTest {

    @Test
    public void test_getAccountsForUsernamePositive () {
        UserPOJO testUser = new UserPOJO();
        LinkedList<AccountPOJO> accountList = new LinkedList<AccountPOJO>();
        String testUserID = "4aed96ae-b328-11eb-b538-fba1f9307d97";
        String testAccountID = "4aed98e8-b328-11eb-b53a-83cc36f981e7";
        int expectedAccounts = 1;

        testUser.setUserID(UUID.fromString(testUserID));
        accountList = AccountService.getAccounts(testUser);


        Assert.assertEquals(expectedAccounts, accountList.size());
        Assert.assertEquals(UUID.fromString(testAccountID), accountList.get(0).getAccountID());
        Assert.assertEquals((int)1000001, (int)accountList.get(0).getAccountNum());
        Assert.assertEquals("test", accountList.get(0).getAccountType());
        Assert.assertEquals("test account", accountList.get(0).getAccountDescription());
        Assert.assertEquals(0.0, accountList.get(0).getBalance(), 0.01);
        Assert.assertTrue(accountList.get(0).isActive());

    }

    @Test
    public void test_getAccountsForUsernameNegative() {
        
    }
}
