
package com.suryodaybank.jyotiassisted.models;
import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("UID1")
    private String uID1;

    @SerializedName("name")
    private String name;

    @SerializedName("mobile")
    private String mobile;

    public String getuID1() {
        return uID1;
    }

    public void setuID1(String uID1) {
        this.uID1 = uID1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public String getbRCD() {
        return bRCD;
    }

    public void setbRCD(String bRCD) {
        this.bRCD = bRCD;
    }

    @SerializedName("userrole")
    private String userrole;

    @SerializedName("BRCD")
    private String bRCD;


}
