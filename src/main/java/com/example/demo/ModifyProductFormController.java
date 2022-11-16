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

public class ModifyProductFormController implements Initializable {

    Stage stage;
    Parent scene;
    public TextField ModifyProductSearchTxt;
    public TextField ModifyProductIdTxt;
    public TextField ModifyProductNameTxt;
    public TextField ModifyProductInvTxt;
    public TextField ModifyProductPriceTxt;
    public TextField ModifyProductMaxTxt;
    public TextField ModifyProductMinTxt;
    public TableView<Part> ModifyProductPartTableView;
    public TableView<Part> ModifyProductAssociatedTableView;
    public TableColumn<Part, Integer> ProductIdTableColumn;
    public TableColumn<Part, String> ProductNameTableColumn;
    public TableColumn<Part, Integer> InventoryTableColumn;
    public TableColumn<Part, Double> PriceTableColumn;
    public TableColumn<Product, ObservableList<Part>> AssociatedProductIdTableColumn;
    public TableColumn<Product, String> AssociatedProductNameTableColumn;
    public TableColumn<Product, Integer> AssoicatedInventoryTableColumn;
    public TableColumn<Product, Double> AssociatedPriceTableColumn;

    private Product chosenProduct;

    private int productId;
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();

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
        ModifyProductPartTableView.setItems(Inventory.getAllParts());
    }

    public void updateAssociatedParts() {
        ModifyProductAssociatedTableView.setItems(associatedPart);
    }

    public void setProduct(Product product) {
        chosenProduct = product;
        productId = Inventory.getAllParts().indexOf(chosenProduct);
        ModifyProductIdTxt.setText(Integer.toString(chosenProduct.getId()));
        ModifyProductInvTxt.setText(Integer.toString(chosenProduct.getStock()));
        ModifyProductNameTxt.setText(chosenProduct.getName());
        ModifyProductPriceTxt.setText(Double.toString(chosenProduct.getPrice()));
        ModifyProductMaxTxt.setText(Integer.toString(chosenProduct.getMax()));
        ModifyProductMinTxt.setText(Integer.toString(chosenProduct.getMax()));
        associatedPart.addAll(chosenProduct.getAllAssociatedParts());
    }

    public void onActionSearch(ActionEvent actionEvent) {
        String query = ModifyProductSearchTxt.getText();
        ObservableList parts = Inventory.lookupPart(query);

        if (parts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("None Found");
            alert.setHeaderText(query + " was not found");
            alert.showAndWait();
        } else {
            ModifyProductPartTableView.setItems(parts);
        }
    }

    public void onActionAdd(ActionEvent actionEvent) {
        Part chosenPart = ModifyProductPartTableView.getSelectionModel().getSelectedItem();
        if (chosenPart != null) {
            associatedPart.add(chosenPart);
            updateParts();
            updateAssociatedParts();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Are you sure?");
            alert.setHeaderText("Are you sure you want to proceed with this action?");
            alert.setContentText("Are you sure you want to proceed with this action?");
            alert.showAndWait();
        }


    }

    public void onActionSave(ActionEvent actionEvent) {
        try{
            Product product = new Product();
            product.setId(getId());
            product.setName(ModifyProductNameTxt.getText());
            product.setStock(Integer.parseInt(ModifyProductInvTxt.getText()));
            product.setMax(Integer.parseInt(ModifyProductMaxTxt.getText()));
            product.setMin(Integer.parseInt(ModifyProductMinTxt.getText()));
            product.setPrice(Double.parseDouble(ModifyProductPriceTxt.getText()));
            product.addAssociatedPart((Part) associatedPart);
            Inventory.updateProduct(productId, product);

            if(Integer.parseInt(ModifyProductMaxTxt.getText()) < Integer.parseInt(ModifyProductMinTxt.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Set the Proper Min and Max");
                alert.setHeaderText("Please Enter a correct Min and Max");
                alert.setContentText("Please Enter a valid Min and Max values");
                alert.showAndWait();
            }
            else if(Integer.parseInt(ModifyProductInvTxt.getText()) < Integer.parseInt(ModifyProductMinTxt.getText())|| Integer.parseInt(ModifyProductInvTxt.getText()) > Integer.parseInt(ModifyProductMaxTxt.getText())) {
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

    public void onActionRemoveAssociatedPart(ActionEvent actionEvent) {
        Part parts = ModifyProductAssociatedTableView.getSelectionModel().getSelectedItem();

        if(parts != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure you want to proceed?");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure you want to proceed with this action?");
            Optional<ButtonType> result = alert.showAndWait();

            associatedPart.remove(parts);
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

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/MainForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.print("Initialized");


        ProductIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        InventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ModifyProductPartTableView.setItems(Inventory.getAllParts());

        AssociatedProductIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedProductNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssoicatedInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ModifyProductAssociatedTableView.setItems(associatedPart);

        updateParts();
        updateAssociatedParts();
    }
}
