package sg.cse.wallet.model.signin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoRes {

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

        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("systemconfigs")
        @Expose
        private Systemconfigs systemconfigs;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Systemconfigs getSystemconfigs() {
            return systemconfigs;
        }

        public void setSystemconfigs(Systemconfigs systemconfigs) {
            this.systemconfigs = systemconfigs;
        }
        public class Systemconfigs {

            @SerializedName("MIN_AMOUNT_WITHDRAW")
            @Expose
            private String mINAMOUNTWITHDRAW;
            @SerializedName("MIN_AMOUNT_INVEST")
            @Expose
            private String mINAMOUNTINVEST;
            @SerializedName("CSE_USD_RATE")
            @Expose
            private String cSEUSDRATE;
            @SerializedName("MIN_AMOUNT_TRANSFER")
            @Expose
            private String mINAMOUNTTRANSFER;
            @SerializedName("TIME_REPEAT_HANDLE_TRANSFER_INTERNAL")
            @Expose
            private String tIMEREPEATHANDLETRANSFERINTERNAL;
            @SerializedName("MAX_AMOUNT_INVEST")
            @Expose
            private String mAXAMOUNTINVEST;
            @SerializedName("MAX_AMOUNT_TRANSFER")
            @Expose
            private String mAXAMOUNTTRANSFER;

            public String getMINAMOUNTWITHDRAW() {
                return mINAMOUNTWITHDRAW;
            }

            public void setMINAMOUNTWITHDRAW(String mINAMOUNTWITHDRAW) {
                this.mINAMOUNTWITHDRAW = mINAMOUNTWITHDRAW;
            }

            public String getMINAMOUNTINVEST() {
                return mINAMOUNTINVEST;
            }

            public void setMINAMOUNTINVEST(String mINAMOUNTINVEST) {
                this.mINAMOUNTINVEST = mINAMOUNTINVEST;
            }

            public String getCSEUSDRATE() {
                return cSEUSDRATE;
            }

            public void setCSEUSDRATE(String cSEUSDRATE) {
                this.cSEUSDRATE = cSEUSDRATE;
            }

            public String getMINAMOUNTTRANSFER() {
                return mINAMOUNTTRANSFER;
            }

            public void setMINAMOUNTTRANSFER(String mINAMOUNTTRANSFER) {
                this.mINAMOUNTTRANSFER = mINAMOUNTTRANSFER;
            }

            public String getTIMEREPEATHANDLETRANSFERINTERNAL() {
                return tIMEREPEATHANDLETRANSFERINTERNAL;
            }

            public void setTIMEREPEATHANDLETRANSFERINTERNAL(String tIMEREPEATHANDLETRANSFERINTERNAL) {
                this.tIMEREPEATHANDLETRANSFERINTERNAL = tIMEREPEATHANDLETRANSFERINTERNAL;
            }

            public String getMAXAMOUNTINVEST() {
                return mAXAMOUNTINVEST;
            }

            public void setMAXAMOUNTINVEST(String mAXAMOUNTINVEST) {
                this.mAXAMOUNTINVEST = mAXAMOUNTINVEST;
            }

            public String getMAXAMOUNTTRANSFER() {
                return mAXAMOUNTTRANSFER;
            }

            public void setMAXAMOUNTTRANSFER(String mAXAMOUNTTRANSFER) {
                this.mAXAMOUNTTRANSFER = mAXAMOUNTTRANSFER;
            }

        }

        public class User {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("updatedAt")
            @Expose
            private String updatedAt;
            @SerializedName("createdAt")
            @Expose
            private String createdAt;
            @SerializedName("username")
            @Expose
            private String username;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("sponsorCode")
            @Expose
            private Object sponsorCode;
            @SerializedName("__v")
            @Expose
            private Integer v;
            @SerializedName("birthDay")
            @Expose
            private String birthDay;
            @SerializedName("lastLoginIP")
            @Expose
            private String lastLoginIP;
            @SerializedName("level")
            @Expose
            private String level;


            @SerializedName("isSetInBinary")
            @Expose
            private Boolean isSetInBinary;
            @SerializedName("phoneNumber")
            @Expose
            private String phoneNumber;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("codeVerify")
            @Expose
            private String codeVerify;
            @SerializedName("isEmailVerified")
            @Expose
            private Boolean isEmailVerified;
            @SerializedName("isAdmin")
            @Expose
            private Boolean isAdmin;
            @SerializedName("avatarUrl")
            @Expose
            private String avatarUrl;
            @SerializedName("address")
            @Expose
            private String address;
            @SerializedName("fullName")
            @Expose
            private String fullName;
            @SerializedName("isHave2Fa")
            @Expose
            private Boolean isHave2Fa;

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

            public Object getSponsorCode() {
                return sponsorCode;
            }

            public void setSponsorCode(Object sponsorCode) {
                this.sponsorCode = sponsorCode;
            }

            public Integer getV() {
                return v;
            }

            public void setV(Integer v) {
                this.v = v;
            }

            public String getBirthDay() {
                return birthDay;
            }

            public void setBirthDay(String birthDay) {
                this.birthDay = birthDay;
            }

            public String getLastLoginIP() {
                return lastLoginIP;
            }

            public void setLastLoginIP(String lastLoginIP) {
                this.lastLoginIP = lastLoginIP;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public Boolean getIsSetInBinary() {
                return isSetInBinary;
            }

            public void setIsSetInBinary(Boolean isSetInBinary) {
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

            public Boolean getIsEmailVerified() {
                return isEmailVerified;
            }

            public void setIsEmailVerified(Boolean isEmailVerified) {
                this.isEmailVerified = isEmailVerified;
            }

            public Boolean getIsAdmin() {
                return isAdmin;
            }

            public void setIsAdmin(Boolean isAdmin) {
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

            public Boolean getIsHave2Fa() {
                return isHave2Fa;
            }

            public void setIsHave2Fa(Boolean isHave2Fa) {
                this.isHave2Fa = isHave2Fa;
            }
        }
    }
}