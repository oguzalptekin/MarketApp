package com.oguz.marketapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Customer extends User{

    private Address address;
    private CreditCard creditcard;
    private Date birthDate;
    private String phoneNumber;
    private String Uid;
    private ArrayList<Product> cart;
    private ArrayList<CreditCard> cards;

    public Customer(){
        super();
    }

    public Customer(String FirstName, String LastName, String email, String phoneNumber,String Uid){
        super(FirstName, LastName, email);
        this.phoneNumber = phoneNumber;
        this.Uid=Uid;
    }
    public Customer(String firstName, String lastName, String email, String phoneNumber,ArrayList<Product> cart,String Uid){
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.cart=cart;
        this.Uid=Uid;
    }

    public Customer(String firstName, String lastName, String email, Address address, CreditCard creditcard, Date birthDate, String phoneNumber, ArrayList<Product> cart, String Uid) { //all informations
        super(firstName, lastName, email);
        this.address = address;
        this.creditcard = creditcard;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
        this.Uid=Uid;
    }

    public Customer(String firstName, String lastName, String email, CreditCard creditcard, Date birthDate, String phoneNumber, ArrayList<Product> cart, String Uid) { //without address
        super(firstName, lastName, email);
        this.creditcard = creditcard;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
        this.Uid=Uid;
    }

    public Customer(String firstName, String lastName, String email, Address address, Date birthDate, String phoneNumber, ArrayList<Product> cart, String Uid) { //without credit card
        super(firstName, lastName, email);
        this.address = address;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
        this.Uid=Uid;
    }

    public Customer(String firstName, String lastName, String email, CreditCard creditCard, String phoneNumber, ArrayList<Product> cart, String currentUid) {
        super(firstName, lastName, email);
        this.creditcard = creditCard;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
        this.Uid = currentUid;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(Product item) {
        cart.add(item);
    }

    public Customer(String firstName, String lastName, String email, Address address, CreditCard creditcard, String phoneNumber, ArrayList<Product> cart, String Uid) { //without birthdate
        super(firstName, lastName, email);
        this.address = address;
        this.creditcard = creditcard;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
        this.Uid=Uid;
    }

    public Customer(String firstName, String lastName, String email,  String phoneNumber, CreditCard creditcard, String uid) {
        super(firstName, lastName, email);
        this.creditcard = creditcard;
        Uid = uid;
        this.phoneNumber = phoneNumber;
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

    /*public Stack<Product> getCart() {
        return cart;
    }*/

    /*public void setCart(Product producitem) {
        cart.push(producitem);
    }*/

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public void deleteCard(){
        this.creditcard = null;
    }

}