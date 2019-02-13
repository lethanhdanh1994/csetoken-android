package sg.cse.wallet.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveCodeReq {
    @SerializedName("email")
    @Expose
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodeVerify() {
        return codeVerify;
    }

    public void setCodeVerify(String codeVerify) {
        this.codeVerify = codeVerify;
    }

    @SerializedName("codeVerify")
    @Expose
    private String codeVerify;
}
