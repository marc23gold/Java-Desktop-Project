package com.example.demo;

import com.example.demo.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class AddProductFormController implements Initializable {

    Stage stage;
    Parent scene;
    public TextField AddProductSearchTxt;
    public TextField AddProductIdTxt;
    public TextField AddProductNameTxt;
    public TextField AddProductInvTxt;
    public TextField AddProductPriceTxt;
    public TextField AddProductMaxTxt;
    public TextField AddProductMinTxt;
    public TableView<Part> AddProductPartTableView;
    public TableView<Part> AddProductAssociatedTableView;
    public TableColumn<Part, Integer> PartIdTableColumn;
    public TableColumn<Part, String> PartNameTableColumn;
    public TableColumn<Part, Double> PriceTableColumn;
    public TableColumn<Part, Integer> InventoryTableColumn;
    public TableColumn<Product, Integer> AssociatedPartIdTableColumn;
    public TableColumn<Product, String> AssociatedPartNameTableColumn;
    public TableColumn<Product, Integer> AssociatedInventoryTableColumn;
    public TableColumn<Product, Double> AssociatedPriceTableColumn;

    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    public ObservableList<Part> parts = FXCollections.observableArrayList();

    private int getId() {
        int id = 1;
        for(int i = 0; i < Inventory.getAllProducts().size(); i++) {
            if (Inventory.getAllProducts().get(i).getId() == id) {
                id++;
            }
        }
        return id;
    }

    public void updateParts() {
        AddProductPartTableView.setItems(Inventory.getAllParts());
    }

    public void updateAssociatedParts() {
        AddProductAssociatedTableView.setItems(associatedParts);
    }
    public void onActionAdd(ActionEvent actionEvent) {
        Part chosenPart = AddProductPartTableView.getSelectionModel().getSelectedItem();
        if (chosenPart != null) {
            associatedParts.add(chosenPart);
            updateParts();
            updateAssociatedParts();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Are you sure you want to proceed with this action?");
            alert.setHeaderText("Are you sure you want to proceed with this action?");
            alert.setContentText("Are you sure you want to proceed with this action?");
            alert.showAndWait();
        }

        
    }

    public void onActionRemoveAssociatedPart(ActionEvent actionEvent) {
        Part parts = AddProductAssociatedTableView.getSelectionModel().getSelectedItem();

        if(parts != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure you want to proceed with this action?");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure you want to proceed with this action?");
            Optional<ButtonType> result = alert.showAndWait();

            associatedParts.remove(parts);
            updateParts();
            updateAssociatedParts();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Are you sure you want to proceed with this action?");
            alert.setHeaderText("Are you sure you want to proceed with this action?");
            alert.setContentText("Are you sure you want to proceed with this action?");
            alert.showAndWait();
        }
    }

    public void onActionSave(ActionEvent actionEvent) throws IOException {
        try{
            Product product = new Product();
            product.setId(getId());
            product.setName(AddProductNameTxt.getText());
            product.setStock(Integer.parseInt(AddProductInvTxt.getText()));
            product.setMax(Integer.parseInt(AddProductMaxTxt.getText()));
            product.setMin(Integer.parseInt(AddProductMinTxt.getText()));
            product.setPrice(Double.parseDouble(AddProductPriceTxt.getText()));
            product.addAssociatedPart((Part) associatedParts);
            Inventory.addProduct(product);

        if(Integer.parseInt(AddProductMaxTxt.getText()) < Integer.parseInt(AddProductMinTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Set the Proper Min and Max");
            alert.setHeaderText("Please Enter a correct Min and Max");
            alert.setContentText("Please Enter a valid Min and Max values");
            alert.showAndWait();
        }
        else if(Integer.parseInt(AddProductInvTxt.getText()) < Integer.parseInt(AddProductMinTxt.getText())|| Integer.parseInt(AddProductInvTxt.getText()) > Integer.parseInt(AddProductMaxTxt.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Set the proper Min and Max");
            alert.setHeaderText("Please Enter a correct Min and Max");
            alert.setContentText("Please Enter a valid Min and Max values");
            alert.showAndWait();
        }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save");
            alert.setHeaderText("Would you like to Save?");
            alert.setContentText("Would you like to save?");
            alert.showAndWait();

            stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/MainMenue.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NumberFormatException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter valid value for each text field");
            alert.showAndWait();

        }
    }

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure you want to proceed with this action?");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure you want to proceed with this action?");
        Optional<ButtonType> result = alert.showAndWait();

        stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/MainForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Initialized");

        parts = Inventory.getAllParts();

        PartIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        InventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        AddProductPartTableView.setItems(parts);

        AssociatedInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        AddProductAssociatedTableView.setItems(associatedParts);

    }

    public void onActionSearch(ActionEvent actionEvent) {
        String query = AddProductSearchTxt.getText();
        ObservableList parts = Inventory.lookupPart(query);

        if (parts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("None Found");
            alert.setHeaderText(query + " was not found");
            alert.showAndWait();
        } else {
            AddProductPartTableView.setItems(parts);
        }
    }
}
