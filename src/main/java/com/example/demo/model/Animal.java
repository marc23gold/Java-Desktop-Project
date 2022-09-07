package com.example.demo.model;

public abstract class Animal {
    private int Id;
    private String breed;
    private int lifespan;
    private String behavior;
    private double price;
    private boolean vaccinated;

    public Animal(int id, String breed, int lifespan, String behavior, double price, boolean vaccinated) {
        Id = id;
        this.breed = breed;
        this.lifespan = lifespan;
        this.behavior = behavior;
        this.price = price;
        this.vaccinated = vaccinated;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }
}
