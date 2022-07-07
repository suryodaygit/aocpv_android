package com.suryodaybank.jyotiassisted.di;

import androidx.annotation.NonNull;

import com.suryodaybank.jyotiassisted.services.AocpvService;
import com.suryodaybank.jyotiassisted.services.RetroServiceInterface;
import com.suryodaybank.jyotiassisted.utils.Constants;
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
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(headerInterceptor);
        clientBuilder.addInterceptor(interceptor);
        return clientBuilder.build();
    }

    @Singleton
    @Provides
    public RetroServiceInterface getRetroServiceInterface(@NonNull Retrofit retrofit) {
        return retrofit.create(RetroServiceInterface.class);
    }

    @Singleton
    @Provides
    public AocpvService getAocpvService(@NonNull Retrofit retrofit) {
        return retrofit.create(AocpvService.class);
    }
}
