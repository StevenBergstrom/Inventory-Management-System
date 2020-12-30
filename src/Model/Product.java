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
public class Product {
    
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private String name;
    private int id, stock, min, max;
    private double price;

    public Product(int id, String name, double price, int stock, int min, int max) {
        setName(name);
        setId(id);
        setStock(stock);
        setMin(min);
        setMax(max);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }
    
    public boolean deleteAssociatedPart(Part selectedPart){
        this.associatedParts.remove(selectedPart);
        return true;
    }
    
    public ObservableList<Part> getAllAssociatedParts(){
        return this.associatedParts;
    }
}
