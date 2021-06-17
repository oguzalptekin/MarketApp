package com.oguz.marketapp;

import java.util.HashMap;

public class Customer extends User{

    private Address address;
    private CreditCard creditcard;
    private Date birthDate;
    private String phoneNumber;
    private HashMap<String, Product> cart;

    public Customer(String FirstName, String LastName, String email, String phoneNumber){
        super(FirstName, LastName, email);
        this.phoneNumber = phoneNumber;
    }
    public Customer(String firstName, String lastName, String email, String phoneNumber,HashMap<String, Product> cart){
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.cart=cart;
    }

    public Customer(String firstName, String lastName, String email, Address address, CreditCard creditcard, Date birthDate, String phoneNumber, HashMap<String, Product> cart) { //all informations
        super(firstName, lastName, email);
        this.address = address;
        this.creditcard = creditcard;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public Customer(String firstName, String lastName, String email, CreditCard creditcard, Date birthDate, String phoneNumber, HashMap<String, Product> cart) { //without address
        super(firstName, lastName, email);
        this.creditcard = creditcard;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public Customer(String firstName, String lastName, String email, Address address, Date birthDate, String phoneNumber, HashMap<String, Product> cart) { //without credit card
        super(firstName, lastName, email);
        this.address = address;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public Customer(String firstName, String lastName, String email, Address address, CreditCard creditcard, String phoneNumber, HashMap<String, Product> cart) { //without birthdate
        super(firstName, lastName, email);
        this.address = address;
        this.creditcard = creditcard;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CreditCard getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(CreditCard creditcard) {
        this.creditcard = creditcard;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HashMap<String, Product> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, Product> cart) {
        this.cart = cart;
    }
}
