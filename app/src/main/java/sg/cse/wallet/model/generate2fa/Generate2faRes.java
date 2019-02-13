package sg.cse.wallet.model.generate2fa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Generate2faRes {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private Result result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("secretKey")
        @Expose
        private String secretKey;
        @SerializedName("qr")
        @Expose
        private String qr;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("isDeleted")
        @Expose
        private Boolean isDeleted;
        @SerializedName("isActive")
        @Expose
        private Boolean isActive;
        @SerializedName("isConfirm")
        @Expose
        private Boolean isConfirm;

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getQr() {
            return qr;
        }

        public void setQr(String qr) {
            this.qr = qr;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Boolean getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Boolean getIsConfirm() {
            return isConfirm;
        }

        public void setIsConfirm(Boolean isConfirm) {
            this.isConfirm = isConfirm;
        }

    }
}
