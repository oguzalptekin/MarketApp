package com.oguz.marketapp;

public class Baker extends Product{
    String productname;
    int weight;
    public Baker(){}
    public Baker(String categoryname, String type, int price, int stock, int quantity, String type1, String productname, int weight) {
        super(categoryname, type, price, stock, quantity);
        this.type = type1;
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
