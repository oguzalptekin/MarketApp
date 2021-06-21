package com.oguz.marketapp;

public class Drinks extends Product {
    int litre;
    String brand,type,productname;

    public Drinks(){}

    public Drinks(String categoryname, String type, int price, int stock, int quantity, String productname, int litre,  String brand) {
        super(categoryname, type, price, stock, quantity);

        this.productname=productname;
        this.litre = litre;
        this.brand = brand;
    }

    public int getLitre() {
        return litre;
    }

    public void setLitre(int litre) {
        this.litre = litre;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}