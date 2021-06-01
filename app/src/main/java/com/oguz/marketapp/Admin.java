package com.oguz.marketapp;

public class Admin extends User{

    private String status;

    public Admin(String firstName, String lastName, String email, String status) {
        super(firstName, lastName, email);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
