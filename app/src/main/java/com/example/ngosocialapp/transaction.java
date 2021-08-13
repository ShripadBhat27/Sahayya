
package com.example.ngosocialapp;

public class transaction
{
    String donar,NGO,amount;

    public transaction() {
    }

    public transaction(String donar, String NGO, String amount) {
        this.donar = donar;
        this.NGO = NGO;
        this.amount = amount;
    }

    public String getDonar() {
        return donar;
    }

    public void setDonar(String donar) {
        this.donar = donar;
    }

    public String getNGO() {
        return NGO;
    }

    public void setNGO(String NGO) {
        this.NGO = NGO;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}