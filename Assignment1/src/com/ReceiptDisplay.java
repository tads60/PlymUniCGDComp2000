package com;

import com.KioskDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReceiptDisplay extends JFrame{

    private JPanel ReceiptPanel;
    private JButton closeBtn;
    private JLabel dateLbl;
    private JLabel priceLbl;
    private JLabel changeLbl;
    private JTextArea itemListTxt;

    private ArrayList<Item> basket;

    public ReceiptDisplay(String receiptForm, float change, ArrayList<Item> basket){
        super(receiptForm);
        this.setContentPane(ReceiptPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.pack();
        this.basket = basket;

        float finalPrice = 0;
        for (int i = 0; i < basket.size(); i++){
            finalPrice += basket.get(i).getPrice();
        }

        float price = (float)Math.round(finalPrice * 100.0f) / 100.0f;
        priceLbl.setText("£" + price);
        float finalChange = (float)Math.round(change * 100.0f) / 100.0f;
        changeLbl.setText("£" + finalChange);

        Date date = Calendar.getInstance().getTime();
        dateLbl.setText(date.toString());

        SwingWorkerLoader();



        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                IModel model = new Model();

                KioskDisplay program = new KioskDisplay("Kiosk");
                program.setVisible(true);

                model.add(program);

                Controller controller = new Controller(model, program);
                program.setController(controller);
                program.controller.updateView();
                dispose();
            }
        });







    }
    void SwingWorkerLoader(){
        itemListTxt.setText("Loading, please wait");
        new SwingWorker<Object, Object>(){
            @Override
            protected Object doInBackground() throws Exception{
                System.out.println("SwingWorkerThread: " + Thread.currentThread().getName());
                itemListTxt.setText(getReceipt());
                return null;
            }
        }.execute();
    }
    private String getReceipt(){
        String receipt = "";
        for (int i = 0; i < basket.size(); i++){
            receipt += basket.get(i).getDescriptionAndPrice() + "\n";

        }
        return receipt;
    }
}
