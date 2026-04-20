package com.example.Backend.DTO;

public class ItemDTO {
    // Member variables
    String itemType;
    int quantity;
    // Member functions

    // zero args constructor
    public ItemDTO() {
        
    }

    // Parameterised constructor
    public ItemDTO(String itemType, int quantity) {
        this.itemType = itemType;
        this.quantity = quantity;
    }

    // Getters and Setters 

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
