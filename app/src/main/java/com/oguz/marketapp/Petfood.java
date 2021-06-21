package com.oguz.marketapp;

public class Petfood extends Product{
    String productname,animal, brand;
    int weight;
    public Petfood(){}

    public Petfood(String categoryname, String type, int price, int stock, int quantity, String productname, String animal, int weight, String brand) {
        super(categoryname, type, price, stock, quantity);
        this.animal = animal;
        this.productname=productname;
        this.weight = weight;
        this.brand = brand;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String whichAnimal) {
        this.animal = whichAnimal;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}