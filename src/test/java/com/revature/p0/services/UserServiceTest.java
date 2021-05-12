package com.revature.p0.services;

import com.revature.p0.pojos.UserPOJO;

import org.junit.Assert;
import org.junit.Test;
import java.util.UUID;


public class UserServiceTest {

    @Test
    public void test_userAuthenticationPositive() {
        UserPOJO returnedUser;
        String username = "testuser";
        String password = "testpass";

        returnedUser = UserService.authenticateUser(username, password);

        Assert.assertEquals( UUID.fromString("4aed96ae-b328-11eb-b538-fba1f9307d97"), returnedUser.getUserID());
        Assert.assertEquals("testfirstname", returnedUser.getFirstName());
        Assert.assertEquals("testlastname", returnedUser.getLastName());
        Assert.assertEquals("testaddress", returnedUser.getAddress());
        Assert.assertEquals("00000", returnedUser.getZipCode());
        Assert.assertEquals("test@email.com", returnedUser.getEmail());
        Assert.assertEquals("testuser", returnedUser.getUsername());
        Assert.assertEquals("testpass", returnedUser.getPassword());
        Assert.assertTrue(returnedUser.isActive());
    }

    @Test
    public void test_userAuthenticationNegative() {
        UserPOJO returnedUser;
        String username = "baduser";
        String password = "badpass";

        returnedUser = UserService.authenticateUser(username, password);

        Assert.assertThrows(null, NullPointerException.class, () -> returnedUser.getUsername());
    }

    @Test
    public void test_checkUserExistsPositive() {
        UserPOJO testUser;

        testUser = new UserPOJO("testuser");

        Assert.assertTrue(UserService.checkUserExists(testUser));
    }

    @Test
    public void test_checkUserExistsNegative() {
        UserPOJO testUser;

        testUser = new UserPOJO("baduser");

        Assert.assertFalse(UserService.checkUserExists(testUser));
    }

    @Test
    public void test_getUserByUsernamePositive() {
        String username = "testuser";
        UserPOJO returnedUser;

        returnedUser = UserService.getUserByUsername(username);

        Assert.assertEquals( UUID.fromString("4aed96ae-b328-11eb-b538-fba1f9307d97"), returnedUser.getUserID());
        Assert.assertEquals("testfirstname", returnedUser.getFirstName());
        Assert.assertEquals("testlastname", returnedUser.getLastName());
        Assert.assertEquals("testaddress", returnedUser.getAddress());
        Assert.assertEquals("00000", returnedUser.getZipCode());
        Assert.assertEquals("test@email.com", returnedUser.getEmail());
        Assert.assertTrue(returnedUser.isActive());
    }

    @Test
    public void test_getUserByUsernameNegative() {
        String username = "baduser";
        UserPOJO returnedUser;

        returnedUser = UserService.getUserByUsername(username);

        Assert.assertNull(returnedUser.getUserID());

    }

}
