package sg.cse.wallet.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Confirm2faReq {
    public String getCode2FA() {
        return code2FA;
    }

    public void setCode2FA(String code2FA) {
        this.code2FA = code2FA;
    }

    @SerializedName("code2FA")
    @Expose
    private String code2FA;
}
