package com.example.ngosocialapp;

import com.example.ngosocialapp.NGO;

import java.io.Serializable;

public class event implements Serializable
{
    String NgoName,name,desc,date;
    Boolean active;

    public event() {
    }

    public event(String ngoName, String name, String desc, String date, Boolean active) {
        NgoName = ngoName;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.active = active;
    }

    public String getNgoName() {
        return NgoName;
    }

    public void setNgoName(String ngoName) {
        NgoName = ngoName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
