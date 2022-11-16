package com.example.demo;

import com.example.demo.model.InHouse;
import com.example.demo.model.Inventory;
import com.example.demo.model.OutSourced;
import com.example.demo.model.Part;
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

public class ModifyPartFormController implements Initializable {

    public Label MachineIdLabel;
    Stage stage;
    Parent scene;
    public RadioButton InHouseRadioBtn;
    public RadioButton OutSourcedRadioBtn;
    public TextField ModifyPartIdTxt;
    public TextField ModifyPartNameTxt;
    public TextField ModifyPartInvTxt;
    public TextField ModifyPartPriceTxt;
    public TextField ModifyPartMaxTxt;
    public TextField ModifyPartMachineTxt;
    public TextField ModifyPartMinTxt;
    public ToggleGroup PartTG;

    private int partId;
    public Part chosenPart;

    public void setPart(Part part) {
        chosenPart = part;
        partId = Inventory.getAllParts().indexOf(chosenPart);
        ModifyPartIdTxt.setText(Integer.toString(chosenPart.getId()));
        ModifyPartInvTxt.setText(Integer.toString(chosenPart.getStock()));
        ModifyPartNameTxt.setText(chosenPart.getName());
        ModifyPartPriceTxt.setText(Double.toString(chosenPart.getPrice()));
        ModifyPartMaxTxt.setText(Integer.toString(chosenPart.getMax()));
        ModifyPartMinTxt.setText(Integer.toString(chosenPart.getMax()));
        if(chosenPart instanceof InHouse) {
            InHouse in = (InHouse) chosenPart;
            InHouseRadioBtn.setSelected(true);
            MachineIdLabel.setText("Machine ID");
        }
        else{
            OutSourced out = (OutSourced) chosenPart;
            InHouseRadioBtn.setSelected(true);
            MachineIdLabel.setText("Company Name");
        }
    }



    public void onActionSave(ActionEvent actionEvent) {
        try{
            int id = Integer.parseInt(ModifyPartIdTxt.getText());
            String partName = ModifyPartNameTxt.getText();
            int inventory = Integer.parseInt(ModifyPartInvTxt.getText());
            double partPrice = Double.parseDouble(ModifyPartPriceTxt.getText());
            int partMin = Integer.parseInt(ModifyPartMinTxt.getText());
            int partMax = Integer.parseInt(ModifyPartMaxTxt.getText());
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
                    String company = ModifyPartMaxTxt.getText();
                    OutSourced part = new OutSourced(id,partName,partPrice, inventory,partMin, partMax, company);
                    Inventory.addPart(part);
                }
                else {
                    int machineId = Integer.parseInt(ModifyPartMaxTxt.getText());
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

    public void OnClickOutSource(ActionEvent actionEvent) {
        MachineIdLabel.setText("In-House");
    }

    public void onClickInHouse(ActionEvent actionEvent) {
        MachineIdLabel.setText("OutSourced");
    }
}
