package com;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        IModel model = new Model();

        KioskDisplay program = new KioskDisplay("Kiosk");
        program.setVisible(true);

        model.add(program);

        Controller controller = new Controller(model, program);
        program.setController(controller);
        program.controller.updateView();
    }
}
