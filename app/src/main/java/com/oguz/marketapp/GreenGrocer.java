package com.oguz.marketapp;

public class GreenGrocer extends Product{
    String productname;
    int weight;

    public GreenGrocer(){}

    public GreenGrocer(String categoryname, String type, int price, int stock, int quantity, String productname, int weight) {
        super(categoryname, type, price, stock, quantity);
        this.productname = productname;
        this.weight = weight;
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
