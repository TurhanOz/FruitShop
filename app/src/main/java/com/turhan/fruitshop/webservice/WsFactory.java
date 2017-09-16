package com.turhan.fruitshop.webservice;

import com.turhan.fruitshop.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WsFactory {
    private final static String API_URL = "http://www.mocky.io/v2/59bd6d8a3c00001303529fba/";
    private static Retrofit retrofit;

    public static Retrofit getWsClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(getClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG)
            builder.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();

    }
}
