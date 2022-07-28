package com.suryodaybank.jyotiassisted.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.suryodaybank.jyotiassisted.models.ErrorModel;

import java.io.IOException;

import okhttp3.ResponseBody;

public class Utils {

    private static final String TAG = "Utils";

    public static String getErrorMessage(ResponseBody body) {
        try {
            String rawError = body.string();
            Log.d(TAG, "getErrorMessage: " + rawError);
            Gson gson = new Gson();
            ErrorModel errorModel = gson.fromJson(rawError, ErrorModel.class);
            if (errorModel != null && errorModel.getError() != null) {
                if (errorModel.getError().getMessage() != null)
                    return errorModel.getError().getMessage();
                else if (errorModel.getError().getDescription() != null)
                    return errorModel.getError().getDescription();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Something is wrong";
    }
}
