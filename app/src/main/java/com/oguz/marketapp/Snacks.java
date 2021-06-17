package com.oguz.marketapp;

public class Snacks extends Product {
    int calory,weight;
    String brand,productname;

    public Snacks(){}

    public Snacks(String categoryname, String type, int price, int stock, int quantity, int calory, int weight, String brand,String productname) {
        super(categoryname, type, price, stock, quantity);
        this.calory = calory;
        this.weight = weight;
        this.brand = brand;
        this.productname=productname;
    }

    public int getCalory() {
        return calory;
    }

    public void setCalory(int calory) {
        this.calory = calory;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
