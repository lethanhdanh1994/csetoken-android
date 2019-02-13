package sg.cse.wallet.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvatarReq {
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @SerializedName("avatarUrl")
    @Expose
    private String avatarUrl;
}
