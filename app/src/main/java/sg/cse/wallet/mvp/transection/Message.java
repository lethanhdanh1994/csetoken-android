package sg.cse.wallet.mvp.transection;

public class Message {
    private  String address;

    public String getCoin() {
        return coin;
    }


    private String coin,total;

    public Message(String message,String total,String coin) {
        this.coin=coin;
        this.total=total;
        this.address = message;
    }

    public String getTotal() {
        return total;
    }


    public String getMessage() {
        return address;
    }


}
