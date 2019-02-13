package sg.cse.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveCodeRes extends BaseRes{
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @SerializedName("result")
    @Expose
    private Result result;


    public class Result {


    }
}
