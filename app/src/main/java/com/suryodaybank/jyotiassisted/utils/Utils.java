package com.suryodaybank.jyotiassisted.utils;

import com.google.gson.Gson;
import com.suryodaybank.jyotiassisted.models.ErrorModel;

import java.io.IOException;

import okhttp3.ResponseBody;

public class Utils {

    public static String getErrorMessage(ResponseBody body) {
        try {
            String rawError = body.string();
            Gson gson = new Gson();
            ErrorModel errorModel = gson.fromJson(rawError, ErrorModel.class);
            if (errorModel != null && errorModel.getError() != null) {
                return errorModel.getError().getMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Something is wrong";
    }
}
