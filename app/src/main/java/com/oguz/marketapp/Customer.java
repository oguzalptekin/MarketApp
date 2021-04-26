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

    public Customer(String username, String firstName, String lastName, String email, Address address, CreditCard creditcard, Date birthDate, String phoneNumber, HashMap<String, Product> cart) { //all informations
        super(/*username,*/ firstName, lastName, email);
        this.address = address;
        this.creditcard = creditcard;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public Customer(String username, String firstName, String lastName, String email, CreditCard creditcard, Date birthDate, String phoneNumber, HashMap<String, Product> cart) { //without address
        super(/*username,*/ firstName, lastName, email);
        this.creditcard = creditcard;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public Customer(String username, String firstName, String lastName, String email, Address address, Date birthDate, String phoneNumber, HashMap<String, Product> cart) { //without credit card
        super(/*username,*/ firstName, lastName, email);
        this.address = address;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public Customer(String username, String firstName, String lastName, String email, Address address, CreditCard creditcard, String phoneNumber, HashMap<String, Product> cart) { //without birthdate
        super(/*username,*/ firstName, lastName, email);
        this.address = address;
        this.creditcard = creditcard;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }


}
