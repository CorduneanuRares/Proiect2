package App;

import App.GUI.MainGUI;

import javax.swing.*;

public class Main {


    public static <runnable> void main(String[] args) {
        JFrame display = new JFrame("Dealer Truck");
        display.setContentPane(new MainGUI().getMainPanel());
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setResizable(false);
        display.pack();
        display.setVisible(true);

    }
}
