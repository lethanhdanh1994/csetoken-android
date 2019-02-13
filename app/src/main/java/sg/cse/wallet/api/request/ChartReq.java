package sg.cse.wallet.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChartReq {

    @SerializedName("coinAsset")
    @Expose
    private String coinAsset;
    @SerializedName("time")
    @Expose
    private String time;

    public String getCoinAsset() {
        return coinAsset;
    }

    public void setCoinAsset(String coinAsset) {
        this.coinAsset = coinAsset;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

}

