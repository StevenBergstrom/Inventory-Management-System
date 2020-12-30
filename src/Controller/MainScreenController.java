/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Part;
import Model.Product;
import Model.Inventory;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author bergs
 */
public class MainScreenController implements Initializable {
    
    Inventory inv;

    @FXML private Label PartsLabel;
    @FXML private Label ProductsLabel;
    @FXML private Label ApplicationLabel;
    
    @FXML private TextField PartsSearchField;
    @FXML private TextField ProductsSearchField;
    
    @FXML private javafx.scene.control.Button closeButton;
    
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> PartId;
    @FXML private TableColumn<Part, String> PartName;
    @FXML private TableColumn<Part, Integer> InventoryLevel;
    @FXML private TableColumn<Part, Double> Price;
    
    @FXML private TableView<Product> prodTable;
    @FXML private TableColumn<Product, Integer> ProductId;
    @FXML private TableColumn<Product, String> ProductName;
    @FXML private TableColumn<Product, Integer> ProductInventoryLevel;
    @FXML private TableColumn<Product, Double> ProductPrice;
    
    private ObservableList<Part> partInv = FXCollections.observableArrayList();
    private ObservableList<Product> prodInv = FXCollections.observableArrayList();
    private ObservableList<Part> partInvSearch = FXCollections.observableArrayList();
    private ObservableList<Product> prodInvSearch = FXCollections.observableArrayList();
    
    
    public MainScreenController(Inventory inv){
        this.inv = inv;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       partInv = inv.getAllParts(); 
       
       //sets up columns in table
       PartId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
       PartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
       InventoryLevel.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
       Price.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
       //sets test data into table
       partTable.setItems(partInv);
       
       prodInv = inv.getAllProducts();
       
       //sets up columns in table
       ProductId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
       ProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
       ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
       ProductPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
       //sets test data into table
       prodTable.setItems(prodInv);
       
    }  
   
    //Changes scene to Add Parts screen when Add button is pushed
    public void AddPartsButtonPushed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddParts.fxml"));
        AddPartsController controller = new AddPartsController(inv);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    //Changes scene to Modify Parts screen when Modify button is pushed
    public void ModifyPartsButtonPushed(ActionEvent event) throws IOException{
        Part selected = partTable.getSelectionModel().getSelectedItem();
        if (inv.getAllParts().isEmpty()){
            errorWindow(1);
            return;
        }
        if (!inv.getAllParts().isEmpty() && selected == null){
            errorWindow(2);
            return;
        } else {
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyParts.fxml"));
        ModifyPartsController controller = new ModifyPartsController(inv, selected);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
        }
    }
    
    //Changes scene to Add Products screen when Add button is pushed
    public void AddProductsButtonPushed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        AddProductController controller = new AddProductController(inv);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    //Changes scene to Modify Products screen when Modify button is pushed
    public void ModifyProductsButtonPushed(ActionEvent event) throws IOException{
        Product selected = prodTable.getSelectionModel().getSelectedItem();
        if (inv.getAllProducts().isEmpty()){
            errorWindow(1);
            return;
        }
        if (!inv.getAllProducts().isEmpty() && selected == null){
            errorWindow(2);
            return;
        } else {
            
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        ModifyProductController controller = new ModifyProductController(inv, selected);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }
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
    
    // displays searched item
    public void SearchProductButtonPushed(){
        if (!ProductsSearchField.getText().isEmpty()){
           prodInvSearch.clear();
           for (Product p: inv.getAllProducts()){
               if (p.getName().contains(ProductsSearchField.getText().trim())){
                   prodInvSearch.add(p);
               }
           }
           prodTable.setItems(prodInvSearch);
           prodTable.refresh();
        }

    }
    
    
    public void DeletePartButtonPushed(){
        Part selected = partTable.getSelectionModel().getSelectedItem();        
        inv.deletePart(selected);
        partTable.setItems(partInv);
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Deleted");
        alert.setHeaderText("Deleted");
        alert.setContentText(selected.getName() + " was deleted");
        alert.showAndWait();
    }
    
    public void DeleteProdButtonPushed(){
        Product selected = prodTable.getSelectionModel().getSelectedItem();        
        inv.deleteProduct(selected);
        prodTable.setItems(prodInv);
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Deleted");
        alert.setHeaderText("Deleted");
        alert.setContentText(selected.getName() + " was deleted");
        alert.showAndWait();
                
    }
      
    public void ExitButtonPushed(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    
    public void errorWindow(int code){
     if (code == 1){
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Error");
         alert.setTitle("Empty Inventory");
         alert.setHeaderText("There is no Inventory");
         alert.showAndWait();
     }
     if (code == 2){
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Error");
         alert.setTitle("Invalid Selection");
         alert.setHeaderText("Please select an item");
         alert.showAndWait();
     }
    }
}
