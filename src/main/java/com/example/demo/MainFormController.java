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
import java.util.ResourceBundle;

import static com.example.demo.model.Inventory.lookupProduct;
import static javafx.fxml.FXMLLoader.load;

public class MainFormController implements Initializable {

    Stage stage;
    Parent scene;
    public TableView<Part> MainPartTableView;
    public TextField PartsTxtField;
    public TextField ProductsTxtField;
    public TableView<Product> MainProductTableView;
    public TableColumn<Part, Integer> PartIDTableColumn;
    public TableColumn<Part, String> PartNameColumnView;
    public TableColumn<Part, Double> PartPriceTableColumn;
    public TableColumn<Part, Integer> PartInventoryTableColumn;
    public TableColumn<Product, Integer> ProductIdTableColumn;
    public TableColumn<Product, String> ProductNameTableColumn;
    public TableColumn<Product, Integer> ProductInventoryTableColumn;
    public TableColumn<Product, Double> ProductPriceTableColumn;


    public void onActionDeletePart(ActionEvent actionEvent) {
        Part selectedPart = MainPartTableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            return;
        }
        MainPartTableView.getItems().remove(selectedPart);
        Inventory.deletePart(selectedPart);
    }

    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {
        Part selectedModifyPart = MainPartTableView.getSelectionModel().getSelectedItem();
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/ModifyPartForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionAddPart(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/AddPartForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeleteProduct(ActionEvent actionEvent) {
        Product selectedProduct = MainProductTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            return;
        }
        MainProductTableView.getItems().remove(selectedProduct);
        Inventory.deleteProduct(selectedProduct);
    }

    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {
        Product selectedModifyProduct = MainProductTableView.getSelectionModel().getSelectedItem();
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/ModifyProductForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionAddProduct(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/AddProductForm.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }


    private static boolean firstTime = true;

    private void addTestPartData() {
        if (!firstTime) {
            return;
        }
        firstTime = false;
        InHouse one = new InHouse(1, "Annoying", 7.50, 45, 30, 50,20);
        Inventory.addPart(one);
        OutSourced two = new OutSourced(2, "Nail", 8.99, 43, 23, 60, "Nokia");
        Inventory.addPart(two);
    }

    private static boolean first = true;

    private void addTestProductData() {
        if (!first) {
            return;
        }
        first = false;
        Product first = new Product(null, 1, "Chair", 5.99, 50, 20, 40);
        Inventory.addProduct(first);
        Product second = new Product(null, 2, "Table", 56.99, 40, 23, 50);
        Inventory.addProduct(second);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addTestPartData();

        MainPartTableView.setItems(Inventory.getAllParts());


        PartIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumnView.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));


        addTestProductData();

        MainProductTableView.setItems(Inventory.getAllProducts());

        ProductIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProductInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("inventory"));


    }


    //Doing the parts search
    public void onActionSearchParts(ActionEvent actionEvent) {
        String query = PartsTxtField.getText();
        ObservableList parts = Inventory.lookupPart(query);

        if (parts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("None Found");
            alert.setHeaderText(query + " was not found");
            alert.showAndWait();
        } else {
            MainPartTableView.setItems(parts);
        }

    }


    //Doing the products search
    public void onActionSearchProducts(ActionEvent actionEvent) {
        String query = ProductsTxtField.getText();
        ObservableList products = Inventory.lookupPart(query);

        if (products.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("None Found");
            alert.setHeaderText(query + " was not found");
            alert.showAndWait();
        } else {
            MainPartTableView.setItems(products);
        }
    }
}





