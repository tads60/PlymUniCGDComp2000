package com;

public interface IModel {

    void add(IGui observer);
    void remove(IGui observer);
    void updateObservers();
    void updateItem(int itemIndex, Item item);
    Item getItem(int itemIndex);
    void saveData();
    void updateStock(int itemIndex, int newStock);
    void addItem(Item item);
    void deleteItem(int itemIndex);
}
