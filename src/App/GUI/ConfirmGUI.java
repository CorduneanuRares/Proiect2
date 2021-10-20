package App.GUI;

import App.Database.Database;
import App.Database.Order;
import App.Tir.Config;
import App.Tir.Tir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmGUI {
    private JPanel masterPanel;
    private JPanel mainPanel;
    private JTextPane optionsTextPane;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel confirmPanel;
    private JLabel priceLabel;
    StringBuilder text = new StringBuilder();
    private String customer;
    private Tir tir;
    private double price;

    public ConfirmGUI(Tir tir, String customer, double price){
        this.customer = customer;
        this.tir = tir;
        this.price = price;
        setOptions(tir);
        createCancelButtonListeners();
        createConfirmButtonListeners();
        priceLabel.setText("Pret total: LEI"+tir.getPrice());


    }
    private void setOptions(Tir tir){
        for(Config config:tir.getConfigs()){
            if(config.getName().startsWith("Produse gratis")){
                populate(config);
                text.append("----------------------\n");
            }
        }
        for(Config config:tir.getConfigs()){
            if(!config.getName().startsWith("Produse gratis")){
                populate(config);
            }
        }
        optionsTextPane.setText(text.toString());

    }
    private void populate(Config config){
        text.append(config.getName());

        text.append(" - "+config.getAmount()*config.getPrice() + " lei");
        text.append("\n");
    }
    private void createConfirmButtonListeners(){
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.addDatabase(new Order(customer, tir, price));
                JButton button = (JButton)e.getSource();
                Window window = SwingUtilities.windowForComponent( button );
                window.dispose();
            }
        });

    }
    private void createCancelButtonListeners(){
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                Window window = SwingUtilities.windowForComponent( button );
                window.dispose();
            }
        });
    }
    public JPanel getMasterPanel(){
        return masterPanel;
    }
}