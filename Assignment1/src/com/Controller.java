package com;

public class Controller {
    private IModel model;
    private IGui view;

    public Controller(IModel model, IGui view){
        this.model = model;
        this.view = view;
    }
    public void updateView(){
        model.updateObservers();
    }
    public Item getItem(int itemIndex){
        Item item = model.getItem(itemIndex);
        return item;
    }
    public void updateStockTotal(int itemIndex, int newQuantity){
        model.updateStock(itemIndex, newQuantity);
    }
    public void updateItem(int itemIndex, Item item){
        model.updateItem(itemIndex, item);
    }
    public void saveDate(){
        model.saveData();
    }
    public void removeObserver(IGui observer){
        model.remove(observer);
    }
    public void addObserver(IGui observer){
        model.add(observer);
    }
    public void addItem(Item item){
        model.addItem(item);
    }
    public void deleteItem(int itemIndex){
        model.deleteItem(itemIndex);
    }
}
