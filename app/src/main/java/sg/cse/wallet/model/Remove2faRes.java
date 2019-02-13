package sg.cse.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Remove2faRes extends BaseRes{
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @SerializedName("result")
    @Expose
    private String result;


}
