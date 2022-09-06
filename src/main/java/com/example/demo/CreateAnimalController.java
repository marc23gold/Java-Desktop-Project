package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAnimalController implements Initializable {
    public TextField animalIdTxt;
    public TextField animalBreedTxt;
    public TextField animalLifespanTxt;
    public TextField animalBehaviorTxt;
    public TextField animalPriceTxt;
    public RadioButton vaccYesBtn;
    public RadioButton vaccNoBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }

    public void onActionDisplayMainMenue(ActionEvent actionEvent) {
    }

    public void onActionSaveAnimal(ActionEvent actionEvent) {
    }
}
