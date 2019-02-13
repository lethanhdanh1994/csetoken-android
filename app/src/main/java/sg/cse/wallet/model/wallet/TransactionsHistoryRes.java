package sg.cse.wallet.model.wallet;

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
public class TransactionsHistoryRes {

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

        @SerializedName("docs")
        @Expose
        private List<Docs> docs = null;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("limit")
        @Expose
        private Integer limit;
        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("pages")
        @Expose
        private Integer pages;

        public List<Docs> getDocs() {
            return docs;
        }

        public void setDocs(List<Docs> docs) {
            this.docs = docs;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPages() {
            return pages;
        }

        public void setPages(Integer pages) {
            this.pages = pages;
        }
        public class Docs {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("updatedAt")
            @Expose
            private String updatedAt;
            @SerializedName("createdAt")
            @Expose
            private String createdAt;
            @SerializedName("fromAddress")
            @Expose
            private String fromAddress;
            @SerializedName("toAddress")
            @Expose
            private String toAddress;
            @SerializedName("coinAsset")
            @Expose
            private String coinAsset;
            @SerializedName("__v")
            @Expose
            private Integer v;
            @SerializedName("blockHash")
            @Expose
            private String blockHash;
            @SerializedName("txId")
            @Expose
            private String txId;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("amount")
            @Expose
            private Double amount;
            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("description")
            @Expose
            private String description;

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

            public String getFromAddress() {
                return fromAddress;
            }

            public void setFromAddress(String fromAddress) {
                this.fromAddress = fromAddress;
            }

            public String getToAddress() {
                return toAddress;
            }

            public void setToAddress(String toAddress) {
                this.toAddress = toAddress;
            }

            public String getCoinAsset() {
                return coinAsset;
            }

            public void setCoinAsset(String coinAsset) {
                this.coinAsset = coinAsset;
            }

            public Integer getV() {
                return v;
            }

            public void setV(Integer v) {
                this.v = v;
            }

            public String getBlockHash() {
                return blockHash;
            }

            public void setBlockHash(String blockHash) {
                this.blockHash = blockHash;
            }

            public String getTxId() {
                return txId;
            }

            public void setTxId(String txId) {
                this.txId = txId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Double getAmount() {
                return amount;
            }

            public void setAmount(Double amount) {
                this.amount = amount;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

        }
    }


}
