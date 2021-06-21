package com.oguz.marketapp;

import com.oguz.marketapp.Customer;
import com.oguz.marketapp.Date;

public class CreditCard {

    private String cardholderName;
    private int cardNumber;
    private Date expirationDate;
    int cvc;
    int cardLimit;
    String expdate;

    public CreditCard(String cardholderName, int cardNumber, Date expirationDate, int cvc) {
        this.cardholderName = cardholderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
    }

    public CreditCard(String cardholderName, String cardNumber, int cardLimit, String expdate, String cvc) {
        this.cardholderName = cardholderName;
        this.cardNumber = Integer.parseInt(cardNumber);
        this.cardLimit = cardLimit;
        this.expdate = expdate;
        this.cvc = Integer.parseInt(cvc);
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }
}
