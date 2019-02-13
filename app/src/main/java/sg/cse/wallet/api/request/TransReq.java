package sg.cse.wallet.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransReq {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("coinAsset")
    @Expose
    private String coinAsset;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("code2FA")
    @Expose
    private String code2FA;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCoinAsset() {
        return coinAsset;
    }

    public void setCoinAsset(String coinAsset) {
        this.coinAsset = coinAsset;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCode2FA() {
        return code2FA;
    }

    public void setCode2FA(String code2FA) {
        this.code2FA = code2FA;
    }

}
