package com;

import com.AdminLogin;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;


public class AdminInterface extends JFrame implements IGui{
    public JPanel adminIntPanel;
    private JTable itemsTbl;
    private JTextField descriptionTxt;
    private JTextField typeTxt;
    private JTextField priceTxt;
    private JButton saveBtn;
    private JButton newItemBtn;
    private JButton deleteBtn;
    private JButton lowStockBtn;
    private JTextField quantityTxt;
    private Controller controller;

    public AdminInterface(String AdminForm, Controller controller)
    {
        super(AdminForm);
        this.setContentPane(adminIntPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        this.pack();
        this.controller = controller;
        controller.addObserver(this);
        controller.updateView();

        itemsTbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        itemsTbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (itemsTbl.getSelectedRow() < 0){

                }
                else
                    fillTextFields();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (itemsTbl.getSelectedRow() < 0){
                    JOptionPane.showMessageDialog(null, "Cannot save changes as no item selected. Please select an item to save changes to, or add new item.");
                }
                else{
                    saveDetails();
                }
            }
        });
        newItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                addNewItem();
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (itemsTbl.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Cannot delete item as no item selected. Please select an item and try again.");
                }
                else{
                    deleteItem();
                }
            }
        });
        lowStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                highlightLowStock();
            }
        });
    }
    public void update(DefaultTableModel model){
        itemsTbl.setModel(model);
    }
    public void fillTextFields(){
        Item item = controller.getItem(itemsTbl.getSelectedRow());
        descriptionTxt.setText(item.getDescription());
        typeTxt.setText(item.getType());
        priceTxt.setText(Float.toString(item.getPrice()));
        quantityTxt.setText(Integer.toString(item.getQuantity()));
    }
    public void saveDetails(){
        Item item = controller.getItem(itemsTbl.getSelectedRow());
        item.setDescription(descriptionTxt.getText());
        item.setType(typeTxt.getText());
        try{
            float newValue = Float.parseFloat(priceTxt.getText());
            item.setPrice(newValue);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Description and type have been updated, however there has been an error in the new price. Please check you have entered the correct new price.");
        }
        try{
            int newQuantity = Integer.parseInt(quantityTxt.getText());
            item.setQuantity(newQuantity);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Description and type have been updated, however there has been an error in the new quantity. Please check you have entered the correct new total quanity.");
        }
        controller.updateItem(itemsTbl.getSelectedRow(), item);
        controller.saveDate();
        controller.updateView();
    }
    public void addNewItem(){
        try {
            String description = descriptionTxt.getText();
            String type = typeTxt.getText();
            int quantity = Integer.parseInt(quantityTxt.getText());
            float price = Float.parseFloat(priceTxt.getText());
            Item item = new Item(description, type, quantity, price);
            controller.addItem(item);
            controller.saveDate();
            controller.updateView();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "There has been an error in creating your new item. Please check all details are correct and try again.");
        }
    }
    public void highlightLowStock(){
        for (int i = 0; i < itemsTbl.getRowCount(); i++){
            Item item = controller.getItem(i);
            if (item.getQuantity() < 5){
                itemsTbl.setRowSelectionInterval(i,i);
                break;
            }
        }
    }
    public void deleteItem(){
        controller.deleteItem(itemsTbl.getSelectedRow());
        controller.saveDate();
        controller.updateView();
    }

}