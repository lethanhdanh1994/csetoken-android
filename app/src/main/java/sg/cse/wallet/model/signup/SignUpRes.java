package sg.cse.wallet.model.signup;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class SignUpRes {

    /**
     * success : true
     * message :
     * result : {"__v":0,"updatedAt":"2018-09-06T06:24:04.993Z","createdAt":"2018-09-06T06:24:04.993Z","username":"thongphm1","email":"thongph1@gmail.com","password":"$2b$10$RkQVAConB5gh0Y2HBrpsTOGZ8JgTDA3nvupHCyDDc0htnK9fiWjd.","sponsorCode":null,"createdAtIP":"::ffff:14.161.23.252","_id":"5b90c804ec9723189c4b7fb6","level":"NORMAL","isSetInBinary":false,"phoneNumber":"","status":"ACTIVE","codeVerify":"2e3e4535baddce355b583014825c99b1d9cb63910e9ab9d86ae0093e5a351254","isEmailVerified":false,"isAdmin":false,"avatarUrl":"","address":"","fullName":""}
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
         * __v : 0
         * updatedAt : 2018-09-06T06:24:04.993Z
         * createdAt : 2018-09-06T06:24:04.993Z
         * username : thongphm1
         * email : thongph1@gmail.com
         * password : $2b$10$RkQVAConB5gh0Y2HBrpsTOGZ8JgTDA3nvupHCyDDc0htnK9fiWjd.
         * sponsorCode : null
         * createdAtIP : ::ffff:14.161.23.252
         * _id : 5b90c804ec9723189c4b7fb6
         * level : NORMAL
         * isSetInBinary : false
         * phoneNumber :
         * status : ACTIVE
         * codeVerify : 2e3e4535baddce355b583014825c99b1d9cb63910e9ab9d86ae0093e5a351254
         * isEmailVerified : false
         * isAdmin : false
         * avatarUrl :
         * address :
         * fullName :
         */

        @SerializedName("__v")
        private int v;
        @SerializedName("updatedAt")
        private String updatedAt;
        @SerializedName("createdAt")
        private String createdAt;
        @SerializedName("username")
        private String username;
        @SerializedName("email")
        private String email;
        @SerializedName("password")
        private String password;
        @SerializedName("sponsorCode")
        private Object sponsorCode;
        @SerializedName("createdAtIP")
        private String createdAtIP;
        @SerializedName("_id")
        private String id;
        @SerializedName("level")
        private String level;
        @SerializedName("isSetInBinary")
        private boolean isSetInBinary;
        @SerializedName("phoneNumber")
        private String phoneNumber;
        @SerializedName("status")
        private String status;
        @SerializedName("codeVerify")
        private String codeVerify;
        @SerializedName("isEmailVerified")
        private boolean isEmailVerified;
        @SerializedName("isAdmin")
        private boolean isAdmin;
        @SerializedName("avatarUrl")
        private String avatarUrl;
        @SerializedName("address")
        private String address;
        @SerializedName("fullName")
        private String fullName;

        public int getV() {
            return v;
        }

        public void setV(int v) {
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getSponsorCode() {
            return sponsorCode;
        }

        public void setSponsorCode(Object sponsorCode) {
            this.sponsorCode = sponsorCode;
        }

        public String getCreatedAtIP() {
            return createdAtIP;
        }

        public void setCreatedAtIP(String createdAtIP) {
            this.createdAtIP = createdAtIP;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public boolean isIsSetInBinary() {
            return isSetInBinary;
        }

        public void setIsSetInBinary(boolean isSetInBinary) {
            this.isSetInBinary = isSetInBinary;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCodeVerify() {
            return codeVerify;
        }

        public void setCodeVerify(String codeVerify) {
            this.codeVerify = codeVerify;
        }

        public boolean isIsEmailVerified() {
            return isEmailVerified;
        }

        public void setIsEmailVerified(boolean isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
        }

        public boolean isIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }
}
