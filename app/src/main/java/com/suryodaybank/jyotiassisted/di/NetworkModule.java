package com.suryodaybank.jyotiassisted.di;

import com.suryodaybank.jyotiassisted.services.AocpvService;
import com.suryodaybank.jyotiassisted.services.RetroServiceInterface;
import com.suryodaybank.jyotiassisted.utils.Constants;
import com.suryodaybank.jyotiassisted.utils.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

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

    @Singleton
    @Provides
    public Retrofit getRetroInstance(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
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
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(interceptor)
                .readTimeout(140, TimeUnit.SECONDS)
                .connectTimeout(140, TimeUnit.SECONDS);
        return clientBuilder.build();
    }

    @Singleton
    @Provides
    public RetroServiceInterface getRetroServiceInterface(Retrofit retrofit) {
        return retrofit.create(RetroServiceInterface.class);
    }

    @Singleton
    @Provides
    public AocpvService getAocpvService(Retrofit retrofit) {
        return retrofit.create(AocpvService.class);
    }
}
