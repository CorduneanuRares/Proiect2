package App.Tir;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Tir {
    private double price;
    private ArrayList<Config> configs = new ArrayList<>();
    public String getPrice() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        price= 0;
        deleteEmpty();
        for(Config config:configs){
            price+=config.getPrice()* config.getAmount();
        }
        return formatter.format(price);
    }

    public void updateConfigs(String name, String price, int amount, Boolean inTir){
        double newPrice = Double.parseDouble(price);
        for (Config i:configs){
            if(name.equals(i.getName())){
                i.setAmount(amount);
                i.setInTir(inTir);
                return;
            }
        }
        Config config = new Config(name, newPrice);
        config.setAmount(amount);
        config.setInTir(inTir);
        configs.add(config);
    }
    public ArrayList<Config> getConfigs(){
        return configs;
    }

    public void deleteEmpty(){
        configs.removeIf(config -> !config.isInTir());
    }
}

