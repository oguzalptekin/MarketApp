package com.oguz.marketapp;

public class Others extends Product{
    String productname;

    public Others(){}

    public Others(String categoryname, String type, int price, int stock, int quantity, String productname) {
        super(categoryname, type, price, stock, quantity);
        this.productname = productname;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
