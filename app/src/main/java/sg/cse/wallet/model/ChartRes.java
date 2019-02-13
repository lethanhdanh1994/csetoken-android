package sg.cse.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChartRes extends BaseRes{
    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public class Result {

        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("price")
        @Expose
        private Double price;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

    }
}
