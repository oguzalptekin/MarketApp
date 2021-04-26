package com.oguz.marketapp;

public class Address {

    //address class'ında değişiklikler olabilir

    private String city;
    private String district;
    private String neighborhood;
    private String street;
    private int apartment;
    private int doorNumber;

    public Address(String city, String district, String neighborhood, String street, int apartment, int doorNumber) {
        this.city = city;
        this.district = district;
        this.neighborhood = neighborhood;
        this.street = street;
        this.apartment = apartment;
        this.doorNumber = doorNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public int getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(int doorNumber) {
        this.doorNumber = doorNumber;
    }
}
