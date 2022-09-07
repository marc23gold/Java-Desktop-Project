package com.example.demo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataProvider {

    private static ObservableList<Animal> allAnimals = FXCollections.observableArrayList();
    private static ObservableList<Animal> filteredAnimals = FXCollections.observableArrayList();

    public static void addAnimal(Animal animal) {
        allAnimals.add(animal);
    }

    public static ObservableList<Animal> getAllAnimals() {
        return allAnimals;
    }

    public static ObservableList<Animal> getAllFilteredAnimals() {
        return filteredAnimals;
    }
}
