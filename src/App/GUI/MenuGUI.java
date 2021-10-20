package App.GUI;

import App.Database.Order;
import App.Tir.Config;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuGUI implements ActionListener {

    private JPanel mainPanel;
    private JTextArea orderTextArea;
    StringBuilder text = new StringBuilder();
    ArrayList<Order> database;

    public MenuGUI(ArrayList<Order> database) {
        this.database = database;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame dialog = new JFrame("Cereri");
        MenuGUI menuGUI = new MenuGUI(database);
        dialog.setContentPane(menuGUI.getMainPanel());
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setVisible(true);
        setDataVisualization();
        menuGUI.setOrderTextArea(text.toString());

    }

    private JPanel getMainPanel() {
        return mainPanel;
    }
    private void setDataVisualization(){
        text.delete(0, text.length());
        text.append("------ Lista de produse ------\n");
        text.append("Numar  |  Produse  -  Pret\n\n");
        for(Order order:database){
            ordersToLines(order);
        }
    }
    private void ordersToLines(Order order){
        text.append(order.getId() + " | " + order.getCustomer() + " | ");
        for (Config config:order.getTir().getConfigs()) text.append(config.getName() + "/ ");
        text.append(" - "+" ("+order.getPrice()+" - lei)");
        text.append("\n");
    }
    public void setOrderTextArea(String text){
        this.orderTextArea.setText(text);
    }

}
