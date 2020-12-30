/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bergs
 *
 */

public class ModifyPartsController implements Initializable {

    Inventory inv;
    Part selectedPart;
    
    @FXML private RadioButton InHouse;
    @FXML private RadioButton Outsourced;
    @FXML private Label BottomLabel;
    private ToggleGroup Vendor;
    @FXML private TextField ID;
    @FXML private TextField Name;
    @FXML private TextField Level;
    @FXML private TextField Price;
    @FXML private TextField Max;
    @FXML private TextField Min;
    @FXML private TextField Last;
    
    

    ModifyPartsController(Inventory inv, Part part) {
        this.inv = inv;
        this.selectedPart = part;      
    }
    
    public void SaveButtonPressed(ActionEvent event) throws IOException{
        
        if (Name.getText().isEmpty()){
            AlertMessage.errorPart(1, Name);
            return;
        }
        if (Level.getText().isEmpty()){
            AlertMessage.errorPart(10, Level);
            return;
        }
        if (Price.getText().isEmpty()){
            AlertMessage.errorPart(11, Price);
            return;
        }
        if (Min.getText().isEmpty()){
            AlertMessage.errorPart(12, Min);
            return;
        }
        if (Max.getText().isEmpty()){
            AlertMessage.errorPart(13, Max);
            return;
        }
        if(Integer.parseInt(Level.getText()) > Integer.parseInt(Max.getText()) || Integer.parseInt(Level.getText()) < Integer.parseInt(Min.getText())){
            AlertMessage.errorPart(2, Level);
            return;
        }
        if(Integer.parseInt(Min.getText()) < 0){
            AlertMessage.errorPart(3, Min);
            return;
        }
        if(Integer.parseInt(Max.getText()) < Integer.parseInt(Min.getText())){
            AlertMessage.errorPart(4, Max);
            return;
        }
        if(Last.getText().isEmpty() && InHouse.isSelected()){
            AlertMessage.errorPart(5, Last);
            return;
        }
        
        if(Last.getText().isEmpty() && Outsourced.isSelected()){
            AlertMessage.errorPart(6, Last);
            return;
        }
        if(Double.parseDouble(Price.getText()) <= 0){
            AlertMessage.errorPart(7, Price);
            return;
        }
        
        if (InHouse.isSelected()){
        try{
            int testMachineID = Integer.parseInt(Last.getText());
            }catch (NumberFormatException ex) {
                AlertMessage.errorPart(9, Last);
                return;
            }
        }
        
        if (InHouse.isSelected() & selectedPart instanceof InHouse){
            UpdateItemInHouse();
        } else if (InHouse.isSelected() & selectedPart instanceof OutSourced){
            UpdateItemInHouse();
        } else if(Outsourced.isSelected() & selectedPart instanceof OutSourced){
            UpdateItemOutsourced();
        } else if (Outsourced.isSelected() & selectedPart instanceof InHouse){
            UpdateItemOutsourced();
        }
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Controller.MainScreenController controller = new Controller.MainScreenController(inv);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    
    private void UpdateItemInHouse(){
                
                if (selectedPart.getId() == Integer.parseInt(ID.getText())){
                    inv.deletePart(selectedPart);
                    inv.addPart(new InHouse(Integer.parseInt(ID.getText().trim()), Name.getText().trim(), Double.parseDouble(Price.getText().trim()),
                    Integer.parseInt(Level.getText().trim()),Integer.parseInt(Min.getText().trim()),
                    Integer.parseInt(Max.getText().trim()), Integer.parseInt(Last.getText().trim())));
                
                }           
    }
    private void UpdateItemOutsourced(){
    
                if (selectedPart.getId() == Integer.parseInt(this.ID.getText())){
                    inv.deletePart(selectedPart);
                    inv.addPart(new OutSourced(Integer.parseInt(ID.getText().trim()), Name.getText().trim(), Double.parseDouble(Price.getText().trim()),
                    Integer.parseInt(Level.getText().trim()),Integer.parseInt(Min.getText().trim()),
                    Integer.parseInt(Max.getText().trim()), Last.getText().trim()));
                
                }
    }
    
    public void CancelButtonPressed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Controller.MainScreenController controller = new Controller.MainScreenController(inv);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cancelled");
        alert.setHeaderText("Cancelled");
        alert.setContentText("Part not modified cancel button pressed");
        alert.showAndWait();
    }
    
    public void ChangeBottomLabel() {
        if (InHouse.isSelected()) {
            BottomLabel.setText("Machine ID");
        }
        else if (Outsourced.isSelected()){
            BottomLabel.setText("Company Name");
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    Vendor = new ToggleGroup();
    this.InHouse.setToggleGroup(Vendor);
    this.Outsourced.setToggleGroup(Vendor);
        
    if (selectedPart instanceof InHouse){
        InHouse part1 = (InHouse) selectedPart;
        InHouse.setSelected(true);
        ID.setText(Integer.toString(part1.getId()));
        ID.setDisable(true);
        Name.setText(part1.getName());
        Level.setText(Integer.toString(part1.getStock()));
        Price.setText(Double.toString(part1.getPrice()));
        Min.setText(Integer.toString(part1.getMin()));
        Max.setText(Integer.toString(part1.getMax()));
        BottomLabel.setText("Machine ID");
        Last.setText(Integer.toString(part1.getMachineID()));
    }
    else if(selectedPart instanceof OutSourced){
        OutSourced part2 = (OutSourced) selectedPart;
        Outsourced.setSelected(true);
        ID.setText(Integer.toString(part2.getId()));
        ID.setDisable(true);
        Name.setText(part2.getName());
        Level.setText(Integer.toString(part2.getStock()));
        Price.setText(Double.toString(part2.getPrice()));
        Min.setText(Integer.toString(part2.getMin()));
        Max.setText(Integer.toString(part2.getMax()));
        BottomLabel.setText("Company Name");
        Last.setText(part2.getCompanyName());
    }
    
    
    
    
    }    
    
}
