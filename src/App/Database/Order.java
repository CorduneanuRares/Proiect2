package App.Database;

import App.Tir.Tir;

public class Order extends ImplementId {
    private int id;
    private String customer;
    private Tir tir;
    private double price;
    public Order(String customer, Tir tir, double price){
        id = super.newId();
        this.customer = customer;
        this.tir = tir;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public String getCustomer() {
        return customer;
    }

    public Tir getTir() {
        return tir;
    }

    public double getPrice() {
        return price;
    }
}
