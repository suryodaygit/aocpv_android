package com.suryodaybank.jyotiassisted.utils;

import androidx.annotation.NonNull;

import com.suryodaybank.jyotiassisted.BuildConfig;

import java.io.IOException;

import javax.inject.Inject;

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


        Request request = chain.request()
                .newBuilder()
                .addHeader("X-Correlation-ID","DIG123456789056")
                .addHeader("X-From-ID","AOCPV")
                .addHeader("X-To-ID","123")
                .addHeader("X-Transaction-ID","123")
                .addHeader("X-User-ID","345")
                .addHeader("X-Request-ID","AOCPV")
                .addHeader("Content-Type","application/json")
                .addHeader("Cookie",": HttpOnly")
                .addHeader("requestKey","")
                .addHeader("sessionId","")
                .addHeader("loginTime","")
                .addHeader("lastRequestTime","")
                .addHeader("createTs","")
                .addHeader("versionNo",versionName)
                .addHeader("appId", appId)
                .addHeader("userId","")
                .addHeader("serverToken","")
                .build();


        Response response = chain.proceed(request);
        return response;

    }
}
