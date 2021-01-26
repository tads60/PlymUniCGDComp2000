package com;

import com.AdminInterface;
import com.CardPayment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class KioskDisplay extends JFrame implements IGui{

    private JPanel KioskPanel;
    private JButton adminBtn;
    private JButton cardCheckoutBtn;
    private JButton cashPaymentBtn;
    private JTextArea scannedItemsTxt;
    private JLabel currentTotalLbl;
    private JScrollPane itemListPnl;
    private JTable itemsTable;
    private JButton scanBtn;
    private JScrollPane scannedItemsPnl;
    public Controller controller;

    private ArrayList<Item> basket = new ArrayList<>();

    private float currentPrice = 0;

    public KioskDisplay(String kioskForm){
        super(kioskForm);
        this.setContentPane(KioskPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.pack();


        adminBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                openAdminInterface();
            }
        });

        cardCheckoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                openCardPayment();
            }
        });

        cashPaymentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                openCashPayment();
            }
        });

        scanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                scanItem();
            }
        });

    }
    public void scanItem(){
        int[] indexes = itemsTable.getSelectedRows();

        for (int i = 0; i < indexes.length; i++){
            Item item = controller.getItem(indexes[i]);
            if (item.getQuantity() == 0){
                JOptionPane.showMessageDialog(null, "No Stock remaining of " + item.getDescription());
            }
            else{
                scannedItemsTxt.append(item.getDescriptionAndPrice() + "\n");
                basket.add(item);
                currentPrice += item.getPrice();
                float roundedFloat = (float)Math.round(currentPrice * 100.0f) / 100.0f;
                currentTotalLbl.setText("£" + Float.toString(roundedFloat));

                controller.updateStockTotal(indexes[i], item.getQuantity() - 1);
                controller.saveDate();
                controller.updateView();
            }
        }
    }


    public void openAdminInterface(){
        new AdminLogin("AdminLogin", controller).setVisible(true);
        controller.removeObserver(this);
        dispose();
    }
    public void openCashPayment(){
        new CashPayment("Checkout", basket).setVisible(true);
        dispose();
    }
    public void openCardPayment(){
        new CardPayment("Checkout", basket).setVisible(true);
        dispose();
    }
    public void setController(Controller controller){
        this.controller = controller;
    }
    public void update(DefaultTableModel model){
        itemsTable.setModel(model);
    }

}
