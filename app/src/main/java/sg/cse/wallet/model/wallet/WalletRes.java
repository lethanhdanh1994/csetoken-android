package sg.cse.wallet.model.wallet;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class WalletRes {

    /**
     * success : true
     * message :
     * result : [{"_id":"5b992e54a844fb3774c0bb15","updatedAt":"2018-09-12T15:49:36.774Z","createdAt":"2018-09-12T15:18:44.556Z","userId":"5afff987e8ae9a6ea05aaaed","coinAsset":"CSE","address":"CSE5B992E54A844FB3774C0BB15","__v":0,"isDeleted":false,"isActive":true,"sentAmount":0,"receivedAmount":1,"availableAmount":10000,"rootWalletAddress":null},{"_id":"5b99322f248925774251efd1","updatedAt":"2018-09-12T15:45:09.148Z","createdAt":"2018-09-12T15:18:44.556Z","userId":"5afff987e8ae9a6ea05aaaed","coinAsset":"BTC","address":"CSE5B992E54A844FB3774C0BB16","__v":0,"isDeleted":false,"isActive":true,"sentAmount":0,"receivedAmount":0,"availableAmount":1.2201,"rootWalletAddress":null}]
     */

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    @SerializedName("result")
    @Expose
    private Result result;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
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

        @SerializedName("totalEstimatedUSDT")
        @Expose
        private Double totalEstimatedUSDT;
        @SerializedName("docs")
        @Expose
        private List<Doc> docs = null;

        public Double getTotalEstimatedUSDT() {
            return totalEstimatedUSDT;
        }

        public void setTotalEstimatedUSDT(Double totalEstimatedUSDT) {
            this.totalEstimatedUSDT = totalEstimatedUSDT;
        }

        public List<Doc> getDocs() {
            return docs;
        }

        public void setDocs(List<Doc> docs) {
            this.docs = docs;
        }

    }
    public class Doc {
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("coinAsset")
        @Expose
        private String coinAsset;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("isDeleted")
        @Expose
        private Boolean isDeleted;
        @SerializedName("isActive")
        @Expose
        private Boolean isActive;
        @SerializedName("sentAmount")
        @Expose
        private Integer sentAmount;
        @SerializedName("receivedAmount")
        @Expose
        private Double receivedAmount;
        @SerializedName("availableAmount")
        @Expose
        private Double availableAmount;
        @SerializedName("rootWalletAddress")
        @Expose
        private Object rootWalletAddress;
        @SerializedName("estimatedUSDT")
        @Expose
        private Double estimatedUSDT;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCoinAsset() {
            return coinAsset;
        }

        public void setCoinAsset(String coinAsset) {
            this.coinAsset = coinAsset;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
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

        public Integer getSentAmount() {
            return sentAmount;
        }

        public void setSentAmount(Integer sentAmount) {
            this.sentAmount = sentAmount;
        }

        public Double getReceivedAmount() {
            return receivedAmount;
        }

        public void setReceivedAmount(Double receivedAmount) {
            this.receivedAmount = receivedAmount;
        }

        public Double getAvailableAmount() {
            return availableAmount;
        }

        public void setAvailableAmount(Double availableAmount) {
            this.availableAmount = availableAmount;
        }

        public Object getRootWalletAddress() {
            return rootWalletAddress;
        }

        public void setRootWalletAddress(Object rootWalletAddress) {
            this.rootWalletAddress = rootWalletAddress;
        }

        public Double getEstimatedUSDT() {
            return estimatedUSDT;
        }

        public void setEstimatedUSDT(Double estimatedUSDT) {
            this.estimatedUSDT = estimatedUSDT;
        }

    }
}
