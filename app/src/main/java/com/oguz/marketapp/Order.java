package com.oguz.marketapp;

import java.util.ArrayList;
import java.util.Stack;

public class Order {

    private Customer recepient;
    private int orderNum;
    private Stack<Product> products;
    private int totalPrice;
    private Address address;
    private Date date;
    private String note;

    //Constructor without note
    public Order(Customer recepient, int orderNum, Stack<Product> products, int totalPrice, Address address, Date date) {
        this.recepient = recepient;
        this.orderNum = orderNum;
        this.products = products;
        this.totalPrice = totalPrice;
        this.address = address;
        this.date = date;
    }

    //Constructor with note
    public Order(Customer recepient, int orderNum, Stack<Product> products, int totalPrice, Address address, Date date, String note) {
        this.recepient = recepient;
        this.orderNum = orderNum;
        this.products = products;
        this.totalPrice = totalPrice;
        this.address = address;
        this.date = date;
        this.note = note;
    }

    public Customer getRecepient() {
        return recepient;
    }

    public void setRecepient(Customer recepient) {
        this.recepient = recepient;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Stack<Product> getProducts() {
        return products;
    }

    public void setProducts(Stack<Product> products) {
        this.products = products;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
