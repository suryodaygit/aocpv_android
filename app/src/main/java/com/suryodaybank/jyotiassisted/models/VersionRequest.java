
package com.suryodaybank.jyotiassisted.models;


import com.google.gson.annotations.Expose;


public class VersionRequest {

    @Expose
    private String os;
    @Expose
    private String version;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
