package com.example.okhttp.data.remote;

import static com.example.okhttp.util.Config.BASE_URL_API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    Retrofit retrofit;
    private static RetrofitClient instance;
    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static RetrofitClient getInstance(){
        if(instance == null) instance = new RetrofitClient();
        return instance;
    }

    public SongService getSongService(){
        return retrofit.create(SongService.class);
    }
}
