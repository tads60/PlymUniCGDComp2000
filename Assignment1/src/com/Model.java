package com;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;


public class Model implements IModel {

    String[] columnNames;
    Vector<String> vectorColumnNames = new Vector<String>();
    Vector<String> vectorStrings = new Vector<String>();
    Vector<Vector<String>> vectorVectorStringsData = new Vector<Vector<String>>();

    DefaultTableModel model = new DefaultTableModel();

    private final ArrayList<Item> itemList = new ArrayList<>();

    private final ArrayList<IGui> observers = new ArrayList<>();

    public Model(){
        updateObservers();
    }

    public void add(IGui observer){
        observers.add(observer);
    }
    public void remove(IGui observer){
        observers.remove(observer);
    }
    public void updateItem(int itemIndex, Item item){
        itemList.set(itemIndex, item);
    }
    public void updateObservers(){
        readDataIn();

        for(IGui observer : observers){
            observer.update(model);
            System.out.println("Updating observers with new data");
        }
    }
    private String currentPath() throws URISyntaxException{
        File currentPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());

        if(currentPath.getPath().substring(currentPath.getPath().lastIndexOf("/") + 1).contains(".jar"))
        {
            return currentPath.getParentFile().getPath();
        }
        return currentPath.getPath();
    }
    private void readDataIn(){
        itemList.clear();
        model.setRowCount(0);
        try {
            File file = new File(currentPath() + "/Items.csv");

            Scanner scanner = new Scanner(file);

            String Headers = scanner.nextLine().trim();
            columnNames = Headers.split(",");
            vectorColumnNames = new Vector<String>();
            for (int i = 0; i < columnNames.length; i++) {
                vectorColumnNames.add(columnNames[i]);
            }

            while (scanner.hasNextLine()) {
                String[] itemRow = scanner.nextLine().trim().split(",");

                vectorStrings = new Vector<String>();
                for (int i = 0; i < itemRow.length; i++) {
                    vectorStrings.add(itemRow[i]);
                }
                vectorVectorStringsData.add(vectorStrings);

                Item item = new Item(itemRow[0], itemRow[1], Integer.parseInt(itemRow[2]), Float.parseFloat(itemRow[3]));

                itemList.add(item);
            }
            scanner.close();
        }
        catch (IOException| URISyntaxException e){
            System.out.println("Error: " + e.getMessage());
        }

        model.setDataVector(vectorVectorStringsData, vectorColumnNames);
        System.out.println("File loaded");

    }

    public void saveData(){
        try {
            FileWriter writer = new FileWriter(currentPath() + "/Items.csv");

            String headers = "Description, Type, Quantity, Price\n";
            writer.write(headers);
            for (int i = 0; i < itemList.size(); i++){
                String itemRow = "";
                if (i>0){
                    itemRow += "\n";
                }
                itemRow += itemList.get(i).getDescription() + ",";
                itemRow += itemList.get(i).getType() + ",";
                itemRow += itemList.get(i).getQuantity() + ",";
                itemRow += itemList.get(i).getPrice();

                writer.write(itemRow);
            }
            writer.close();
        }
        catch (IOException | URISyntaxException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void updateStock(int itemIndex, int newQuantity){
        itemList.get(itemIndex).setQuantity(newQuantity);
    }
    public Item getItem(int itemIndex){
        return itemList.get(itemIndex);
    }
    public void addItem(Item item){
        itemList.add(item);
    }
    public void deleteItem(int itemIndex){
        itemList.remove(itemIndex);
    }



}
