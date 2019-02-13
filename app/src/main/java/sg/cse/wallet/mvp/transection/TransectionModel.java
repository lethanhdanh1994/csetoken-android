package sg.cse.wallet.mvp.transection;

public class TransectionModel {
    private double amount;
    private String time;

    public TransectionModel(double amount, String time) {
        this.amount = amount;
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
