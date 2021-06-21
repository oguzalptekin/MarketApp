package com.oguz.marketapp;

public class Product {

    String categoryname,type,productName;
    int price,stock, quantity;

    public Product(){}

    public Product(String categoryname,String type, int price, int stock, int quantity, String productName) {
        this.categoryname = categoryname;
        this.type=type;
        this.quantity=quantity;
        this.price = price;
        this.stock = stock;
        this.productName = productName;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}