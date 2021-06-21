package com.oguz.marketapp;

public class Technology extends Product{
    String productname,brand;
    public Technology(){}

    public Technology(String categoryname, String type, int price, int stock, int quantity,String productname, String brand) {
        super(categoryname, type, price, stock, quantity);
        this.brand=brand;
        this.productname=productname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}