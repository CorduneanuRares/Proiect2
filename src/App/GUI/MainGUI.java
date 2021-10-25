package App.GUI;

import App.Database.Database;
import App.Tir.Tir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

public class MainGUI{


    private JPanel mainPanel, confirmationPanel,nameLabel,breadSelection,breadCostLabel2,breadCostLabel3,breadCostLabel4,cheeseSelection;
    private JTextField customerText;
    private JButton confirm;
    private JLabel confirmationName;

    private JRadioButton ButonAlbastru, ButonRosu, NegruButon, AlbButon;

    private JComboBox AlbastruAmount, RosuAmount, NegruAmount, AlbAmount;
    private JLabel breadCostLabel1;
    private JCheckBox Volvo,Scania,Daf,Iveco,Renault;
    private JPanel othersPanel;
    private JCheckBox TuratieCuplu1, TuratieCupu2;
    private JPanel greensPanel;
    private JCheckBox Cuplu1,Cuplu2,Cuplu3,Cp1,Cp2,Cp3,Cp4,Cp5,Dm1,Dm2,Dm3,Dm4,Dm5,Dm6,Cp1Amount,Cp2Amount,Cp3Amount,Cp4Amount,Cp5Amount;
    private JPanel saucesPanel;
    private JPanel meatPanel;

    private JComboBox RenaultAmont,VolvoAmount,ScaniaAmount,DafAmount,RenaultAmount;
    private JLabel cheeseLabel1,cheeseLabel2,cheeseLabel3,cheeseLabel4,cheeseLabel5;

    private JComboBox Turatie1Amojnt;
    private JComboBox Turatie2Amount;
    private JLabel othersLabel1;
    private JLabel othersLabel2;
    private JLabel saucesLabel1;
    private JLabel saucesLabel2;
    private JLabel saucesLabel3;
    private JLabel saucesLabel4;
    private JLabel saucesLabel5;
    private JComboBox Dm6Amount;
    private JComboBox Dm5Amount;
    private JComboBox Dm4Amount;
    private JComboBox Dm3Amount;
    private JComboBox Dm2Amount;
    private JComboBox Dm1Amount;
    private JLabel meatLabel1;
    private JLabel meatLabel2;
    private JLabel meatLabel3;
    private JLabel meatLabel4;
    private JLabel meatLabel5;
    private JLabel meatLabel6;
    private JComboBox Cuplu1Amount;
    private JComboBox Cumplu2Amount;
    private JComboBox Cuplu3Amount;
    private JLabel greensLabel1;
    private JLabel greensLabel2;
    private JLabel greensLabel3;
    private JLabel nameError;
    private JLabel totalValueLabel;
    private JButton visualizeButton;
    private JMenu ordersMenus; private Tir tir = new Tir();
    //endregion ------
    JCheckBox[] checkBoxes = new JCheckBox[]{Volvo, Scania, Daf, Iveco, Renault, TuratieCuplu1,
            TuratieCupu2, Cuplu1, Cuplu2, Cuplu3, Cp1, Cp2, Cp3, Cp4,
            Cp5, Dm1, Dm2, Dm3, Dm4, Dm5, Dm6};
    JComboBox[] comboBoxes = new JComboBox[]{breadComboBox1, breadComboBox2, breadComboBox3, breadComboBox4, RenaultAmont,
            VolvoAmount, ScaniaAmount, DafAmount, cheeseComboBox4, othersComboBox2, othersComboBox1, Cp1Amount,
            Cp2Amount, Cp3Amount, Cp4Amount, Cp5Amount, Dm6Amount, Dm5Amount,
            Dm4Amount, Dm3Amount, Dm2Amount, Dm1Amount, Cuplu1Amount, Cumplu2Amount, Cuplu3Amount};
    JRadioButton[] radioButtons = new JRadioButton[]{breadButton1, breadButton2, breadButton3, breadButton4};

    String noText = customerText.getText();




    public MainGUI() {
        mainPanel.setBackground(Color.green);
        newTir();
        createCheckBoxListeners();
        createRadioButtonListeners();
        createMainButtonListeners();
        createComboBoxListeners();
        createOrdersButtonListeners();


    }

    private void createOrdersButtonListeners(){
        visualizeButton.addActionListener(new MenuGUI(Database.getDatabase()));
    }
    private void createMainButtonListeners(){
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(customerText.getText().equals(noText)){
                    JOptionPane.showMessageDialog(null, "Introduceti un nume pentru a efectua factura", "Greseala", JOptionPane.ERROR_MESSAGE);
                }
                else if(!(ButonAlbastru.isSelected() || ButonRosu.isSelected()|| NegruButon.isSelected() || AlbButon.isSelected())){
                    JOptionPane.showMessageDialog(null, "Selectati obligatoriu culoarea dorita", "Greseala", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JFrame display = new JFrame("Confirmare");
                    display.setContentPane(new ConfirmGUI(tir, customerText.getText(), Double.parseDouble(tir.getPrice().replace(",","."))).getMasterPanel());
                    display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    display.setResizable(false);
                    display.pack();
                    display.setVisible(true);

                }
                tir.deleteEmpty();
            }
        });
    }
    private void createRadioButtonListeners(){
        for(JRadioButton radioButton:radioButtons){
            for(JComboBox comboBox:comboBoxes){
                String[] actualField = radioButton.getName().split("Button");
                if (Arrays.equals(actualField, comboBox.getName().split("ComboBox"))){
                    radioButton.addItemListener(new ItemListener() {
                        JComboBox comboBox;
                        Tir tir;
                        JRadioButton radioButton;
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String name = radioButton.getText().split( " - RON ")[0];
                            String value = radioButton.getText().split( " - RON ")[1];
                            if(comboBox.isEnabled()){
                                comboBox.setEnabled(false);
                                tir.updateConfigs(name, value, Integer.parseInt(comboBox.getSelectedItem().toString()), false);
                                updatePrice();
                            }else{
                                comboBox.setEnabled(true);
                                tir.updateConfigs(name, value, Integer.parseInt(comboBox.getSelectedItem().toString()), true);
                                updatePrice();
                            }
                        }
                        private ItemListener setParams(JComboBox comboBox, Tir tir, JRadioButton radioButton) {
                            this.comboBox = comboBox;
                            this.tir = tir;
                            this.radioButton = radioButton;
                            return this;
                        }
                    }.setParams(comboBox, tir, radioButton));
                }
            }
        }
    }
    private void createCheckBoxListeners(){
        for(JCheckBox checkBox:checkBoxes){
            for(JComboBox comboBox:comboBoxes){
                String[] actualField = checkBox.getName().split("CheckBox");
                if (Arrays.equals(actualField, comboBox.getName().split("ComboBox"))){
                    checkBox.addActionListener(new ActionListener() {
                        Tir tir;
                        JComboBox comboBox;
                        JCheckBox checkBox;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = checkBox.getText().split( " - RON ")[0];
                            String value = checkBox.getText().split( " - RON ")[1];
                            if(comboBox.isEnabled()){
                                comboBox.setEnabled(false);
                                tir.updateConfigs(name, value, Integer.parseInt(comboBox.getSelectedItem().toString()), false);
                                updatePrice();
                            }else{
                                comboBox.setEnabled(true);
                                tir.updateConfigs(name, value, Integer.parseInt(comboBox.getSelectedItem().toString()), true);
                                updatePrice();
                            }
                        }
                        private ActionListener setParams(JComboBox comboBox, Tir tir, JCheckBox checkBox){
                            this.comboBox = comboBox;
                            this.tir = tir;
                            this.checkBox = checkBox;
                            return this;
                        }
                    }.setParams(comboBox, tir, checkBox));
                }
            }
        }
    }
    private void createComboBoxListeners(){
        for(JComboBox comboBox:comboBoxes){
            for(JCheckBox checkBox:checkBoxes){
                String[] actualCheckField = checkBox.getName().split("CheckBox");
                if (Arrays.equals(actualCheckField, comboBox.getName().split("ComboBox"))){
                    comboActionReceiver(comboBox, checkBox);
                }
            }
            for(JRadioButton radioButton:radioButtons){
                String[] actualCheckField = radioButton.getName().split("Button");
                if (Arrays.equals(actualCheckField, comboBox.getName().split("ComboBox"))){
                    comboActionReceiver(comboBox, radioButton);
                }
            }
        }
    }
    private void comboActionReceiver(JComboBox comboBox, JCheckBox checkBox){
        comboBox.addActionListener(new ActionListener() {
            Tir tir;
            JComboBox comboBox;
            JCheckBox checkBox;
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = checkBox.getText().split( " - RON ")[0];
                String value = checkBox.getText().split( " - RON ")[1];
                tir.updateConfigs(name, value, Integer.parseInt(comboBox.getSelectedItem().toString()), true);
                updatePrice();
            }
            private ActionListener setParams(JComboBox comboBox, Tir tir, JCheckBox checkBox){
                this.comboBox = comboBox;
                this.tir = tir;
                this.checkBox = checkBox;
                return this;
            }
        }.setParams(comboBox, tir, checkBox));}

    private void comboActionReceiver(JComboBox comboBox, JRadioButton radioButton){
        comboBox.addActionListener(new ActionListener() {
            Tir tir;
            JComboBox comboBox;
            JRadioButton radioButton;
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = radioButton.getText().split( " - RON ")[0];
                String value = radioButton.getText().split( " - RON ")[1];
                tir.updateConfigs(name, value, Integer.parseInt(comboBox.getSelectedItem().toString()), true);
                updatePrice();
            }
            private ActionListener setParams(JComboBox comboBox, Tir tir, JRadioButton radioButton){
                this.comboBox = comboBox;
                this.tir = tir;
                this.radioButton = radioButton;
                return this;
            }
        }.setParams(comboBox, tir, radioButton));
    }
    public void newTir(){

        this.tir = new Tir();
    }
    private void updatePrice(){

        totalValueLabel.setText("RON "+tir.getPrice());
    }
    public JPanel getMainPanel() {

        return mainPanel;
    }
}