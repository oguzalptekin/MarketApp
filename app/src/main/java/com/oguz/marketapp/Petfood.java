package com.oguz.marketapp;

public class Petfood extends Product{
    String productname,whichAnimal;
    int weight;
    public Petfood(){}

    public Petfood(String categoryname, String type, int price, int stock, int quantity, String productname, String whichAnimal, int weight) {
        super(categoryname, type, price, stock, quantity);
        this.productname = productname;
        this.whichAnimal = whichAnimal;
        this.weight = weight;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getWhichAnimal() {
        return whichAnimal;
    }

    public void setWhichAnimal(String whichAnimal) {
        this.whichAnimal = whichAnimal;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
