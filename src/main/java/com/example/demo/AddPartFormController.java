package com.example.demo;

import com.example.demo.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class AddPartFormController implements Initializable {

    Stage stage;
    Parent scene;

    public RadioButton InHouseRadioBtn;
    public RadioButton OutSourcedRadioBtn;
    public Label AddPartChangingLabel;
    public TextField AddPartIdTxt;
    public TextField AddPartNameTxt;
    public TextField AddPartInvTxt;
    public TextField AddPartPriceTxt;
    public TextField AddPartMaxTxt;
    public TextField AddPartMachineTxt;
    public TextField AddPartMinTxt;
    public ToggleGroup PartTG;

    public static int staticId() {
        int id= 1;
        for(int i = 0; i < Inventory.getAllProducts().size(); i++) {
            id++;
        }
        return id;
    }


    public void onActionSave(ActionEvent actionEvent) {

        if(InHouseRadioBtn.isSelected()) {
            System.out.println("In house is currently selected");
        } else if(OutSourcedRadioBtn.isSelected()) {
            System.out.println("Out Sourced is currently selected");
        }

        //Work on the function
        try{
            int id = staticId();
            String partName = AddPartNameTxt.getText();
            int inventory = Integer.parseInt(AddPartInvTxt.getText());
            double partPrice = Double.parseDouble(AddPartPriceTxt.getText());
            int partMin = Integer.parseInt(AddPartMinTxt.getText());
            int partMax = Integer.parseInt(AddPartMaxTxt.getText());
            if(partMax < partMin) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Set the Proper Min and Max");
                alert.setHeaderText("Please Enter a correct Min and Max");
                alert.setContentText("Please Enter a valid Min and Max values");
                alert.showAndWait();
            }
            else if(inventory < partMin|| inventory >partMax) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Set the Proper Min and Max");
                alert.setHeaderText("Please Enter a correct Min and Max");
                alert.setContentText("Please Enter a valid Min and Max values");
                alert.showAndWait();
            }
            else {
                if(OutSourcedRadioBtn.isSelected()) {
                    String company = AddPartMachineTxt.getText();
                    OutSourced part = new OutSourced(id,partName,partPrice, inventory,partMin, partMax, company);
                    Inventory.addPart(part);
                }
                else {
                    int machineId = Integer.parseInt(AddPartMachineTxt.getText());
                    InHouse part = new InHouse(id,partName, partPrice,inventory,partMin,partMax,machineId);
                    Inventory.addPart(part);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Save");
                alert.setHeaderText("Would you like to Save?");
                alert.setContentText("Would you like to save?");
                alert.showAndWait();
            }
            stage =(Stage)((Button) actionEvent.getSource()).getScene().getWindow();
            scene = load(Objects.requireNonNull(getClass().getResource("/com/example/demo/MainMenue.fxml")));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NumberFormatException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter valid value for each text field");
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
        System.out.println("Initialized");
    }

    public void onClickInHouse(ActionEvent actionEvent) {
        AddPartChangingLabel.setText("In-House");
    }

    public void onClickOutSource(ActionEvent actionEvent) {
        AddPartChangingLabel.setText("OutSourced");
    }
}
