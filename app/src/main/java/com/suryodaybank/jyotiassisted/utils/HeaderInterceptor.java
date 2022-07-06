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
                .addHeader("appId","com.suryodaybank.assisted")
                .addHeader("deviceId","8ace-4b4f-b2b8-45b3a484b411")
                .addHeader("requestKey","")
                .addHeader("screenId","")
                .addHeader("status","")
                .addHeader("userId","")
                .addHeader("interfaceId","CBLogin")
                .addHeader("longitude","")
                .addHeader("latitude","")
                .addHeader("source","")
                .addHeader("requestID","")
                .addHeader("clientNonce","")
                .addHeader("serverNonce","")
                .addHeader("sessionToken","")
                .addHeader("reqRefId","")
                .build();


        Response response = chain.proceed(request);
        return response;

    }
}
