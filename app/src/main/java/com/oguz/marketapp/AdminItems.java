package com.oguz.marketapp;

public class AdminItems {

    private String transaction;
    private int symbol;
    private int enter;

    public AdminItems(int symbol, String transaction, int enter) {
        this.transaction = transaction;
        this.symbol = symbol;
        this.enter = enter;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public int getEnter() {
        return enter;
    }

    public void setEnter(int enter) {
        this.enter = enter;
    }
}
