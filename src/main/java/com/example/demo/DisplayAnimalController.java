package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayAnimalController implements Initializable {
    public TableColumn animalIdCol;
    public TableColumn animalBreedCol;
    public TableColumn animalLifespanCol;
    public TableColumn animalPriceLbl;
    public TableView animalTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }

    public void onActionDisplayAnimalMenue(ActionEvent actionEvent) {
    }

    public void onActionDisplayMainMenue(ActionEvent actionEvent) {
    }
}
