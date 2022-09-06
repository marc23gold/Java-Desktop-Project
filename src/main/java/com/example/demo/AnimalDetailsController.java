package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class AnimalDetailsController implements Initializable {

    Stage stage;
    Parent scene;
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

    public void onActionDisplayMainMenue(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/MainMenue.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
