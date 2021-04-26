package com.oguz.marketapp;

public class Date {

    private int day;
    private int month;
    private int year;

    //Tarih class'ında değişiklik olabilir belki silinebilir de

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(int month, int year){
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if(day > 0 && day <= 31){
            this.day = day;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month > 0 && month <= 12){
            this.month = month;
        }

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
