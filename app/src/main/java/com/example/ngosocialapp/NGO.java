package com.example.ngosocialapp;

public class NGO {
    String name,email,year;

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
