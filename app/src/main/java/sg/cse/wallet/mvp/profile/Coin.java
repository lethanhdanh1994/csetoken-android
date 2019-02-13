package sg.cse.wallet.mvp.profile;

public class Coin {
    public String name;
    public double amount;
    public String usd;

    public Coin(String name, double amount, String usd) {
        this.name = name;
        this.amount = amount;
        this.usd = usd;
    }
}
