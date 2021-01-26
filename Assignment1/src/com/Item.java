package com;

import java.util.Set;

public class Item {
    private String description;
    private String type;
    private int quantity;
    private float price;
    public Item(String description, String type, int quantity, float price){
        this.description = description;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }
    public String getDescription(){
        return description;
    }
    public String getType(){
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
    public float getPrice(){
        return price;
    }

    public void setDescription(String setDescription){
        description = setDescription;
    }
    public void setType(String setType){
        type = setType;
    }
    public void setQuantity(int setQuantity){
        quantity = setQuantity;
    }
    public void setPrice(float setPrice){
        price = setPrice;
    }

    public String getDescriptionAndPrice(){
        String output = "";
        output = description + " £" + Float.toString(price);
        return output;
    }

}
