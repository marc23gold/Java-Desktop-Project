package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class DisplayAnimalController implements Initializable {
    Stage stage;
    Parent scene;
    public TableColumn animalIdCol;
    public TableColumn animalBreedCol;
    public TableColumn animalLifespanCol;
    public TableColumn animalPriceLbl;
    public TableView animalTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }

    public void onActionDisplayAnimalMenue(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/AnimalDetailsMenue.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDisplayMainMenue(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/MainMenue.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
