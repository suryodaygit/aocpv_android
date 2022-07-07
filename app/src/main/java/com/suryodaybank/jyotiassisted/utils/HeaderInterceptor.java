package com.suryodaybank.jyotiassisted.utils;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

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
                .build();


        Response response = chain.proceed(request);
        return response;

    }
}
