package com.oguz.marketapp;

public class User {

    private String FirstName;
    private String LastName;
    private String email;

    public User(String firstName, String lastName, String email) {
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
