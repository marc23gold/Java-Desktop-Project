package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenueController implements Initializable  {
    public Label TheLabel;

    public int count = 1;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }

    public void onButtonClick(ActionEvent actionEvent) {
        System.out.println("I am clicked");
        TheLabel.setText("You clicked the button " + count++);
    }

    public void onActionCreateAnimal(ActionEvent actionEvent) {
    }

    public void onActionDisplayAnimal(ActionEvent actionEvent) {
    }

    public void onActionExitAnimal(ActionEvent actionEvent) {
    }
}