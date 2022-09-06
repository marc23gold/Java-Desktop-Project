package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimalDetailsController implements Initializable {
    public Label animalIdLbl;
    public Label animalBreedLbl;
    public Label animalLifespanLbl;
    public Label animalBehaviorLbl;
    public Label animalPriceLbl;
    public Label animalVaccLbl;
    public Label specialLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(" I am initialized");
    }

    public void onActionDisplayMainMenue(ActionEvent actionEvent) {
    }
}
