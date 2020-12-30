/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author bergs
 */
public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;
    
    
    public Inventory(){
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }
    
    public void addPart(Part newPart){
        if (newPart != null){
            allParts.add(newPart);
        }
    }
    
    public void addProduct(Product newProduct){
        if (newProduct != null){
            allProducts.add(newProduct);
        }
    }
    
    public Part lookupPart(int partId){
        if (!allParts.isEmpty()){    
            for (int i = 0; i < allParts.size(); i++){
                if (allParts.get(i).getId() == partId){
                        return allParts.get(i);
                }
            }
        }
        return null;
    }
    
    public Product lookupProduct(int productId){
        if (!allProducts.isEmpty()){    
            for (int i = 0; i < allProducts.size(); i++){
                if (allProducts.get(i).getId() == productId){
                        return allProducts.get(i);
                }
            }
        }
        return null;
    }
    
    public ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> multipleParts = FXCollections.observableArrayList();
        if (!allParts.isEmpty()){    
            for (int i = 0; i < allParts.size(); i++){
                if (allParts.get(i).getName() == partName){
                      multipleParts.add(allParts.get(i));
                }
            }
            return multipleParts;
        }
        return null;
    }
    
    public ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> multipleProducts = FXCollections.observableArrayList();
        if (!allProducts.isEmpty()){    
            for (int i = 0; i < allProducts.size(); i++){
                if (allProducts.get(i).getName() == productName){
                      multipleProducts.add(allProducts.get(i));
                }
            }
            return multipleProducts;
        }
        return null;
    }
    
    public void updatePart(int index, Part newPart){
        allParts.remove(index);
        allParts.add(index, newPart);
    }
    
    public void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }
   
    public boolean deletePart(Part selectedPart){
        for (int i = 0; i < allParts.size(); i++){
            if (allParts.get(i) == selectedPart){
                allParts.remove(i);
                return true;
            }
        }
        return false;
    }
   
    public boolean deleteProduct(Product selectedProduct){
        for (int i = 0; i < allProducts.size(); i++){
            if (allProducts.get(i) == selectedProduct){
                allProducts.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }
    
}
