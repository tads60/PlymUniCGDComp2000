package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CardPayment extends JFrame {
    private JPanel CardPanel;
    private JButton paymentBtn;
    private JButton cashBtn;

    private ArrayList<Item> basket;

    public CardPayment(String CardPayment, ArrayList<Item> basket){
        super(CardPayment);
        this.setContentPane(CardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.pack();
        this.basket = basket;



        cashBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new CashPayment("Checkout", basket).setVisible(true);
                dispose();
            }
        });

        paymentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            paymentComplete();
            }
        });
    }
    private void paymentComplete(){
        new ReceiptDisplay("Reciept", 0, basket).setVisible(true);
        dispose();
    }

}
