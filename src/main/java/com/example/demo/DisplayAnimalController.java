package com.example.demo;

import com.example.demo.model.Animal;
import com.example.demo.model.DataProvider;
import com.example.demo.model.Dog;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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


    public boolean search(int id) {
        for(Animal dog : DataProvider.getAllAnimals()){
            if(dog.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean update(int id, Animal animal) {
        int index = -1;
        for(Animal dog : DataProvider.getAllAnimals()) {
            index++;
            if(dog.getId() == id){
                DataProvider.getAllAnimals().set(index, animal);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        for(Animal dog : DataProvider.getAllAnimals()) {
            if(dog.getId() == id) {
                return DataProvider.getAllAnimals().remove(dog);
            }
        }
        return false;
    }

    public Animal selectAnimal(int id) {
        for(Animal dog : DataProvider.getAllAnimals())
            if(dog.getId() == id) {
                return dog;
        }
        return null;
    }

    public ObservableList<Animal> filter(String breed) {
        if(!(DataProvider.getAllFilteredAnimals().isEmpty())) {
            DataProvider.getAllFilteredAnimals().clear();
        }
        for(Animal dog : DataProvider.getAllAnimals()) {
                if(dog.getBreed().contains(breed)) {
                    DataProvider.getAllFilteredAnimals().add(dog);
                }
        }

        if(DataProvider.getAllFilteredAnimals().isEmpty()) {
            return DataProvider.getAllAnimals();
        } else {
            return DataProvider.getAllFilteredAnimals();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("I am initialized");
        animalTableView.setItems(DataProvider.getAllAnimals());
        //animalTableView.setItems(filter("Grey Hound"));


        animalIdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        animalBreedCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
        animalLifespanCol.setCellValueFactory(new PropertyValueFactory<>("lifespan"));
        animalPriceLbl.setCellValueFactory(new PropertyValueFactory<>("price"));

        if(update(5, new Dog(5, "Grey Hound", 13, "funny", 35.44, true, "kind"))) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }

        if(delete(3)) {
            System.out.println("Deleted success");
        } else {
            System.out.println("Delete failed");
        }

        animalTableView.getSelectionModel().select(selectAnimal(3));
    }

    public void onActionDisplayAnimalMenue(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/demo/AnimalDetailsMenue.fxml"));
        loader.load();

        AnimalDetailsController ADMController = loader.getController();
        ADMController.sendAnimal(animalTableView.getSelectionModel().getSelectedItem());


        stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
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
