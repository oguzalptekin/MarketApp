package com.oguz.marketapp;

public class StapleFood extends Product{
    String productname;
    String brand;
    int weight;
    public StapleFood(){}

    public StapleFood(String categoryname, String type, int price, int stock,  int quantity,String productname, String brand, int weight) {
        super(categoryname, type, price, stock, quantity);
        this.brand=brand;
        this.weight = weight;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}