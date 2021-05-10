package com.revature.p0.pojos;

import java.util.UUID;

public class UserPOJO {
    private UUID userID;
    private String firstName;
    private String lastName;
    private String address;
    //private Integer zipCode;
    private String zipCode;
    private String email;
    private String username;
    private String password;
    private boolean active;

    public UserPOJO() {
        //empty constructor;
    }

    public UserPOJO(String username) {
        this.username = username;
    }

    public UserPOJO(String username, String password) {
        this(username);
        this.password = password;
    }

    public UserPOJO(String firstname, String lastname, String username, String password,
                    String address, String zip, String email) {
        this(username, password);
        this.firstName = firstname;
        this.lastName = lastname;
        this.address = address;
        this.zipCode = zip;
        this.email = email;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
