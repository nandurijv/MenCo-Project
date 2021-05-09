package com.heavycoders.myapplication.models;

public class Model {
    String city, name, number, state;

    public Model() {
    }

    public Model(String city, String name, String number, String state) {
        this.city = city;
        this.name = name;
        this.number = number;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
