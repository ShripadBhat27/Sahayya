package com.example.ngosocialapp;

import java.io.Serializable;

public class NGO implements Serializable {
    String name,email,year,sector,address,phone,desc,website,insta,accountNum,reAccount,IFSC,recName;
    int postcnt;
    public NGO(){

    }
    public NGO(String name, String email, String year, String sector, String address, String phone, String desc, String website, String insta, String accountNum, String reAccount, String IFSC, String recName,int postcnt) {
        this.name = name;
        this.email = email;
        this.year = year;
        this.sector = sector;
        this.address = address;
        this.phone = phone;
        this.desc = desc;
        this.website = website;
        this.insta = insta;
        this.accountNum = accountNum;
        this.reAccount = reAccount;
        this.IFSC = IFSC;
        this.recName = recName;
        this.postcnt=0;
    }

    public int getPostcnt() {
        return postcnt;
    }

    public void setPostcnt(int postcnt) {
        this.postcnt = postcnt;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta(String insta) {
        this.insta = insta;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getReAccount() {
        return reAccount;
    }

    public void setReAccount(String reAccount) {
        this.reAccount = reAccount;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public NGO(String name, String email, String year) {
        this.name = name;
        this.email = email;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
