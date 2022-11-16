package com.example.demo;

import com.example.demo.model.Animal;
import com.example.demo.model.DataProvider;
import com.example.demo.model.Dog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Dog dog1 = new Dog(1, "Lab", 15, "crafty", 599.99, true, "Whistles" );
        Dog dog2 = new Dog(2, "Bulldog", 8, "noisy", 700.00, true, "gnarls" );
        Dog dog3 = new Dog(3, "Rottweiler", 12, "energetic", 645.85, false, "eats" );
        Dog dog4 = new Dog(4, "Cat", 17, "real", 299.99, true, "sneaks" );
        Dog dog5 = new Dog(5, "Sheppard", 10, "crafty", 599.99, true, "Hunts" );

        DataProvider.addAnimal(dog1);
        DataProvider.addAnimal(dog2);
        DataProvider.addAnimal(dog3);
        DataProvider.addAnimal(dog4);
        DataProvider.addAnimal(dog5);



        launch();
    }
}