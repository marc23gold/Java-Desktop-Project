package com.example.demo.model;

import com.example.demo.model.Animal;

public class Dog extends Animal{

    private String special;

    public Dog(int id, String breed, int lifespan, String behavior, double price, boolean vaccinated,String special) {
        super(id, breed, lifespan, behavior, price, vaccinated);
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}
