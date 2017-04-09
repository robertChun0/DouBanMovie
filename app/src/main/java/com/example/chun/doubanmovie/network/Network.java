package com.example.chun.doubanmovie.network;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chun on 17/4/9.
 */

public class Network {
    private static final String BASE_URL="https://api.douban.com";
    private static DoubanApi doubanApi;
    private static OkHttpClient mOkHttpClient=new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static DoubanApi getDoubanApi(){
        if(doubanApi==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            doubanApi=retrofit.create(DoubanApi.class);
        }
        return doubanApi;
    }


}
