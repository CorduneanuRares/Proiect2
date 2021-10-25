package App.Tir;

public class Config implements CConfig{
    private String name;
    private double price;
    private int amount;
    private Boolean inTir;

    public Config(String name, double price){
        setName(name);
        setPrice(price);
    }
    @Override
    public void setInTir(Boolean inTir) {
        this.inTir = inTir;
    }
    @Override
    public Boolean isInTir() {
        return inTir;
    }
    private void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
    private void setPrice(double price) {
        this.price = price;
    }
    @Override
    public double getPrice() {
        return price;
    }
    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
    @Override
    public int getAmount() {
        return amount;
    }
}
