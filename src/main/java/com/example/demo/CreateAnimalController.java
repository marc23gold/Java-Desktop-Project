package com.example.demo;

import com.example.demo.model.DataProvider;
import com.example.demo.model.Dog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class CreateAnimalController implements Initializable {

    Stage stage;
    Parent scene;
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

    public void onActionDisplayMainMenue(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, do you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/MainMenue.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    public void onActionSaveAnimal(ActionEvent actionEvent) throws IOException {

        try{
            int id = Integer.parseInt(animalIdTxt.getText());
            String breed = animalBreedTxt.getText();
            int lifespan = Integer.parseInt(animalLifespanTxt.getText());
            String behavior = animalBehaviorTxt.getText();
            double price = Double.parseDouble(animalPriceTxt.getText());
            boolean isVaccinated;
            String special = null;

            if(vaccYesBtn.isSelected()) {
                isVaccinated = true;
            } else {
                isVaccinated = false;
            }

            DataProvider.addAnimal(new Dog( id, breed, lifespan, behavior, price, isVaccinated, special));

            stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/MainMenue.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter valid value for each text field");
            alert.showAndWait();

        }



    }
}
