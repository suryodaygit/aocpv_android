package com.suryodaybank.jyotiassisted.utils;

import androidx.annotation.NonNull;

import com.suryodaybank.jyotiassisted.BuildConfig;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        String appId = BuildConfig.APPLICATION_ID;

        Headers headers = chain.request().headers();
        Request.Builder request = chain.request()
                .newBuilder()
                .addHeader("X-Transaction-ID", "EabeDcEE-db3c-BddD-CbD7-4bAA992c75d4")
                .addHeader("X-User-ID", "30639")
                .addHeader("appId", "com.suryodaybank.assisted")
                .addHeader("deviceId", "/aK0Qq/rzCTnGs6CClBKWjXvtG9QlGaK")
                .addHeader("source", "AOCPV")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", ": HttpOnly")
                .addHeader("requestKey", "")
                .addHeader("sessionId", "")
                .addHeader("loginTime", "")
                .addHeader("lastRequestTime", "")
                .addHeader("createTs", "")
                .addHeader("versionNo", versionName)
                .addHeader("userId", "")
                .addHeader("serverToken", "");

        if (headers.get("X-Correlation-ID") == null) {
            request.header("X-Correlation-ID", "1234");
        }
        if (headers.get("X-From-ID") == null) {
            request.header("X-From-ID", "CB");
        }
        if (headers.get("X-To-ID") == null) {
            request.header("X-To-ID", "MB");
        }
        if (headers.get("X-Request-ID") == null) {
            request.header("X-Request-ID", "AOCPV");
        }

        Response response = chain.proceed(request.build());
        return response;

    }
}
