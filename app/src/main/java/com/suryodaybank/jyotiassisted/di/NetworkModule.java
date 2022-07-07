package com.suryodaybank.jyotiassisted.di;

import com.suryodaybank.jyotiassisted.services.RetroServiceInterface;
import com.suryodaybank.jyotiassisted.utils.HeaderInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class NetworkModule {

    String baseURL = "http://10.20.25.15:443/aocpv/v1/";

    @Singleton
    @Provides
    public Retrofit getRetroInstance(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkhttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        HeaderInterceptor headerInterceptor = new HeaderInterceptor();
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        clientBuilder.addInterceptor(interceptor);
        clientBuilder.addInterceptor(headerInterceptor);
        return clientBuilder.build();

    }

    @Singleton
    @Provides
    public RetroServiceInterface getRetroServiceInterface(Retrofit retrofit) {
        return retrofit.create(RetroServiceInterface.class);

    }

}
