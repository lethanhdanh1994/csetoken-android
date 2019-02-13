package sg.cse.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceAndFreeRes extends BaseRes {

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @SerializedName("result")
    @Expose
    private Result  result;

    public class Result {

        @SerializedName("fee")
        @Expose
        private Double fee;

        public Double getFee() {
            return fee;
        }

        public void setFee(Double fee) {
            this.fee = fee;
        }

        public Double getPriceUSD() {
            return priceUSD;
        }

        public void setPriceUSD(Double priceUSD) {
            this.priceUSD = priceUSD;
        }

        @SerializedName("priceUSD")
        @Expose
        private Double priceUSD;



    }
}
