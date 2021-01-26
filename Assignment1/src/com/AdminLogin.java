package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame {
    private JPanel mainPanel;
    private JTextField AdminLoginTxt;
    private JButton LoginBtn;
    private JPasswordField AdminPasswordTxt;
    private Controller controller;

    public AdminLogin(String adminLogin, Controller controller){
        super(adminLogin);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.pack();

        this.controller = controller;

        LoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                login();
            }
        });
    }
    public void login(){
        String username = AdminLoginTxt.getText();
        String password = AdminPasswordTxt.getText();

        if(username.equals("admin") & password.equals("admin")){
            new AdminInterface("Admin", controller).setVisible(true);
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Incorrect Username or Password.");
        }
    }


}
