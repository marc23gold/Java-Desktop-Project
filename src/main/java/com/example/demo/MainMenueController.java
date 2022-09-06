package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class MainMenueController implements Initializable  {

    Stage stage;
    Parent scene;
    public Label TheLabel;

    public int count = 1;
    @FXML
    private Label welcomeText;
    private Parent FXMLLoader;

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

    public void onActionCreateAnimal(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/resources/com/example/demo/MainMenue.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDisplayAnimal(ActionEvent actionEvent) {
    }

    public void onActionExitAnimal(ActionEvent actionEvent) {
    }
}