package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class ErrorModel {

    @SerializedName("Error")
    private Error error;

    public Error getError() {
        return error;
    }

    public static class Error {

        @SerializedName("code")
        private String code;

        @SerializedName("message")
        private String message;

        @SerializedName("Description")
        private String description;

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getDescription() {
            return description;
        }
    }
}