package com.oguz.marketapp;

public class User {

    //private String username;
    private String FirstName;
    private String LastName;
    private String email;

    public User(/*String username,*/ String firstName, String lastName, String email) {
        //this.username = username;
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
    }

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

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
