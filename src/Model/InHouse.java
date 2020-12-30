/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;




/**
 *
 * @author bergs
 */
public class InHouse extends Part{
    
    private int machineID;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        setId(id);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
        setMachineID(machineID);
    
    }
        
    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
