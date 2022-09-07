package com.example.demo;

import com.example.demo.model.Animal;
import com.example.demo.model.Dog;
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

    public void sendAnimal(Animal dog) {
        animalIdLbl.setText(String.valueOf(dog.getId()));
        animalBreedLbl.setText(String.valueOf(dog.getBreed()));
        animalLifespanLbl.setText(String.valueOf(dog.getLifespan()));
        animalPriceLbl.setText(String.valueOf(dog.getPrice()));
        animalBehaviorLbl.setText(String.valueOf(dog.getBehavior()));
        if(dog.isVaccinated()) {
            animalVaccLbl.setText("Yes");
        } else {
            animalVaccLbl.setText("No");
        }

        if(dog instanceof Dog) {
            specialLbl.setText(((Dog) dog).getSpecial());
        }
    }

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
