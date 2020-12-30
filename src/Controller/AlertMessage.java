/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author bergs
 */
public class AlertMessage {
    
    public static void errorPart(int code, TextField field){
        fieldError(field);
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error adding part");
        alert.setHeaderText("Cannot add part");
        switch (code){
            case 1: {
                alert.setContentText("Please enter a name");
                break;
            }
            case 2: {
                alert.setContentText("Inventory Level must be equal to or between Min and Max");
                break;
            }
            case 3: {
                alert.setContentText("Min cannot be negative");
                break;
            }
            case 4: {
                alert.setContentText("Max cannot be less than Min");
                break;
            }
            case 5: {
                alert.setContentText("Please enter a Machine ID Number");
                break;
            }
            case 6: {
                alert.setContentText("Please enter a Company Name");
                break;
            }
            case 7: {
                alert.setContentText("Price must be greater than 0");
                break;
            }
            case 8: {
                alert.setContentText("Min cannot be higher than max");
                break;
            }
            case 9: {
                alert.setContentText("Machine ID must be a number");
                break;
            }
            case 10: {
                alert.setContentText("Enter an Inventory level");
                break;
            }
            case 11: {
                alert.setContentText("Enter a Price");
                break;
            }
            case 12: {
                alert.setContentText("Enter a Min value");
                break;
            }
            case 13: {
                alert.setContentText("Enter a Max value");
                break;
            }
            default: {
                alert.setContentText("Unknown Error");
                break;
            }
        }
        alert.showAndWait();
        
    }
    
    public static void errorProd(int code, TextField field){
        fieldError(field);
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error adding product");
        alert.setHeaderText("Cannot add product");
        switch (code){
            case 1: {
                alert.setContentText("Please enter a name");
                break;
            }
            case 2: {
                alert.setContentText("Inventory Level must be equal to or between Min and Max");
                break;
            }
            case 3: {
                alert.setContentText("Min cannot be negative");
                break;
            }
            case 4: {
                alert.setContentText("Max cannot be less than Min");
                break;
            }
            case 5: {
                alert.setContentText("Please enter a Machine ID Number");
                break;
            }
            case 6: {
                alert.setContentText("Please enter a Company Name");
                break;
            }
            case 7: {
                alert.setContentText("Associated parts list cannot be empty");
                break;
            }
            case 8: {
                alert.setContentText("Min cannot be higher than max");
                break;
            }
            case 9: {
                alert.setContentText("Machine ID must be a number");
                break;
            }
            case 10: {
                alert.setContentText("Enter an Inventory level");
                break;
            }
            case 11: {
                alert.setContentText("Enter a Price");
                break;
            }
            case 12: {
                alert.setContentText("Enter a Min value");
                break;
            }
            case 13: {
                alert.setContentText("Enter a Max value");
                break;
            }
            default: {
                alert.setContentText("Unknown Error");
                break;
            }
        }
        alert.showAndWait();
        
    }
    
        
    private static void fieldError(TextField field){
            if (field != null){
                field.setStyle("-fc-border-color: red");
            }
    }
}
