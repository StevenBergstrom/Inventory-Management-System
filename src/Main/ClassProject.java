/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bergs
 */
public class ClassProject extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Inventory inv = new Inventory();
        addTestData(inv);
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Controller/MainScreen.fxml"));
        Controller.MainScreenController controller = new Controller.MainScreenController(inv);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }    
    
    void addTestData(Inventory inv){
        Part A = new InHouse(1, "part A", 1.99, 5, 5, 10, 101);
        inv.addPart(A);
        Part B = new InHouse(2, "part B", 2.99, 5, 5, 10, 101);
        inv.addPart(B);
        Part C = new InHouse(3, "part C", 3.99, 5, 5, 10, 101);
        inv.addPart(C);
        
        Part D = new OutSourced(4, "part D", 4.99, 7, 5, 10, "Apple");
        inv.addPart(D);
        Part E = new OutSourced(5, "part E", 5.99, 7, 5, 10, "Apple");
        inv.addPart(E);
        Part F = new OutSourced(6, "part F", 6.99, 7, 5, 10, "Apple");
        inv.addPart(F);
        
        Product Z = new Product(1, "Prod Z", 19.99, 5, 5, 10);
        Z.addAssociatedPart(A);
        Z.addAssociatedPart(B);
        inv.addProduct(Z);
        Product Y = new Product(2, "Prod Y", 18.99, 5, 5, 10);
        Y.addAssociatedPart(C);
        Y.addAssociatedPart(D);
        inv.addProduct(Y);
        Product X = new Product(3, "Prod X", 17.99, 5, 5, 10);
        X.addAssociatedPart(E);
        X.addAssociatedPart(F);
        inv.addProduct(X);
        
        
    }
}
