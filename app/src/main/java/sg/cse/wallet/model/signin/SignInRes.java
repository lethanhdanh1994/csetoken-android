package sg.cse.wallet.model.signin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class SignInRes {

    /**
     * success : true
     * message :
     * result : {"token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1Yjg4MTgwNTQ0NmJhZTIzYzQ5YjliMDgiLCJ1c2VybmFtZSI6ImFjY291bnQwMyIsImlhdCI6MTUzNjEzOTc1MSwiZXhwIjoxNTM2MzEyNTUxLCJhdWQiOiJmYi5jb20vZ2lhYmFvLmRldiIsImlzcyI6InBoYW1sZWdpYWJhbzEyMTBAZ21haWwuY29tIn0.pjfA5k-36lrMJcXHw1NutVcNIH4CvqgbG51-AhMVfLE9couWcRzqAvsh0FurssNxFDAeBdIxi7yc2TdFh9UFGI3rxyd8RAXII2RCOqMCKwy4TBQzBZf8iCJjZ5c9oVpvkKgKP7GiHsWfzOOqvcN5JXh_xhOVrlGoWrf81L8NdvI"}
     */

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
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

    public static class Result {
        /**
         * token : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1Yjg4MTgwNTQ0NmJhZTIzYzQ5YjliMDgiLCJ1c2VybmFtZSI6ImFjY291bnQwMyIsImlhdCI6MTUzNjEzOTc1MSwiZXhwIjoxNTM2MzEyNTUxLCJhdWQiOiJmYi5jb20vZ2lhYmFvLmRldiIsImlzcyI6InBoYW1sZWdpYWJhbzEyMTBAZ21haWwuY29tIn0.pjfA5k-36lrMJcXHw1NutVcNIH4CvqgbG51-AhMVfLE9couWcRzqAvsh0FurssNxFDAeBdIxi7yc2TdFh9UFGI3rxyd8RAXII2RCOqMCKwy4TBQzBZf8iCJjZ5c9oVpvkKgKP7GiHsWfzOOqvcN5JXh_xhOVrlGoWrf81L8NdvI
         */

        @SerializedName("token")
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
