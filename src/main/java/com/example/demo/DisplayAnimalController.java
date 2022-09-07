package com.example.demo;

import com.example.demo.model.Animal;
import com.example.demo.model.DataProvider;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class DisplayAnimalController implements Initializable {
    Stage stage;
    Parent scene;
    public TableColumn<Animal, Integer> animalIdCol;
    public TableColumn<Animal, String> animalBreedCol;
    public TableColumn<Animal, Integer> animalLifespanCol;
    public TableColumn<Animal, Double> animalPriceLbl;
    public TableView<Animal> animalTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
        animalTableView.setItems(DataProvider.getAllAnimals());

        animalIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        animalBreedCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
        animalLifespanCol.setCellValueFactory(new PropertyValueFactory<>("lifespan"));
        animalPriceLbl.setCellValueFactory(new PropertyValueFactory<>("price"));
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
