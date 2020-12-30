/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class ModifyProductController implements Initializable {
    
    Inventory inv;
    Product selectedProd;
    Part selectedPart;
    double total;
    
    @FXML private TextField ID;
    @FXML private TextField Name;
    @FXML private TextField Level;
    @FXML private TextField Price;
    @FXML private TextField Max;
    @FXML private TextField Min;
    
    @FXML private TextField PartsSearchField;
    
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> PartId;
    @FXML private TableColumn<Part, String> PartName;
    @FXML private TableColumn<Part, Integer> InventoryLevel;
    @FXML private TableColumn<Part, Double> PartPrice;
    
    @FXML private TableView<Part> associatedTable;
    @FXML private TableColumn<Part, Integer> AssociatedId;
    @FXML private TableColumn<Part, String> AssociatedName;
    @FXML private TableColumn<Part, Integer> AssociatedInventoryLevel;
    @FXML private TableColumn<Part, Double> AssociatedPrice;  
    
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partInvSearch = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPartList = FXCollections.observableArrayList();
    
    public ModifyProductController(Inventory inv, Product prod){
        this.inv = inv;
        this.selectedProd = prod;
    }
    
    public void AddButtonPressed(ActionEvent event) throws IOException{
        Part addPart = partTable.getSelectionModel().getSelectedItem(); 
        if (associatedPartList.contains(addPart)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding product");
            alert.setHeaderText("Cannot add product");
            alert.setContentText("Cannot have duplicate part");
            alert.showAndWait();
            return;
        }
        if (addPart != null){
            associatedPartList.add(addPart);
        }
    }
    
    public void SaveButtonPressed(ActionEvent event) throws IOException{
        
        
        if (Name.getText().isEmpty()){
            AlertMessage.errorProd(1, Name);
            return;
        }
        if (Level.getText().isEmpty()){
            AlertMessage.errorProd(10, Level);
            return;
        }
        if (Price.getText().isEmpty()){
            AlertMessage.errorProd(11, Price);
            return;
        }
        if (Min.getText().isEmpty()){
            AlertMessage.errorProd(12, Min);
            return;
        }
        if (Max.getText().isEmpty()){
            AlertMessage.errorProd(13, Max);
            return;
        }
        
        if(Integer.parseInt(Level.getText()) > Integer.parseInt(Max.getText()) || Integer.parseInt(Level.getText()) < Integer.parseInt(Min.getText())){
            AlertMessage.errorProd(2, Level);
            return;
        }
        if(Integer.parseInt(Min.getText()) < 0){
            AlertMessage.errorProd(3, Min);
            return;
        }
        if(Integer.parseInt(Max.getText()) < Integer.parseInt(Min.getText())){
            AlertMessage.errorProd(4, Max);
            return;
        }
        if (selectedProd.getAllAssociatedParts().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding product");
            alert.setHeaderText("Cannot add product");
            alert.setContentText("Must have at least 1 associated part");
            alert.showAndWait();
            return;
        }
        if (associatedPartList.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding product");
            alert.setHeaderText("Cannot add product");
            alert.setContentText("Must have at least 1 associated part");
            alert.showAndWait();
            return;
        }
        for (Part p: associatedPartList){
            total += p.getPrice();         
        }
        if (total > Double.parseDouble(Price.getText().trim())){
            total = 0;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding product");
            alert.setHeaderText("Cannot add product");
            alert.setContentText("Associated Part cost cannot exceed Product cost");
            alert.showAndWait();
            return;
        }
        
        
        inv.deleteProduct(selectedProd);
        Product newProd = new Product(Integer.parseInt(ID.getText().trim()), Name.getText().trim(), Double.parseDouble(Price.getText().trim()),
        Integer.parseInt(Level.getText().trim()),Integer.parseInt(Min.getText().trim()),
        Integer.parseInt(Max.getText().trim()));
        for (Part p: associatedPartList){
            selectedPart = p;
            newProd.addAssociatedPart(selectedPart);
        }
        inv.addProduct(newProd);
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Controller.MainScreenController controller = new Controller.MainScreenController(inv);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    // displays searched item
    public void SearchPartButtonPushed(){
        if (!PartsSearchField.getText().isEmpty()){
           partInvSearch.clear();
           for (Part p: inv.getAllParts()){
               if (p.getName().contains(PartsSearchField.getText().trim())){
                   partInvSearch.add(p);
               }
           }
           partTable.setItems(partInvSearch);
           partTable.refresh();
        }
            
    }
    
    public void DeleteButtonPressed(){
        selectedPart = associatedTable.getSelectionModel().getSelectedItem();        
        
        if(selectedPart != null){
        associatedTable.getItems().remove(selectedPart);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Deleted");
        alert.setHeaderText("Deleted");
        alert.setContentText(selectedPart.getName() + " was deleted");
        alert.showAndWait();
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
        alert.setContentText("Product not modified cancel button pressed");
        alert.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        partInventory.setAll(inv.getAllParts());
        
        ID.setText(Integer.toString(selectedProd.getId()));
        ID.setDisable(true);
        Name.setText(selectedProd.getName());
        Level.setText(Integer.toString(selectedProd.getStock()));
        Price.setText(Double.toString(selectedProd.getPrice()));
        Max.setText(Integer.toString(selectedProd.getMax()));
        Min.setText(Integer.toString(selectedProd.getMin()));
        
        //sets up columns in table
        PartId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        InventoryLevel.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        //sets test data into table
        partTable.setItems(partInventory);
        
        associatedPartList.setAll(selectedProd.getAllAssociatedParts());
       
        AssociatedId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        AssociatedName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        AssociatedInventoryLevel.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        AssociatedPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        //sets test data into table
        associatedTable.setItems(associatedPartList);
    }    
    
}
