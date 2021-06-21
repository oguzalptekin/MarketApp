package com.oguz.marketapp;

public class Cosmetics extends Product{
    String productname,brand, gender;

    public Cosmetics(){}
    public Cosmetics(String categoryname, String type, int price, int stock, int quantity, String productname, String gender, String brand) {
        super(categoryname, type, price, stock, quantity);
        this.productname=productname;
        this.brand=brand;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}