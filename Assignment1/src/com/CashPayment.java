package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CashPayment extends JFrame {
    private JPanel CashPanel;
    private JButton cardBtn;
    private JButton paymentCompleteBtn;
    private JLabel totalNeededLbl;
    private JLabel currentInsertedLbl;
    private JButton onePenceBtn;
    private JButton twentyPenceBtn;
    private JButton £5Btn;
    private JButton twoPenceBtn;
    private JButton fiftyPenceBtn;
    private JButton £10Btn;
    private JButton fivePenceBtn;
    private JButton £1Btn;
    private JButton £20Btn;
    private JButton tenPenceBtn;
    private JButton £50Btn;
    private JButton £2Btn;

    private float currentInserted = 0;

    private ArrayList<Item> basket;

    public CashPayment(String cashForm, ArrayList<Item> basket) {
        super(cashForm);
        this.setContentPane(CashPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        this.pack();
        this.basket = basket;
        float cost = costCalculation();
        totalNeededLbl.setText(Float.toString(cost));

        cardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new CardPayment("Checkout", basket).setVisible(true);
                dispose();
            }
        });
        onePenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 0.01;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        twoPenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 0.02;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        fivePenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 0.05;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        tenPenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 0.10;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        twentyPenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 0.20;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        fiftyPenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 0.50;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        £1Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 1.00;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        £2Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 2.00;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        £5Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 5.00;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        £10Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 10.00;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        £20Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 20.00;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });
        £50Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentInserted += 50.00;
                float roundedInserted = (float)Math.round(currentInserted * 100.0f) / 100.0f;
                currentInsertedLbl.setText(Float.toString(roundedInserted));
            }
        });

        paymentCompleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                float inserted = Float.parseFloat(currentInsertedLbl.getText());
                if (inserted >= cost) {
                    float change = inserted - cost;
                    paymentComplete(change);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Not enough money inserted, please insert the correct amount and try again.");
                }


            }
        });
    }
    public void paymentComplete(float change){
        new ReceiptDisplay("Receipt", change, basket).setVisible(true);
        dispose();
    }
    public float costCalculation(){
        float cost = 0;
        for (int i = 0; i < basket.size(); i++){
            cost += basket.get(i).getPrice();
        }
        return cost;
    }
}
