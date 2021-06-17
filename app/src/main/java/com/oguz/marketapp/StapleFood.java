package com.oguz.marketapp;

public class StapleFood extends Product{
    String productname;
    String brand;
    public StapleFood(){}

    public StapleFood(String categoryname, String type, int price, int stock, int quantity, String productname,String brand) {
        super(categoryname, type, price, stock, quantity);
        this.productname = productname;
        this.brand=brand;
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
